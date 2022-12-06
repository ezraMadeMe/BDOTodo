package com.ezralee.bdotodo.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentColorPickerBinding
import com.ezralee.bdotodo.goallist.SetGoalActivity
import com.ezralee.bdotodo.goallist.SetGoalFragment1

class ColorPickerActivity : AppCompatActivity() {
    val binding: FragmentColorPickerBinding by lazy { FragmentColorPickerBinding.inflate(layoutInflater) }
    val adapter: ColorPickerAdapter by lazy { ColorPickerAdapter(this@ColorPickerActivity,items) }

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

        binding.colorPickerRecycler.adapter = adapter

        adapter.setOnItemClickListener(object: ColorPickerAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
//                var returnIntent = Intent()
//                returnIntent.putExtra("color", items[position])
//                Log.i("item@@@@",items[position].toString())
//                setResult(RESULT_OK,returnIntent)
//                startActivityForResult(returnIntent,101)

                var frag1 = SetGoalFragment1()
                var bundle = Bundle()
                bundle.putInt("color",items[position])
                frag1.arguments = bundle
                //IllegalArgumentException
                //supportFragmentManager.beginTransaction().replace(R.id.set_goal1_root,frag1).commit()

                this@ColorPickerActivity.finish()
            }
        })
    }

    override fun onResume() {
        super.onResume()

    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//    }
}