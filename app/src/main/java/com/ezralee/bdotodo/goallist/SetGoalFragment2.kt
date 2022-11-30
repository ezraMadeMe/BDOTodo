package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentSetGoal2Binding
import com.ezralee.bdotodo.main.TaskItem
import com.ezralee.bdotodo.main.DatePickerDialog

class SetGoalFragment2: Fragment() {
    val binding: FragmentSetGoal2Binding by lazy { FragmentSetGoal2Binding.inflate(layoutInflater) }
    var items: MutableList<TaskItem> = mutableListOf(
        TaskItem("고대 셰레칸의 선단",1, 0),
        TaskItem("용의 이빨", 100, 87)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.taskRecycler.adapter = GoalTaskRecyclerAdapter(requireContext(),items)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goalStartDate.setOnClickListener {
            DatePickerDialog().show(parentFragmentManager,"date picker")
        }
        binding.goalEndDate.setOnClickListener {
            DatePickerDialog().show(parentFragmentManager,"date picker")
        }

        binding.addDetailGoalPage.setOnClickListener{
            Toast.makeText(activity as SetGoalActivity,  (activity as SetGoalActivity).items.size, Toast.LENGTH_SHORT).show()
            (activity as SetGoalActivity).addPage()
        }

        binding.deleteDetailGoalPage.setOnClickListener {
            (activity as SetGoalActivity).deletePage()
        }
    }
}
