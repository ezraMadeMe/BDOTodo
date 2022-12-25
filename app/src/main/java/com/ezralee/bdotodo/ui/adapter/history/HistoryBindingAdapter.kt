package com.ezralee.bdotodo.ui.adapter.history

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.data.model.HistoryData

object HistoryBindingAdapter{

    //어댑터 아이템 연결, 갱신
    @BindingAdapter("items")
    @JvmStatic
    fun RecyclerView.setItems(items: List<HistoryData>){
        val historyAdapter = this.adapter as HistoryAdapter
        historyAdapter.submitList(items.toMutableList())
    }

    @BindingAdapter("image")
    @JvmStatic
    fun ImageView.setImage(imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }
}