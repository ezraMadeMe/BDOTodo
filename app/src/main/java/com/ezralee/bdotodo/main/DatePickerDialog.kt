package com.ezralee.bdotodo.main

import android.content.Intent
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
import com.ezralee.bdotodo.history.SetHistoryActivity

class DatePickerDialog : DialogFragment() {

    //lateinit var binding: FragmentDatePickerBinding
//    private var _binding:FragmentDatePickerBinding? = null
//    val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDatePickerBinding.inflate(inflater, container, false)

        binding.datePicker.setOnDateChangedListener { datePicker, i, i2, i3 ->
            //Toast.makeText(requireContext(), "$i/$i2/$i3", Toast.LENGTH_SHORT).show()
            var month = String.format("%02d",i2+1)
            var day = String.format("%02d",i3)
            var intent = Intent()
            intent.putExtra("date","$i/$month/$day")
            startActivityForResult(intent,100)
            dismiss()
        }

        binding.datePicker.setOnFocusChangeListener { view, b ->
            view.setBackgroundColor(resources.getColor(R.color.colorOnSecondary))
        }
        return binding.root
    }
}