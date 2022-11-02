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

    inner class VH(val binding: HistoryRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(date: HistoryItem) {
            binding.historyRecyclerDateRight.text = date.date
            binding.root.setOnClickListener {

            }
        }
    }//inner class VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(historyItems[position])
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }


}