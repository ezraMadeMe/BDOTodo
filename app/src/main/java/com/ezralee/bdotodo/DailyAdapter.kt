package com.ezralee.bdotodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.databinding.DailyRecyclerItemBinding

class DailyAdapter(var context: Context, var items: MutableList<DailyTodoItem>): RecyclerView.Adapter<DailyAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView){
        var binding: DailyRecyclerItemBinding = DailyRecyclerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemview: View = LayoutInflater.from(context).inflate(R.layout.daily_recycler_item,parent, false)
        return VH(itemview)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.dailyRecyclerItemCiv.setColorFilter(ContextCompat.getColor(context, items[position].color))
        holder.binding.dailyRecyclerItemDday.text = "D-"+items[position].dday
        holder.binding.dailyRecyclerItemPercentage.text = items[position].percent.toString()+"%"
        holder.binding.dailyRecyclerItemSubtitle.text = items[position].todo
        holder.binding.dailyRecyclerItemTitle.text = items[position].title
    }

    override fun getItemCount(): Int {
        return items.size
    }


}