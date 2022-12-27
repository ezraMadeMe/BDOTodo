package com.ezralee.bdotodo.ui.fragment.goal

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.TaskData
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.databinding.FragmentSetGoal2Binding
import com.ezralee.bdotodo.ui.adapter.goal.TaskAdapter
import com.ezralee.bdotodo.ui.adapter.goal.TaskAdapter.OnGoalItemClickListener
import com.ezralee.bdotodo.viewmodel.goal.SetGoalActivityVM

class SetGoalFragment2 : Fragment() {

    lateinit var binding: FragmentSetGoal2Binding
    lateinit var viewModel: SetGoalActivityVM
    lateinit var db: GoalDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = GoalDB.getInstance(requireContext())!!
        viewModel = ViewModelProvider(this)[SetGoalActivityVM::class.java]
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_set_goal_2)

        binding.apply {
            lifecycleOwner = this@SetGoalFragment2
            viewModel = viewModel
            taskRecycler.adapter = TaskAdapter(viewModel, this@SetGoalFragment2,
                object : OnGoalItemClickListener<TaskData>{
                //task 리사이클러 추가 및 삭제 클릭 리스너
                override fun onAddTaskClick(data: TaskData) {
                    viewModel.addTask()
//                    binding.taskRecycler.adapter?.notifyItemInserted(viewModel.currentPage + 1)
                }
                override fun onDeleteTaskClick(position: Int) {
                    viewModel.deleteTask(position)
//                    binding.taskRecycler.adapter?.notifyDataSetChanged()
                }
            })
            viewModel.goalData.value?.andor = andOrToggle.isActivated
        }
    }//onCreate

    companion object {
        fun newInstance() =
            SetGoalFragment2().apply {
                arguments =
                    Bundle().apply {
                        putString("any", "새 페이지 생성")
                    }
            }
    }
}