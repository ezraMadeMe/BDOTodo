package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.databinding.GoallistRecyclerItemBinding

class GoalAdapter(val context: Context) : ListAdapter<GoalItem, GoalAdapter.Holder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = GoallistRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<GoalItem>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: GoallistRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GoalItem) {
            binding.goalData = data.goalData
            binding.apply {
                goallistDetailRecycler.adapter = GoalInnerAdapter()
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GoalItem>() {
            override fun areItemsTheSame(oldItem: GoalItem, newItem: GoalItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GoalItem, newItem: GoalItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}