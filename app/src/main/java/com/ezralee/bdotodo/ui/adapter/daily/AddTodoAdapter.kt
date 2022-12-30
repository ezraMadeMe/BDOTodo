package com.ezralee.bdotodo.ui.adapter.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.TaskAccureData
import com.ezralee.bdotodo.data.model.TaskData
import com.ezralee.bdotodo.databinding.AddTodoRecyclerItemBinding
import com.ezralee.bdotodo.databinding.SetGoal2RecyclerItemBinding
import com.ezralee.bdotodo.viewmodel.daily.MainDailyVM
import com.ezralee.bdotodo.viewmodel.goal.SetGoalVM

class AddTodoAdapter(
    private var viewModel: MainDailyVM,
    private var owner: ViewModelStoreOwner,
    private val listener: OnDailyItemClickListener<TaskAccureData>
) : ListAdapter<TaskAccureData, AddTodoAdapter.Holder>(diffUtil) {

    interface OnDailyItemClickListener<T> {
        fun onDeleteDataClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            AddTodoRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<TaskAccureData>?) {
        super.submitList(list)
    }

    inner class Holder(private val binding: AddTodoRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            viewModel = ViewModelProvider(owner)[MainDailyVM::class.java]
            binding.btnAddTodoDelete.setOnClickListener {
                listener.onDeleteDataClick(adapterPosition)
            }
        }

        fun bind(currentData: TaskAccureData) {
            binding.accureItem = currentData
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TaskAccureData>() {
            override fun areItemsTheSame(oldItem: TaskAccureData, newItem: TaskAccureData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TaskAccureData, newItem: TaskAccureData): Boolean {
                return oldItem == newItem
            }
        }
    }
}