package com.ezralee.bdotodo.main

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentColorPickerBinding

class ColorPickerActivity : AppCompatActivity() {
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
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.colorPickerRecycler.adapter = ColorPickerAdapter(this@ColorPickerActivity,items)
        binding.colorPickerRecycler.setOnClickListener {
            this@ColorPickerActivity.finish()
        }
    }
}