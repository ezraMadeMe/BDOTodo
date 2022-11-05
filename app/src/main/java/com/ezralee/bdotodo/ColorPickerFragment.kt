package com.ezralee.bdotodo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.colorPickerRecycler.adapter = ColorPickerAdapter(requireContext())

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



    }
}