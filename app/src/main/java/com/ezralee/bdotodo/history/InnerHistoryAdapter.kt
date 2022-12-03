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
        //날짜별로 각 날짜에 속한 히스토리만 출력되어야 함

        //이미지 연결 -  DB안에는 이미지의 경로 주소만 있음. 즉, 서버컴퓨터 도메인 주소가 없음.
        var imgUrl="http://ezra2022.dothome.co.kr/bdotodo/" + items[position].image

        holder.binding.innerRecyclerTitle.text = items[position].title
        Glide.with(context).load(imgUrl).into(holder.binding.innerRecyclerImage)
        //카테고리

        holder.itemView.setOnClickListener {
            var intent = Intent(context,ShowHistoryActivity::class.java)
            intent.putExtra("title",items[position].title)
            intent.putExtra("date", items[position].date)
            intent.putExtra("category",items[position].category)
            intent.putExtra("memo",items[position].memo)
            intent.putExtra("image",imgUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}