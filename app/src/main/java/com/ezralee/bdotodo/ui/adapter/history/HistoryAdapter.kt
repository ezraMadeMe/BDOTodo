package com.ezralee.bdotodo.ui.adapter.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.ui.activity.history.ShowHistoryActivity
import com.ezralee.bdotodo.ui.adapter.OnItemClickListener
import com.ezralee.bdotodo.viewmodel.main.eventObserve

class HistoryAdapter: ListAdapter<HistoryData,HistoryAdapter.VH>(diffUtil) {

    inner class VH(private val binding: HistoryRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root),View.OnClickListener {

        fun bind(data: HistoryData){
            binding.apply {
                this.data.date = data.date
                this.data.title = data.title
                this.data.category = data.category
                this.data.imgUrl = data.imgUrl
                this.data.memo = data.memo
                this.data.imgUrl = data.imgUrl

                if (adapterPosition%2 == 0){
                    binding.historyRecyclerDatasLeft.visibility = View.INVISIBLE
                    binding.historyRecyclerLeft.visibility = View.INVISIBLE
                    binding.historyRecyclerDateLeft.visibility = View.INVISIBLE
                    binding.lineLeft.visibility = View.INVISIBLE
                }else{
                    binding.historyRecyclerRight.visibility = View.INVISIBLE
                    binding.historyRecyclerDatasRight.visibility = View.INVISIBLE
                    binding.historyRecyclerDateRight.visibility = View.INVISIBLE
                    binding.lineLeft.visibility = View.INVISIBLE
                }
            }
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }
    }//inner class

    private lateinit var onItemClickListener : OnItemClickListener

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<HistoryData>?) {
        super.submitList(list)
    }

    private fun initObserve() {
        viewModel.openEvent.eventObserve(this) { data ->
            val intent = Intent(requireContext(), ShowHistoryActivity::class.java)
            val bundle = Bundle()
            val datas = arrayOf(
                data.title,
                data.date,
                data.category,
                data.imgUrl,
                data.memo
            )
            bundle.putStringArray("data", datas)
            intent.putExtra("data", bundle)
            startActivity(intent)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HistoryData>(){
            override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}