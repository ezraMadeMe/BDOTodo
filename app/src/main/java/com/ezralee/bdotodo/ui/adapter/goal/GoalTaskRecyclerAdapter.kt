package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.SetGoal2RecyclerItemBinding
import com.ezralee.bdotodo.main.TaskItem
import com.ezralee.bdotodo.main.TaskList
import com.ezralee.bdotodo.ui.fragment.goal.MyGoalFragment

class GoalTaskRecyclerAdapter(var context: Context, var taskItems: MutableList<TaskItem>) :
    RecyclerView.Adapter<GoalTaskRecyclerAdapter.VH>() {

    companion object{
        var task: String = ""
        var total: Int = 0
        var count: Int = 0

        var newTask: TaskItem? = null
        var newTaskList: TaskList? = null
    }

    inner class VH(itemView: View) : ViewHolder(itemView) {
        val binding: SetGoal2RecyclerItemBinding = SetGoal2RecyclerItemBinding.bind(itemView)
        init {
            binding.andOr.visibility = View.INVISIBLE
            newTask = MyGoalFragment.taskItem
//            newTaskList = MyGoalFragment.getTaskList(newTask!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.set_goal2_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.task.setText(taskItems[position].task)
        holder.binding.total.setText(taskItems[position].total.toString())

        //Task 데이터 추가
        task = taskItems[position].task
        total = taskItems[position].total
        newTask = TaskItem(task, total, count)
        newTaskList = MyGoalFragment.getTaskList(newTask!!)

        holder.binding.addTask.setOnClickListener {
            addTask(position)
        }
        holder.binding.deleteTask.setOnClickListener {
            deleteTask(position)
        }
    }

    override fun getItemCount(): Int {
        return taskItems.size
    }

    fun addTask(position: Int){
        var tsk = taskItems[position].task
        var ttl = taskItems[position].total
        var cnt = 0
        var item = TaskItem(tsk,ttl,cnt)

        //리사이클러뷰의 개수 변경
        taskItems.add(item)
        this@GoalTaskRecyclerAdapter?.notifyItemInserted(taskItems.size)
        //데이터의 개수 변경
        //newTaskList.tasks.add(newTask)
        Toast.makeText(context, ""+ newTaskList?.tasks?.size, Toast.LENGTH_SHORT).show()
    }

    fun deleteTask(position: Int){
        if (taskItems.size <= 1){
            Toast.makeText(context,"달성 방법은 하나 이상 존재해야 합니다", Toast.LENGTH_SHORT).show()
        }else{
            //기존에 작성되어 있던 항목들이 전부 초기화되는 문제
            taskItems.removeAt(position)
            this@GoalTaskRecyclerAdapter?.notifyDataSetChanged()
            //Toast.makeText(context, taskItems.size.toString(), Toast.LENGTH_SHORT).show()
            //데이터의 개수 변경
            //newTaskList.tasks.removeAt(position)
            Log.i("@@@@Pos/Size","POS:${position}+SIZE:${taskItems.size}+DATA:${newTaskList?.tasks?.size}")
        }
    }
}