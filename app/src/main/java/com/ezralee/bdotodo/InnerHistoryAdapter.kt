package com.ezralee.bdotodo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.databinding.HistoryInnerRecyclerItemBinding

class InnerHistoryAdapter(val historyInnerItems: Array<HistoryInnerItem>) :
    RecyclerView.Adapter<InnerHistoryAdapter.VH>() {

    inner class VH(val binding: HistoryInnerRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: HistoryInnerItem){
            binding.innerRecyclerTitle.text = item.title
            Glide.with(binding.root).load(item.image).into(binding.innerRecyclerImage)
            binding.root.setOnClickListener{
                //해당 데이터를 가진 ShowHistoryActivity 팝업

            }
        }
    }//inner class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = HistoryInnerRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(historyInnerItems[position])
    }

    override fun getItemCount(): Int {
        return historyInnerItems.size
    }


}