package com.ezralee.bdotodo.ui.adapter.history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import com.ezralee.bdotodo.ui.activity.history.ShowHistoryActivity

class HistoryAdapter(val context: Context) :
    ListAdapter<HistoryData, HistoryAdapter.Holder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<HistoryData>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: HistoryRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: HistoryData) {
            binding.data = data

            binding.apply {
                historyRecyclerDatasRight.adapter = HistoryInnerAdapter(context,
                    object : HistoryInnerAdapter.OnHistoryItemClickListener<HistoryData> {
                        val intent = Intent(context, ShowHistoryActivity::class.java)
                        override fun onHistoryClick(data: HistoryData) {
                            data.apply {
                                intent.putExtra("title", title)
                                intent.putExtra("date", date)
                                intent.putExtra("category", category)
                                intent.putExtra("image", imgUrl)
                                intent.putExtra("memo", memo)
                            }
                            context.startActivity(intent)
                        }
                    })

                historyRecyclerDatasLeft.adapter = HistoryInnerAdapter(context,
                    object : HistoryInnerAdapter.OnHistoryItemClickListener<HistoryData> {
                        val intent = Intent(context, ShowHistoryActivity::class.java)
                        override fun onHistoryClick(data: HistoryData) {
                            data.apply {
                                intent.putExtra("title", title)
                                intent.putExtra("date", date)
                                intent.putExtra("category", category)
                                intent.putExtra("image", imgUrl)
                                intent.putExtra("memo", memo)
                            }
                            context.startActivity(intent)
                        }
                    })
            }

            if (adapterPosition % 2 == 0) {
                binding.historyRecyclerRight.visibility = View.INVISIBLE
            } else {
                binding.historyRecyclerLeft.visibility = View.INVISIBLE
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HistoryData>() {
            override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
                return oldItem == newItem
            }
        }
    }
}