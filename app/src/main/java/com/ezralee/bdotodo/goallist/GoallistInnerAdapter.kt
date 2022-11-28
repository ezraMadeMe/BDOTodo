package com.ezralee.bdotodo.goallist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.GoallistInnerRecyclerItemBinding
import com.ezralee.bdotodo.history.GoalListItem

//소목표 단
class GoallistInnerAdapter(var context: Context, var items: MutableList<GoalListItem>): RecyclerView.Adapter<GoallistInnerAdapter.VH>() {

    inner class VH(itemview: View) : RecyclerView.ViewHolder(itemview){
        val binding: GoallistInnerRecyclerItemBinding = GoallistInnerRecyclerItemBinding.bind(itemview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.goallist_inner_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.goallistInnerRecyclerItemTitle.text = items[position].title
        holder.binding.goallistInnerRecycler.adapter = GoallistInnerInnerAdapter(context,items)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}