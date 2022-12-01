package com.ezralee.bdotodo.history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.HistoryInnerRecyclerItemBinding
import com.ezralee.bdotodo.main.HistoryItem
import java.text.SimpleDateFormat
import java.util.*

class InnerHistoryAdapter(val context: Context, var historyInnerItems: MutableList<HistoryItem>) :
    RecyclerView.Adapter<InnerHistoryAdapter.VH>() {

    inner class VH(itemview: View) : RecyclerView.ViewHolder(itemview){
        val binding: HistoryInnerRecyclerItemBinding = HistoryInnerRecyclerItemBinding.bind(itemview)

        init {
            binding.innerRecyclerTitle.text = "앱 설치일"
            Glide.with(context).load(R.drawable.ic_baseline_create_24).into(binding.innerRecyclerImage)
        }
    }//inner class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.history_inner_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        //holder.binding.innerRecyclerTitle.text = ShowHistoryDetailActivity().binding.historyTitle.text
        //Glide.with(context).load(holder.binding.innerRecyclerImage).into(ShowHistoryDetailActivity().binding.historyImage)

        holder.binding.innerRecyclerTitle.text = historyInnerItems[position].title
        Glide.with(context).load(historyInnerItems[position]).error(R.drawable.ic_baseline_create_24).into(holder.binding.innerRecyclerImage)
        //카테고리

        holder.itemView.setOnClickListener {
            var intent = Intent(context,ShowHistoryDetailActivity::class.java)
            intent.putExtra("title",historyInnerItems[position].title)
            intent.putExtra("date", historyInnerItems[position].date)
            intent.putExtra("category",historyInnerItems[position].category)
            intent.putExtra("memo",historyInnerItems[position].memo)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return historyInnerItems.size
    }
}