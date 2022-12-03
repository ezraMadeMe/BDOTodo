package com.ezralee.bdotodo.goallist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.GoallistInnerInnerRecyclerItemBinding
import com.ezralee.bdotodo.main.GoalListItem
import com.ezralee.bdotodo.main.TaskItem

class GoallistInnerInnerAdapter(var context: Context, var items: MutableList<TaskItem>) : RecyclerView.Adapter<GoallistInnerInnerAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView){
        val binding: GoallistInnerInnerRecyclerItemBinding = GoallistInnerInnerRecyclerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.goallist_inner_inner_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.goallistInnerInnerTask.text = items[position].task
        holder.binding.goallistInnerInnerPercentage.text = "${(items[position].count)/items[position].total}+%"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}