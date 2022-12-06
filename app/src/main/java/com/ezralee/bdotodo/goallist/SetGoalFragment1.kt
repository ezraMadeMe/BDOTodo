package com.ezralee.bdotodo.goallist

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentSetGoal1Binding
import com.ezralee.bdotodo.main.*

class SetGoalFragment1 : MyGoalFragment() {
    lateinit var binding: FragmentSetGoal1Binding

    companion object{
        var items: MutableList<GoalItem> = mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetGoal1Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goalStartDate.text = Info.date
        binding.goalEndDate.text = Info.date

        var intentPre = Intent()
        if (intentPre != null) {
            //프리셋 및 기존에 설정된 목표데이터를 받아올 인텐트
        }


        //색상 변경
        binding.goalColorPicker.setOnClickListener {
            var intentColor = Intent(requireContext(),ColorPickerActivity::class.java)
            startActivity(intentColor)

            if (arguments != null){
                val color = arguments?.getInt("color")
                Log.i("get@@@@",color.toString())
                binding.goalColorPicker.setColorFilter(ContextCompat.getColor(requireContext(), color!!))
            }
//            if (intentColor != null){
//                var color = intentColor.getIntExtra("color",R.color.colorPicker02)
//                Log.i("pick@@@@",color.toString())
//                binding.historyColorPicker.setColorFilter(ContextCompat.getColor(requireContext(), color))
//            }
//            activity?.startActivityForResult(Intent(requireContext(),ColorPickerActivity::class.java),101)
//            activity?.setResult(RESULT_OK)
        }

        //시작일 변경 - 날짜안보임 어디감..
        binding.goalStartDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(parentFragmentManager, "date")
            var pickedDate = intentPre.getStringExtra("date")
            binding.goalStartDate.text = pickedDate
        }

        //종료일 변경 - 날짜 안보임 어디감..
        binding.goalEndDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(parentFragmentManager, "date")
            var pickedDate = intentPre.getStringExtra("date")
            binding.goalStartDate.text = pickedDate
        }

        binding.goalDone.setOnClickListener {

        }
    }

    open fun goalData(): GoalItem{
        var gl = binding.historyTitleEdit.text.toString()
        var glS = binding.goalStartDate.text.toString()
        var glE = binding.goalEndDate.text.toString()
        var clr = binding.goalColorPicker.solidColor.toString()
        var ctgr = binding.historyCategory.selectedItem.toString()
        var memo = binding.historyMemoEdit.text.toString()

        var item = getGoalData(gl,glS,glE,clr,ctgr,memo)

        return item //GoalItem
    }


}