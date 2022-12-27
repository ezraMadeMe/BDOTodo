package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.databinding.GoalPresetRecyclerItemBinding

class PresetAdapter(val context: Context,
private val listener: OnPresetClickListener<GoalItem>) :
    ListAdapter<GoalItem, PresetAdapter.Holder>(diffUtil) {

    interface OnPresetClickListener<T> {
        fun onPresetClick(data: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            GoalPresetRecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<GoalItem>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: GoalPresetRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(data: GoalItem) {
                binding.presetGoal.text = data.goalData.goal
                binding.root.setOnClickListener {
                    listener.onPresetClick(getItem(adapterPosition))
                }
            }
        }

        companion object {
            val diffUtil = object : DiffUtil.ItemCallback<GoalItem>(){
                override fun areItemsTheSame(oldItem: GoalItem, newItem: GoalItem): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: GoalItem, newItem: GoalItem): Boolean {
                    return oldItem == newItem
                }

            }
        }
}