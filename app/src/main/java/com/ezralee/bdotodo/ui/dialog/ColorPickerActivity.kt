package com.ezralee.bdotodo.ui.dialog

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.R

class ColorPickerActivity : AppCompatActivity() {

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

    }

}