package com.ezralee.bdotodo.ui.adapter.goal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.TaskAccureData
import com.ezralee.bdotodo.data.model.TaskData
import com.ezralee.bdotodo.data.model.TaskItem
import com.ezralee.bdotodo.databinding.SetGoal2RecyclerItemBinding
import com.ezralee.bdotodo.viewmodel.goal.SetGoalActivityVM

class TaskAdapter(
    private var viewModel: SetGoalActivityVM,
    private var owner: ViewModelStoreOwner,
    private val listener: OnGoalItemClickListener<TaskData>
) : ListAdapter<TaskData, TaskAdapter.Holder>(diffUtil) {

    interface OnGoalItemClickListener<T> {
        fun onAddTaskClick(data: T)
        fun onDeleteTaskClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            SetGoal2RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<TaskData>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: SetGoal2RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var initAccure = mutableListOf<TaskAccureData>()
        init {
            initAccure.add(TaskAccureData(KakaoLogin.USER_ID, binding.task.toString(), "", "0","0"))
            viewModel = ViewModelProvider(owner)[SetGoalActivityVM::class.java]

            binding.addTask.setOnClickListener {
                listener.onAddTaskClick(getItem(adapterPosition))
                mergeTask(getItem(adapterPosition))
            }
            binding.deleteTask.setOnClickListener {
                listener.onDeleteTaskClick(adapterPosition)
                removeTask()
            }
        }

        //최종 추가된 데이터 live data 에 추가시키기
        fun mergeTask(data: TaskData) {
            var totalData = TaskItem(data,initAccure)
            viewModel.planList.value?.taskList?.add(totalData)
        }
        //live data에서 해당 포지션의 데이터 삭제
        fun removeTask(){
            viewModel.planList.value?.taskList?.removeAt(adapterPosition)
        }

        fun bind(currentData: TaskData) {
            viewModel._taskData.value = currentData
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TaskData>() {
            override fun areItemsTheSame(oldItem: TaskData, newItem: TaskData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData): Boolean {
                return oldItem == newItem
            }
        }
    }
}