package com.ezralee.bdotodo

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.ezralee.bdotodo.databinding.ActivitySetHistoryBinding
import java.text.SimpleDateFormat
import java.util.*

class SetHistoryActivity : DialogFragment() {
    lateinit var binding: ActivitySetHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.history_dialog)

        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ActivitySetHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyCancel.setOnClickListener {
            this.dismiss()
        }

        binding.historyClose.setOnClickListener {
            this.dismiss()
        }

        binding.historyColorPicker.setOnClickListener {
            val colorPicker = ColorPickerFragment()
            colorPicker.show(parentFragmentManager, "set color")
            //컬러피커 색상 변경적용 안됨
            //colorPicker.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        }

        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        binding.historyDateEdit.setOnClickListener{
//            new DatePickerDialog(MainActivity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                //DO SOMETHING
//            }
//        }, 2015, 02, 26).show();

            val dlg = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.historyDateEdit.text = "${year}/${month+1}/${dayOfMonth}"
                }
            }, year, month, date)
            dlg.show()
        }

        binding.historyDateToday.setOnClickListener {
            var now = System.currentTimeMillis()
            var today = Date(now)
            var sdf = SimpleDateFormat("yyyy/MM/dd")
            binding.historyDateEdit.text = sdf.format(today)

        }
    }
}