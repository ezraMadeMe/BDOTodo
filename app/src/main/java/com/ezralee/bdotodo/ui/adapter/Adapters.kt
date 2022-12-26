package com.ezralee.bdotodo.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object Adapters{
    @BindingAdapter("image")
    @JvmStatic
    fun ImageView.setImage(imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }

    @BindingAdapter("isGone")
    fun bindIsGone(view: View, isGone: Boolean) {
        view.visibility = if (isGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}