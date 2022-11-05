package com.ezralee.bdotodo

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.ContentProvider
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.databinding.ColorPickerItemBinding

class ColorPickerAdapter(var context: Context) : RecyclerView.Adapter<ColorPickerAdapter.VH>() {

    var items: MutableList<Int> = mutableListOf(
        R.color.colorPicker01,
        R.color.colorPicker02,
        R.color.colorPicker03,
        R.color.colorPicker04,
        R.color.colorPicker05,
        R.color.colorPicker06,
        R.color.colorPicker07,
        R.color.colorPicker08,
    )

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ColorPickerItemBinding = ColorPickerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemview :View = LayoutInflater.from(context).inflate(R.layout.color_picker_item,parent,false)

        return VH(itemview)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.colorPickerItem.setColorFilter(ContextCompat.getColor(context, items[position]))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}