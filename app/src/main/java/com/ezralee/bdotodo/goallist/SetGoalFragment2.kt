package com.ezralee.bdotodo.goallist

import android.content.Intent
import android.os.Bundle
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
import com.ezralee.bdotodo.main.TaskItem
import com.ezralee.bdotodo.main.DatePickerDialog
import com.ezralee.bdotodo.main.PlanItem
import kotlinx.android.synthetic.main.fragment_set_goal_2.*
import kotlinx.android.synthetic.main.set_goal2_recycler_item.view.*

class SetGoalFragment2: Fragment() {
    val binding: FragmentSetGoal2Binding by lazy { FragmentSetGoal2Binding.inflate(layoutInflater) }
    var items: MutableList<TaskItem> = mutableListOf( TaskItem("달성 방법",0, 0) )
    
    companion object{
        fun newPlanInstance(pl: String, plS: String, plE: String, nor: Boolean) =
            SetGoalFragment2().apply {
                arguments = bundleOf(
                    SetGoalActivity.PLAN to pl,
                    SetGoalActivity.PLANSTART to plS,
                    SetGoalActivity.PLANEND to plE,
                    SetGoalActivity.ANDOR to nor
                )
            }
    }

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

        var plan = binding.planTitleEdit.text.toString()
        var planStart = binding.planStartDate.text.toString()
        var planEnd = binding.planEndDate.text.toString()
        var andor = binding.andOrToggle.isActivated

        newPlanInstance(plan,planStart,planEnd,andor)

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
    }/////////

//    //bundle로 데이터 전달(intent는 전달, bundle은 저장)
//    fun planBundle(){
//        val bundle= Bundle()
//        bundle.apply {
//            this.putString("plan",binding.planTitleEdit.text.toString())
//            this.putString("planStart",binding.planStartDate.text.toString())
//            this.putString("planEnd",binding.planEndDate.text.toString())
//            this.putBoolean("andor",binding.andOrToggle.isActivated)
//        }
//        this.arguments = bundle
//    }

//    //bundle로 데이터 전달(intent는 전달, bundle은 저장)
//    fun taskBundle(holder: GoalTaskRecyclerAdapter.VH): Bundle{
//        val bundle= Bundle()
//        bundle.apply {
//            this.putString("task",holder.binding.task.text.toString())
//            this.putString("total",holder.binding.total.text.toString())
//            this.putString("count","0")
//        }
//        this.arguments = bundle
//        return this.requireArguments()
//    }
}