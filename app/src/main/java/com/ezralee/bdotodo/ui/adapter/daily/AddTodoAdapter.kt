package com.ezralee.bdotodo.ui.adapter.daily

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.main.AddTodoItem
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.AddTodoRecyclerItemBinding

class AddTodoAdapter(var context: Context, var items: MutableList<AddTodoItem>): Adapter<AddTodoAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView){
        var binding: AddTodoRecyclerItemBinding = AddTodoRecyclerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var view: View = LayoutInflater.from(context).inflate(R.layout.add_todo_recycler_item, parent, false)

        return VH(view.rootView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.todoRecyclerItemSubtitle.text = items[position].task
        holder.binding.dailyRecyclerItemDate.text = items[position].date
        holder.binding.dailyRecyclerItemCount.text = items[position].count.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }


}