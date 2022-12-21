package com.ezralee.bdotodo.ui.fragment.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ezralee.bdotodo.databinding.FragmentSetGoal2Binding
import com.ezralee.bdotodo.dialog.DatePickerDialog
import com.ezralee.bdotodo.main.*
import com.ezralee.bdotodo.ui.adapter.goal.GoalTaskRecyclerAdapter
import com.ezralee.bdotodo.ui.activity.goal.SetGoalActivity

class SetGoalFragment2 : MyGoalFragment() {
    lateinit var binding: FragmentSetGoal2Binding
    var items: MutableList<TaskItem> = mutableListOf(TaskItem("", 0, 0))

    companion object{
        var planItem: PlanItem? = null
    }

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

        planItem = planData()
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