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

class DatePickerDialog: DialogFragment() {
    var _binding: FragmentDatePickerBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDatePickerBinding.inflate(inflater, container, false)
        binding.datePicker.setOnFocusChangeListener { view, b ->
            view.setBackgroundColor(resources.getColor(R.color.colorPrimaryVariant))

        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.datePicker.setOnDateChangedListener { datePicker, i, i2, i3 ->
            Toast.makeText(requireContext(), "$i/$i2/$i3", Toast.LENGTH_SHORT).show()
            var intent = Intent().putExtra("date","$i/$i2/$i3")
            startActivity(intent)
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}