package com.ezralee.bdotodo.ui.adapter.goal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.TaskAccureData
import com.ezralee.bdotodo.data.model.TaskData
import com.ezralee.bdotodo.databinding.SetGoal2RecyclerItemBinding
import com.ezralee.bdotodo.viewmodel.goal.SetGoalVM

class TaskAdapter(
    private var viewModel: SetGoalVM,
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

        init {
            viewModel = ViewModelProvider(owner)[SetGoalVM::class.java]

            binding.addTask.setOnClickListener {
                listener.onAddTaskClick(getItem(adapterPosition))
            }
            binding.deleteTask.setOnClickListener {
                listener.onDeleteTaskClick(adapterPosition)
            }
        }

        fun bind(currentData: TaskData) {
            binding.taskData = currentData
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