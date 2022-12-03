package com.ezralee.bdotodo.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import com.ezralee.bdotodo.main.HistoryItem
import kotlinx.android.synthetic.main.history_inner_recycler_item.view.*

class HistoryAdapter(var context: Context, var items: MutableList<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: HistoryRecyclerItemBinding = HistoryRecyclerItemBinding.bind(itemView)
    }//inner class VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding =
            HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding.root)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.historyRecyclerDatasRight.adapter = InnerHistoryAdapter(context, items)
        holder.binding.historyRecyclerDatasLeft.adapter = InnerHistoryAdapter(context, items)
        holder.binding.historyRecyclerDateLeft.text = items[position].date
        holder.binding.historyRecyclerDateRight.text = items[position].date

        when (position % 2) {
            0 -> {
                holder.binding.historyRecyclerDatasRight.visibility = View.INVISIBLE
                holder.binding.historyRecyclerDateRight.visibility = View.INVISIBLE
                holder.binding.lineRight.visibility = View.INVISIBLE
            }
            1 -> {
                holder.binding.historyRecyclerDatasLeft.visibility = View.INVISIBLE
                holder.binding.historyRecyclerDateLeft.visibility = View.INVISIBLE
                holder.binding.lineLeft.visibility = View.INVISIBLE

            }
            else -> {
                holder.binding.historyRecyclerDateRight.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}