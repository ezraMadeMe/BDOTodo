package com.ezralee.bdotodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import java.security.AccessController.getContext
import kotlin.coroutines.coroutineContext

class HistoryAdapter(val historyItems: Array<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.VH>(){

    val historyInnerItems: Array<HistoryInnerItem> = arrayOf(
        HistoryInnerItem("제목 1", R.drawable.img_sample),
        HistoryInnerItem("제목 2", R.drawable.img_sample),
        HistoryInnerItem("제목 3", R.drawable.img_sample),
        HistoryInnerItem("제목 4", R.drawable.img_sample),
        HistoryInnerItem("제목 5", R.drawable.img_sample)

    )

    inner class VH(val binding: HistoryRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(date: HistoryItem) {
            binding.historyRecyclerDateRight.text = date.date
            binding.historyRecyclerDateLeft.text = date.date
        }
    }//inner class VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(historyItems[position])
        holder.binding.historyRecyclerDatasRight.adapter = InnerHistoryAdapter(historyInnerItems)
        holder.binding.historyRecyclerDatasLeft.adapter = InnerHistoryAdapter(historyInnerItems)
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }


}