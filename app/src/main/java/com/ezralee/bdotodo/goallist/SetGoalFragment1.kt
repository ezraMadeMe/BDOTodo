package com.ezralee.bdotodo.goallist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentSetGoal1Binding
import com.ezralee.bdotodo.main.ColorPickerActivity
import com.ezralee.bdotodo.main.DatePickerDialog
import com.ezralee.bdotodo.main.Info

class SetGoalFragment1 : Fragment() {
    lateinit var binding: FragmentSetGoal1Binding

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

        var intent = Intent()
        if (intent != null) {
            //프리셋 및 기존에 설정된 목표데이터를 받아올 인텐트
        }

        //색상 변경
        binding.historyColorPicker.setOnClickListener {
            val intent = Intent(requireContext(), ColorPickerActivity::class.java)
            startActivity(intent)
            //색상 변경 안됨, 다이얼로그 안 꺼짐
            if (intent.hasExtra("color")) {
                var color = intent.getIntExtra("color", R.color.colorPicker02)
                binding.historyColorPicker.setColorFilter( ContextCompat.getColor(requireContext(),color) )
            } else {
                Toast.makeText(requireContext(), "색상을 다시 지정하세요", Toast.LENGTH_SHORT).show()
            }
        }

        //시작일 변경 - 날짜안보임 어디감..
        binding.goalStartDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(parentFragmentManager, "date")
        }
        var pickedDate = intent.getStringExtra("date")
        binding.goalStartDate.text = pickedDate

        //종료일 변경 - 날짜 안보임 어디감..
        binding.goalEndDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(parentFragmentManager, "date")
        }
        pickedDate = intent.getStringExtra("date")
        binding.goalEndDate.text = pickedDate
    }

    //bundle로 데이터 전달(intent는 전달, bundle은 저장)
//    fun goalBundle(){
//        val bundle= Bundle()
//        bundle.apply {
//            this.putString("goal",binding.historyTitleEdit.text.toString())
//            this.putString("color",binding.historyColorPicker.solidColor.toString())
//            this.putString("goalStart",binding.goalStartDate.text.toString())
//            this.putString("goalEnd",binding.goalEndDate.text.toString())
//            this.putString("category",binding.historyCategory.selectedItem.toString())
//            this.putString("memo",binding.historyMemoEdit.text.toString())
//        }
//        this.arguments = bundle
//    }
}