package com.ezralee.bdotodo.ui.fragment.goal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.GoalData
import com.ezralee.bdotodo.databinding.FragmentSetGoal1Binding
import com.ezralee.bdotodo.ui.dialog.ColorPickerActivity
import com.ezralee.bdotodo.ui.dialog.DatePickerDialog

class SetGoalFragment1 : Fragment() {

    lateinit var binding: FragmentSetGoal1Binding
    var items: MutableList<GoalData> = mutableListOf()


    companion object{
        var goalItem: GoalData? = null
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
            var intentColor = Intent(requireContext(), ColorPickerActivity::class.java)
            startActivity(intentColor)

            var bundle = requireArguments()
            if (bundle != null) {
                val color = arguments?.getInt("color")
                Log.i("get@@@@", color.toString())
                binding.goalColorPicker.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        color!!
                    )
                )
            }
        }

        //시작일 변경 - 날짜안보임 어디감..
        binding.goalStartDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(parentFragmentManager, "date")

            var bundle = requireArguments()
            if (bundle != null) {
                var pickedDate = bundle.getString("date", Info.date)
                binding.goalStartDate.text = pickedDate
            }
        }

        //종료일 변경 - 날짜 안보임 어디감..
        binding.goalEndDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(parentFragmentManager, "date")

            var bundle = requireArguments()
            if (bundle != null) {
                var pickedDate = bundle.getString("date", Info.date)
                binding.goalStartDate.text = pickedDate
            }
        }

        goalItem = goalData()
    }

    fun goalData(): GoalData {
        var gl = binding.historyTitleEdit.text.toString()
        var glS = binding.goalStartDate.text.toString()
        var glE = binding.goalEndDate.text.toString()
        var clr = binding.goalColorPicker.solidColor.toString()
        var ctgr = binding.historyCategory.selectedItem.toString()
        var memo = binding.historyMemoEdit.text.toString()

        var item = GoalData(KakaoLogin.USER_ID, gl, glS, glE, clr, ctgr, memo)

        return item
    }
}