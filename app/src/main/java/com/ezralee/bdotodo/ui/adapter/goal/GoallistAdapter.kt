package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.GoallistRecyclerItemBinding
import com.ezralee.bdotodo.main.GoalItem
import com.ezralee.bdotodo.main.PlanItem
import com.ezralee.bdotodo.main.TaskItem
import java.text.SimpleDateFormat
import java.util.*

class GoallistAdapter(var context: Context, var items: MutableList<GoalItem>, var planItems: MutableList<PlanItem>, var taskItems: MutableList<TaskItem>) :
    RecyclerView.Adapter<GoallistAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView) {
        val binding: GoallistRecyclerItemBinding = GoallistRecyclerItemBinding.bind(itemView)
        init {
            binding.goallist.setOnClickListener {
                var item = items[adapterPosition]
                //item.isExpanded = false

                //val show = toggleLayout(!item.isExpanded, it, binding.goallistDetailRecycler)
                //item.isExpanded = show
            }
        }

        private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: RecyclerView): Boolean {
            // 2
            ToggleAnimation.toggleArrow(view, isExpanded)
            if (isExpanded) {
                ToggleAnimation.expand(layoutExpand)
            } else {
                ToggleAnimation.collapse(layoutExpand)
            }
            return isExpanded
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(context).inflate(R.layout.goallist_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.goallistDetailRecycler.adapter = GoallistInnerAdapter(context,planItems, taskItems)

        holder.binding.dailyRecyclerItemTitle.text = items[position].goal
        holder.binding.dailyRecyclerItemCiv.circleBackgroundColor = parseColor(items[position].color)
        holder.binding.dailyRecyclerItemDday.text = ddayCount(items[position].start, items[position].end)
        holder.binding.dailyRecyclerItemPercentage.text = "${percentCount(taskItems)}%"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

fun percentCount(taskItems: MutableList<TaskItem>): Int{
    var total : Int = 0
    var count : Int = 0

    for (item in taskItems){
        total = taskItems[0].total
        count = taskItems[0].count
        total += total
        count += count
    }

    var percent = count/total
    return percent
}

fun ddayCount(start: String, end: String): String{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    val startDate = dateFormat.parse(start).time
    val endDate = dateFormat.parse(end).time
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

//    if (start == null){
//        return "${(endDate - today) / (24 * 60 * 60 * 1000)}"
//    }else if (end == null){
//        return "${(today - startDate) / (24 * 60 * 60 * 1000)}"
//    }else{
//        return "${(endDate - startDate) / (24 * 60 * 60 * 1000)}"
//    }
    return "${(endDate - today) / (24 * 60 * 60 * 1000)}"
}

class ToggleAnimation{
    companion object{
        fun toggleArrow(view: View, isExpanded: Boolean): Boolean{
            if (isExpanded){
                view.animate().setDuration(200)
                return true
            }else{
                view.animate().setDuration(200)
                return false
            }
        }//화살표 애니메이션

        fun expand(view: View){
            val animation = expandAction(view)
            view.startAnimation(animation)
        }//확장 애니메이션

        fun expandAction(view: View): Animation {
            view.measure(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            val actualHeight = view.measuredHeight

            view.layoutParams.height = 0
            view.visibility = View.VISIBLE

            val animation = object : Animation(){
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    super.applyTransformation(interpolatedTime, t)
                    view.layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT
                    else (actualHeight * interpolatedTime).toInt()

                    view.requestLayout()
                }
            }
            animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()
            view.startAnimation(animation)

            return animation
        }//확장 애니메이션

        fun collapse(view: View){
            val actualHeight = view.measuredHeight

            val animation = object : Animation(){
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    super.applyTransformation(interpolatedTime, t)
                    if (interpolatedTime ==1f){
                        view.visibility = View.GONE
                    }else{
                        view.layoutParams.height = (actualHeight-(actualHeight * interpolatedTime)).toInt()
                        view.requestLayout()
                    }
                }
            }

            animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()
            view.startAnimation(animation)
        }//닫기 애니메이션
    }
}