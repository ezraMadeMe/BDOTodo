package com.ezralee.bdotodo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding

class HistoryAdapter(var context: Context, var historyDateItems: Array<HistoryDateItem>) : RecyclerView.Adapter<HistoryAdapter.VH>(){

    val historyInnerItems: Array<HistoryInnerItem> = arrayOf(
        HistoryInnerItem("제목 1", R.drawable.img_sample),
        HistoryInnerItem("제목 2", R.drawable.img_sample),
        HistoryInnerItem("제목 3", R.drawable.img_sample),
        HistoryInnerItem("제목 4", R.drawable.img_sample),
        HistoryInnerItem("제목 5", R.drawable.img_sample)

    )

    inner class VH(val binding: HistoryRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(date: HistoryDateItem) {
            binding.historyRecyclerDateRight.text = date.date
            binding.historyRecyclerDateLeft.text = date.date
        }
    }//inner class VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(historyDateItems[position])
        holder.binding.historyRecyclerDatasRight.adapter = InnerHistoryAdapter(context, historyInnerItems)
        holder.binding.historyRecyclerDatasLeft.adapter = InnerHistoryAdapter(context, historyInnerItems)
    }

    override fun getItemCount(): Int {
        return historyDateItems.size
    }


}