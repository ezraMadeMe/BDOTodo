package com.ezralee.bdotodo.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(var context: Context, var historyItems: Array<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.VH>(){

    val now = System.currentTimeMillis()
    val date = Date(now)
    val sdf = SimpleDateFormat("yyyy/MM/dd")
    val createdDate = sdf.format(date)

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
        holder.binding.historyRecyclerDatasRight.adapter = InnerHistoryAdapter(context, historyItems)
        holder.binding.historyRecyclerDatasLeft.adapter = InnerHistoryAdapter(context, historyItems)
        holder.binding.historyRecyclerDateRight.text = historyItems[position].date
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }


}