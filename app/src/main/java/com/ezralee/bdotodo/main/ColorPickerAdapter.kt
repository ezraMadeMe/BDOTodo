package com.ezralee.bdotodo.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ColorPickerItemBinding

class ColorPickerAdapter(var context: Context, var items: MutableList<Int>) : RecyclerView.Adapter<ColorPickerAdapter.VH>() {

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