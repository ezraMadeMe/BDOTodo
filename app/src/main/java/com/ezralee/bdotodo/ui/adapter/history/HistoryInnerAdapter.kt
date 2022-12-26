package com.ezralee.bdotodo.ui.adapter.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding

class HistoryInnerAdapter(val context: Context, private val listener: OnHistoryItemClickListener<HistoryData>)
    : ListAdapter<HistoryData, HistoryInnerAdapter.Holder>(diffUtil) {

    interface OnHistoryItemClickListener<T>{
        fun onHistoryClick(data: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<HistoryData>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: HistoryRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                listener.onHistoryClick(getItem(adapterPosition))
            }
        }
        fun bind(currentData: HistoryData){
            binding.data = currentData
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HistoryData>(){
            override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
                return oldItem == newItem
            }
        }
    }
}