package com.ezralee.bdotodo.goallist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentSetGoal2Binding
import com.ezralee.bdotodo.main.*
import kotlinx.android.synthetic.main.fragment_set_goal_2.*
import kotlinx.android.synthetic.main.set_goal2_recycler_item.view.*

class SetGoalFragment2: MyGoalFragment() {
    val binding: FragmentSetGoal2Binding by lazy { FragmentSetGoal2Binding.inflate(layoutInflater) }
    var items: MutableList<TaskItem> = mutableListOf( TaskItem("",0, 0) )
    var adapter = GoalTaskRecyclerAdapter(requireContext(),items)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.taskRecycler.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.planStartDate.setOnClickListener {
            DatePickerDialog().show(parentFragmentManager,"date picker")
        }
        binding.planEndDate.setOnClickListener {
            DatePickerDialog().show(parentFragmentManager,"date picker")
        }

        binding.addDetailGoalPage.setOnClickListener{
            (activity as SetGoalActivity).addPage()
        }

        binding.deleteDetailGoalPage.setOnClickListener {
            (activity as SetGoalActivity).deletePage()
        }

        var cnt = binding.taskRecycler.childCount
        binding.goalDone.setOnClickListener {

        }
    }/////////

    open fun planData(): PlanList {
        var gl = binding.planTitleEdit.text.toString()
        var glS = binding.planStartDate.text.toString()
        var glE = binding.planEndDate.text.toString()
        var nor = binding.andOrToggle.isActivated

        var item = getPlanData(gl,glS,glE, nor)

        SetGoalActivity.planList.plan = item
        SetGoalActivity.planList.taskList = SetGoalActivity.taskList

        return SetGoalActivity.planList
    }
}