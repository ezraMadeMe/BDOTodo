package com.ezralee.bdotodo.goallist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.SetGoal2RecyclerItemBinding
import com.ezralee.bdotodo.main.TaskItem
import kotlinx.android.synthetic.main.set_goal2_recycler_item.view.*

class GoalTaskRecyclerAdapter(var context: Context, var taskItems: MutableList<TaskItem>) :
    RecyclerView.Adapter<GoalTaskRecyclerAdapter.VH>() {

    companion object{
        fun newTaskInstance(tsk: String, ttl: Int) =
            SetGoalFragment2().apply {
                arguments = bundleOf(
                    SetGoalActivity.TASK to tsk,
                    SetGoalActivity.TOTAL to ttl
                )
            }
    }

    inner class VH(itemView: View) : ViewHolder(itemView) {
        val binding: SetGoal2RecyclerItemBinding = SetGoal2RecyclerItemBinding.bind(itemView)
        init {
            binding.task.setText("달성 방법")
            binding.total.setText("갯수")
            binding.andOr.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.set_goal2_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        var task = taskItems[position].task
        var total = taskItems[position].total

        newTaskInstance(task, total)

        holder.binding.task.setText(taskItems[position].task)
        holder.binding.total.setText(taskItems[position].total.toString())

        holder.binding.addTask.setOnClickListener {
            addTask()
        }
        holder.binding.deleteTask.setOnClickListener {
            deleteTask(position)
        }
    }

    override fun getItemCount(): Int {
        return taskItems.size
    }

    fun addTask(){
        taskItems.add(TaskItem("달성 방법",0,0))
        this@GoalTaskRecyclerAdapter?.notifyItemInserted(taskItems.size)
        Toast.makeText(context, ""+taskItems.size, Toast.LENGTH_SHORT).show()
    }

    fun deleteTask(position: Int){
        if (taskItems.size <= 1){
            Toast.makeText(context,"달성 방법은 하나 이상 존재해야 합니다", Toast.LENGTH_SHORT).show()
        }else{
            taskItems.removeAt(position)
            this@GoalTaskRecyclerAdapter?.notifyDataSetChanged()
            Toast.makeText(context, taskItems.size.toString(), Toast.LENGTH_SHORT).show()
        }
    }


}