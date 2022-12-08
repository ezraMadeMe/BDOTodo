package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.ezralee.bdotodo.databinding.FragmentSetGoal2Binding
import com.ezralee.bdotodo.main.*

class SetGoalFragment2 : MyGoalFragment() {
    lateinit var binding: FragmentSetGoal2Binding
    var items: MutableList<TaskItem> = mutableListOf(TaskItem("", 0, 0))

    lateinit var newPlanList: PlanList
    lateinit var newPlanUnit: PlanUnit
//    var newGoalList: GoalList = getGoalList(SetGoalFragment1.newGoalItem, newPlanUnit)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetGoal2Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskRecycler.adapter = GoalTaskRecyclerAdapter(requireContext(), items)
        newPlanList = getPlanList(planData(), GoalTaskRecyclerAdapter.newTaskList)
        newPlanUnit = getPlanUnit(newPlanList)

        binding.planStartDate.setOnClickListener {
            DatePickerDialog().show(parentFragmentManager, "date picker")
        }
        binding.planEndDate.setOnClickListener {
            DatePickerDialog().show(parentFragmentManager, "date picker")
        }

        binding.addDetailGoalPage.setOnClickListener {
            (activity as SetGoalActivity).addPage()
        }

        binding.deleteDetailGoalPage.setOnClickListener {
            (activity as SetGoalActivity).deletePage()
        }

        Log.i("@@@@GOAL", newPlanUnit.plans.size.toString())

    }/////////

    fun planData(): PlanItem {
        var pl = binding.planTitleEdit.text.toString()
        var plS = binding.planStartDate.text.toString()
        var plE = binding.planEndDate.text.toString()
        var nor = binding.andOrToggle.isActivated

        var item = PlanItem(pl, plS, plE, nor)

        return item
    }
}