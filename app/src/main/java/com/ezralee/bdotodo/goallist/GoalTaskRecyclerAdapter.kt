package com.ezralee.bdotodo.goallist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.SetGoal2RecyclerItemBinding
import com.ezralee.bdotodo.main.GoalItem
import com.ezralee.bdotodo.main.PlanItem
import com.ezralee.bdotodo.main.TaskItem
import com.ezralee.bdotodo.main.TaskList

class GoalTaskRecyclerAdapter(var context: Context, var taskItems: MutableList<TaskItem>) :
    RecyclerView.Adapter<GoalTaskRecyclerAdapter.VH>() {
    lateinit var task: String
    var total: Int = 0

    inner class VH(itemView: View) : ViewHolder(itemView) {
        val binding: SetGoal2RecyclerItemBinding = SetGoal2RecyclerItemBinding.bind(itemView)
        init {
            binding.andOr.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.set_goal2_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.task.setText(taskItems[position].task)
        holder.binding.total.setText(taskItems[position].total.toString())

        //Task 데이터 추가
        task = holder.binding.task.text.toString()
        total = Integer.parseInt(holder.binding.total.text.toString())

        var item = MyGoalFragment.getTaskData(task,total,0)
        SetGoalActivity.taskList.tasks.add(item)

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