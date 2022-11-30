package com.ezralee.bdotodo.goallist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.SetGoal2RecyclerItemBinding
import com.ezralee.bdotodo.main.TaskItem

class GoalTaskRecyclerAdapter(var context: Context, var items: MutableList<TaskItem>) :
    RecyclerView.Adapter<GoalTaskRecyclerAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView) {
        val binding: SetGoal2RecyclerItemBinding = SetGoal2RecyclerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(LayoutInflater.from(context).inflate(R.layout.task_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.addTask.setOnClickListener {
            items.add(TaskItem("", 100,14))
        }
        holder.binding.deleteTask.setOnClickListener {
            items.removeAt(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}