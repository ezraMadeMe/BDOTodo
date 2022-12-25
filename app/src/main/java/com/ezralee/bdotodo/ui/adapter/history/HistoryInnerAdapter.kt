package com.ezralee.bdotodo.ui.adapter.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import com.ezralee.bdotodo.ui.adapter.OnItemClickListener

class HistoryInnerAdapter(private val listener: OnItemClickListener)
    : ListAdapter<HistoryData, HistoryInnerAdapter.Holder>(diffUtil) {

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

    inner class Holder(private val binding: HistoryRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            //클릭된 아이템의 객체를 넘겨줌
            binding.root.setOnClickListener {
                listener.onItemClick(getItem(adapterPosition))
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