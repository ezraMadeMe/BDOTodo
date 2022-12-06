package com.ezralee.bdotodo.main

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ColorPickerItemBinding
import com.ezralee.bdotodo.goallist.SetGoalActivity
import com.ezralee.bdotodo.goallist.SetGoalFragment1

class ColorPickerAdapter(var context: Context, var items: MutableList<Int>) : RecyclerView.Adapter<ColorPickerAdapter.VH>() {

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    lateinit var itemClickListener: OnItemClickListener

    open fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        itemClickListener = onItemClickListener
    }
    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ColorPickerItemBinding = ColorPickerItemBinding.bind(itemView)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                var item = items[position]
                //Log.i("color@@@@",position.toString())
                Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show()
                if(position != RecyclerView.NO_POSITION && itemClickListener != null){
                    itemClickListener.onItemClick(itemView, position)
                }
            }
        }
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

    open fun getColor(position: Int): Int{
        return items[position]
    }
}