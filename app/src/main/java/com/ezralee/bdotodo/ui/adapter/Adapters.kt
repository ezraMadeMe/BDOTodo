package com.ezralee.bdotodo.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.model.PlanItem
import com.ezralee.bdotodo.data.model.TaskItem
import com.ezralee.bdotodo.ui.adapter.goal.GoalAdapter
import com.ezralee.bdotodo.ui.adapter.goal.GoalInnerAdapter
import com.ezralee.bdotodo.ui.adapter.goal.GoalInnerInnerAdapter
import com.ezralee.bdotodo.ui.adapter.history.HistoryAdapter
import com.ezralee.bdotodo.ui.adapter.history.HistoryInnerAdapter

object Adapters{
    //어댑터 아이템 연결, 갱신
    @BindingAdapter("goalItems")
    @JvmStatic
    fun RecyclerView.setGoals(items: List<GoalItem>){
        val goalAdapter = this.adapter as GoalAdapter
        goalAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("planItems")
    @JvmStatic
    fun RecyclerView.setPlans(items: List<PlanItem>){
        val planAdapter = this.adapter as GoalInnerAdapter
        planAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("taskItems")
    @JvmStatic
    fun RecyclerView.setTasks(items: List<TaskItem>){
        val taskAdapter = this.adapter as GoalInnerInnerAdapter
        taskAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("historyItems")
    @JvmStatic
    fun RecyclerView.setHistory(items: List<HistoryData>){
        val historyAdapter = this.adapter as HistoryAdapter
        historyAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("historyInnerItems")
    @JvmStatic
    fun RecyclerView.setInnerHistory(items: List<HistoryData>){
        val historyInnerAdapter = this.adapter as HistoryInnerAdapter
        historyInnerAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("image")
    @JvmStatic
    fun ImageView.setImage(imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }

    @BindingAdapter("isVisible")
    fun setVisible(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}