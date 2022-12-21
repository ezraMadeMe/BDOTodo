package com.ezralee.bdotodo.ui.dialog

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentDatePickerBinding
import com.ezralee.bdotodo.goallist.SetGoalFragment1

class DatePickerDialog : DialogFragment() {
    lateinit var binding: FragmentDatePickerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDatePickerBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.datePicker.setOnDateChangedListener { datePicker, i, i2, i3 ->

            var month = String.format("%02d",i2+1)
            var day = String.format("%02d",i3)

            var frag1 = SetGoalFragment1()
            parentFragmentManager.beginTransaction().replace(R.id.set_goal1_root,frag1).commit()

            var bundle = Bundle()
            bundle.putString("date","$i/$month/$day")
            frag1.arguments = bundle

            Toast.makeText(requireContext(), "$i/$month/$day", Toast.LENGTH_SHORT).show()
//            var intent = Intent()
//            intent.putExtra("date","$i/$month/$day")
//            startActivityForResult(intent,100)
            dismiss()
        }

        binding.datePicker.setOnFocusChangeListener { view, b ->
            view.setBackgroundColor(resources.getColor(R.color.colorOnSecondary))
        }
    }
}