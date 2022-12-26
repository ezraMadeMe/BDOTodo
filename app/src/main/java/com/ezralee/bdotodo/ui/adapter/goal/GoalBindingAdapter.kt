package com.ezralee.bdotodo.ui.adapter.goal

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.model.PlanItem
import com.ezralee.bdotodo.data.model.TaskItem
import com.ezralee.bdotodo.ui.adapter.history.HistoryAdapter

object GoalBindingAdapter {
    //어댑터 아이템 연결, 갱신
    @BindingAdapter("items")
    @JvmStatic
    fun RecyclerView.setGoals(items: List<GoalItem>){
        val goalAdapter = this.adapter as GoalAdapter
        goalAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("items")
    @JvmStatic
    fun RecyclerView.setPlans(items: List<PlanItem>){
        val planAdapter = this.adapter as GoalInnerAdapter
       planAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("items")
    @JvmStatic
    fun RecyclerView.setTasks(items: List<TaskItem>){
        val taskAdapter = this.adapter as GoalInnerInnerAdapter
        taskAdapter.submitList(items.toMutableList())
    }
}