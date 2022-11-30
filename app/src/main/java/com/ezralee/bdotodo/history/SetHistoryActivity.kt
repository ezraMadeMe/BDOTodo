package com.ezralee.bdotodo.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivitySetHistoryBinding
import com.ezralee.bdotodo.main.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

class SetHistoryActivity : DialogFragment() {
    val binding: ActivitySetHistoryBinding by lazy { ActivitySetHistoryBinding.inflate(layoutInflater) }

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

//        binding.historyColorPicker.setOnClickListener {
//            val colorPicker = ColorPickerFragment()
//            colorPicker.show(parentFragmentManager, "set color")
//            //컬러피커 색상 변경적용 안됨
//            //colorPicker.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        }

        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        binding.historyDateEdit.setOnClickListener{
            var intent = Intent(context, DatePickerDialog::class.java)
            startActivityForResult(intent,2)
        }

        binding.historyDateToday.setOnClickListener {
            var now = System.currentTimeMillis()
            var today = Date(now)
            var sdf = SimpleDateFormat("yyyy/MM/dd")
            binding.historyDateEdit.text = sdf.format(today)

        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 2){
                if (data != null){
                    binding.historyDateEdit.text = data.getStringExtra("date")
                }
            }
        }
    }//OnViewCreated
}