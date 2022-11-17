package com.ezralee.bdotodo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.databinding.HistoryInnerRecyclerItemBinding

class InnerHistoryAdapter(val context: Context, val historyInnerItems: Array<HistoryInnerItem>) :
    RecyclerView.Adapter<InnerHistoryAdapter.VH>() {

    inner class VH(itemview: View) : RecyclerView.ViewHolder(itemview){
        val binding: HistoryInnerRecyclerItemBinding = HistoryInnerRecyclerItemBinding.bind(itemview)
        init {
            binding.root.setOnClickListener{

            }
        }
    }//inner class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(LayoutInflater.from(context).inflate(R.layout.history_inner_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        //holder.binding.innerRecyclerTitle.text = ShowHistoryDetailActivity().binding.historyTitle.text
        //Glide.with(context).load(holder.binding.innerRecyclerImage).into(ShowHistoryDetailActivity().binding.historyImage)
        holder.binding.innerRecyclerTitle.text = historyInnerItems[position].title
        Glide.with(context).load(historyInnerItems[position]).into(holder.binding.innerRecyclerImage)
        //카테고리
    }

    override fun getItemCount(): Int {
        return historyInnerItems.size
    }


}