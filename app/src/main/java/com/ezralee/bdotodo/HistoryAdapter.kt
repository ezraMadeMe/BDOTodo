package com.ezralee.bdotodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding

class HistoryAdapter(var context: Context, var items: MutableList<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: HistoryRecyclerItemBinding = HistoryRecyclerItemBinding.bind(itemView)
        init {
            binding.root
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder: View = LayoutInflater.from(context).inflate(R.layout.history_recycler_item, parent,false)
        return VH(holder)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.historyRecyclerDateRight.text = items[position].date
    }

    override fun getItemCount(): Int = items.size
}