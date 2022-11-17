package com.ezralee.bdotodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.GoallistRecyclerItemBinding

class GoallistAdapter(var context: Context, var goallistitems: Array<GoalListItem>) :
    RecyclerView.Adapter<GoallistAdapter.VH>() {

    var goallistInnerItem: Array<GoalListInnerItem> = arrayOf(
        GoalListInnerItem("셰레칸의 선단", 80),
        GoalListInnerItem("발타라의 천안", 20),
        GoalListInnerItem("잿빛 가크투낙의 눈깔", 50),
    )

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding: GoallistRecyclerItemBinding = GoallistRecyclerItemBinding.bind(itemView)

        init {
            binding.goallistDetailRecycler.adapter =
                GoallistInnerAdapter(context, goallistInnerItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(context).inflate(R.layout.goallist_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.dailyRecyclerItemTitle.text = goallistitems[position].title
        holder.binding.dailyRecyclerItemCiv.circleBackgroundColor = goallistitems[position].color
        holder.binding.dailyRecyclerItemDday.text = "D-${goallistitems[position].dday}"
        holder.binding.dailyRecyclerItemPercentage.text = "${goallistitems[position].percent}%"
        holder.binding.goallistShowMore.setOnClickListener {
            holder.binding.goallistDetailRecycler.adapter = GoallistInnerAdapter(context, goallistInnerItem)
            holder.binding.goallistDetailRecycler.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return goallistitems.size
    }
}