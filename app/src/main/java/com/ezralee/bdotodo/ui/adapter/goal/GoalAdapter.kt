package com.ezralee.bdotodo.ui.adapter.goal

import android.animation.ValueAnimator
import android.content.Context
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.databinding.GoallistRecyclerItemBinding

class GoalAdapter(val context: Context, private val listener: OnGoalItemClickListener<GoalItem>) :
    ListAdapter<GoalItem, GoalAdapter.Holder>(diffUtil) {

    interface OnGoalItemClickListener<T> {
        fun onGoalClick(data: T)
    }

    var selected = SparseBooleanArray()
    var preposition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            GoallistRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.apply {
            bind(getItem(position), selected)
        }
    }

    override fun submitList(list: MutableList<GoalItem>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: GoallistRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onGoalClick(getItem(adapterPosition))
            }
        }

        fun bind(data: GoalItem, sparse: SparseBooleanArray) {
            binding.apply {
                goal = data.goalData
                goallistDetailRecycler.adapter = GoalInnerAdapter(context)

                root.setOnClickListener {
                    changVisibility(sparse.get(adapterPosition))

                    if (selected.get(adapterPosition)) {
                        selected.delete(adapterPosition)
                    } else {
                        selected.delete(preposition)
                        selected.put(adapterPosition, true)
                    }

                    if (preposition != -1) notifyItemChanged(preposition)
                    notifyItemChanged(adapterPosition)
                    preposition = adapterPosition
                }
            }
        }//bind

        fun changVisibility(isExpanded: Boolean) {
            //plan task 목록 접고 펼치기
            var va =
                if (isExpanded) {
                    ValueAnimator.ofInt(0, 600)
                } else {
                    ValueAnimator.ofInt(600, 0)
                }
            va.duration = 500
            va.addUpdateListener {
                binding.goallistDetailRecycler.layoutParams.height = it.animatedValue as Int
                binding.root.requestLayout()
                binding.root.visibility =
                    if (isExpanded) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
            va.start()
        }//changeVisibility
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