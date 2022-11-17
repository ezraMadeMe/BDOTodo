package com.ezralee.bdotodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.GoallistInnerRecyclerItemBinding

class GoallistInnerAdapter(var context: Context, var goallistInnerItem: Array<GoalListInnerItem>): RecyclerView.Adapter<GoallistInnerAdapter.VH>() {

    inner class VH(itemview: View) : RecyclerView.ViewHolder(itemview){
        val binding: GoallistInnerRecyclerItemBinding = GoallistInnerRecyclerItemBinding.bind(itemview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.goallist_inner_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.goallistInnerRecyclerItemTitle.text = goallistInnerItem[position].title
        holder.binding.goallistInnerRecyclerItemPercentage.text = "${goallistInnerItem[position].percent} %"
    }

    override fun getItemCount(): Int {
        return goallistInnerItem.size
    }


}