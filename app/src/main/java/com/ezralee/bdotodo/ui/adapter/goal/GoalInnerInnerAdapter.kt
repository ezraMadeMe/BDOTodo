package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.TaskItem
import com.ezralee.bdotodo.databinding.GoallistInnerInnerRecyclerItemBinding

class GoalInnerInnerAdapter(val context: Context) :
    ListAdapter<TaskItem, GoalInnerInnerAdapter.Holder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = GoallistInnerInnerRecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<TaskItem>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: GoallistInnerInnerRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(data: TaskItem) {
                binding.taskData = data.taskData
            }
        }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TaskItem>() {
            override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}