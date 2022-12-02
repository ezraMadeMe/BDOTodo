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

class InnerHistoryAdapter(val context: Context, var items: MutableList<HistoryItem>) :
    RecyclerView.Adapter<InnerHistoryAdapter.VH>() {

    inner class VH(itemview: View) : RecyclerView.ViewHolder(itemview){
        val binding: HistoryInnerRecyclerItemBinding = HistoryInnerRecyclerItemBinding.bind(itemview)
    }//inner class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var view = LayoutInflater.from(context).inflate(R.layout.history_inner_recycler_item,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.innerRecyclerTitle.text = items[position].title
        Glide.with(context).load(items[position]).error(R.drawable.ic_baseline_create_24).into(holder.binding.innerRecyclerImage)
        //카테고리

        holder.itemView.setOnClickListener {
            var intent = Intent(context,ShowHistoryActivity::class.java)
            intent.putExtra("title",items[position].title)
            intent.putExtra("date", items[position].date)
            intent.putExtra("category",items[position].category)
            intent.putExtra("memo",items[position].memo)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}