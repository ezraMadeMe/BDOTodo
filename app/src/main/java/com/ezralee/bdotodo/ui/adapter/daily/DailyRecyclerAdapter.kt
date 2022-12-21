package com.ezralee.bdotodo.ui.adapter.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class DailyRecyclerAdapter<T>(
    private val layouts: Map<Int, Int>,
    items: List<T>,
    private val listener: Any? = null
): RecyclerView.Adapter<DailyRecyclerAdapter.Holder>() {

    private val items = ObservableArrayList<T>().apply {
        addAll(items)
    }

    class Holder(val bind: ViewDataBinding) : RecyclerView.ViewHolder(bind.root)

    constructor(id: Int, items: List<T>, listener: Any? = null): this(mapOf(0 to id), items, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return LayoutInflater.from(parent.context).let {
            val id = layouts[viewType]!!
            val bind = DataBindingUtil.inflate<ViewDataBinding>(it, id,parent, false)
            return@let Holder(bind)
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind.setVariable(BR._all, getItemViewType(position))
        listener?.let { holder.bind.setVariable(BR._all, it) }
        holder.bind.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.itemAnimator = null
    }

    fun add(item: T) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun addAll(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun remove(item: T) {
        items.remove(item)
        notifyDataSetChanged()
    }

    fun replaceAll(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun getItems(): ObservableList<T> = items
}