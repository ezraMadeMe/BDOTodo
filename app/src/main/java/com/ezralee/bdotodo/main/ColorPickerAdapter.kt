package com.ezralee.bdotodo.main

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ColorPickerItemBinding
import com.ezralee.bdotodo.goallist.SetGoalActivity
import com.ezralee.bdotodo.goallist.SetGoalFragment1

class ColorPickerAdapter(var context: Context, var items: MutableList<Int>) : RecyclerView.Adapter<ColorPickerAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ColorPickerItemBinding = ColorPickerItemBinding.bind(itemView)
        init {
            binding.colorPickerItem.setOnClickListener {
                val position = adapterPosition
                //Log.i("colorpick####",items[position].toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemview :View = LayoutInflater.from(context).inflate(R.layout.color_picker_item,parent,false)

        return VH(itemview)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.colorPickerItem.setColorFilter(ContextCompat.getColor(context, items[position]))
        holder.binding.colorPickerItem.setOnClickListener {
            var intent = Intent(context,SetGoalActivity::class.java)
            intent.putExtra("color",items[position])
            context.startActivity(intent)
            //Log.i("color####",items[position].toString())
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}