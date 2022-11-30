package com.ezralee.bdotodo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentNumberPickerBinding

class NumberPickerFragment: DialogFragment() {
    val binding: FragmentNumberPickerBinding by lazy { FragmentNumberPickerBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 1000
        binding.numberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            Toast.makeText(requireContext(), "$i + $i2", Toast.LENGTH_SHORT).show()
        }

        binding.numberPickerOk.setOnClickListener {
            //선택한 숫자를 task 개수 textview에 설정
        }
    }
}