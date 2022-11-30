package com.ezralee.bdotodo.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentColorPickerBinding

class ColorPickerFragment : DialogFragment() {
    val binding: FragmentColorPickerBinding by lazy { FragmentColorPickerBinding.inflate(layoutInflater) }

    var items: MutableList<Int> = mutableListOf(
        R.color.colorPicker01,
        R.color.colorPicker02,
        R.color.colorPicker03,
        R.color.colorPicker04,
        R.color.colorPicker05,
        R.color.colorPicker06,
        R.color.colorPicker07,
        R.color.colorPicker08,
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.colorPickerRecycler.adapter = ColorPickerAdapter(requireContext(),items)

        return binding.root
    }
}