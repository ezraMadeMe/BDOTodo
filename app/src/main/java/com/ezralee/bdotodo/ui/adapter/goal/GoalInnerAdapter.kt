package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.model.PlanItem
import com.ezralee.bdotodo.databinding.GoallistInnerRecyclerItemBinding

class GoalInnerAdapter(val context: Context) :
    ListAdapter<PlanItem, GoalInnerAdapter.Holder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = GoallistInnerRecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<PlanItem>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: GoallistInnerRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(data: PlanItem) {
                binding.planData = data.planData
                binding.apply {
                    goallistInnerRecycler.adapter = GoalInnerInnerAdapter(context)

                }
            }
        }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PlanItem>() {
            override fun areItemsTheSame(oldItem: PlanItem, newItem: PlanItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PlanItem, newItem: PlanItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}