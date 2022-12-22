package com.ezralee.bdotodo.ui.adapter.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import com.ezralee.bdotodo.data.model.HistoryData

class HistoryAdapter(var context: Context, var items: MutableList<HistoryData>) :
    RecyclerView.Adapter<HistoryAdapter.VH>() {
    interface OnItemClickListener {
        fun onItemClick(v: View?, p: Int)
    }
    interface OnItemLongClickListener {
        fun onItemLongClick(v: View?, p: Int)
    }
    // 리스너 객체 참조를 저장하는 변수
    private var mListener: OnItemClickListener? = null

    // OnItemClickListener 객체 참조를 어댑터에 전달하는 메서드
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    inner class VH(private val binding: HistoryRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //val binding: HistoryRecyclerItemBinding = HistoryRecyclerItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        mListener?.onItemClick(v, position)
                    }
                }
            })
        }

        fun bind(item: HistoryData){
            with(binding){
                historyData = item
                executePendingBindings()
            }
        }

    }//inner class VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding =
            HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.historyRecyclerDatasRight.adapter = InnerHistoryAdapter(context, items)
        holder.binding.historyRecyclerDatasLeft.adapter = InnerHistoryAdapter(context, items)
        holder.binding.historyRecyclerDateLeft.text = items[position].date
        holder.binding.historyRecyclerDateRight.text = items[position].date

        when (position % 2) {
            0 -> {
                holder.binding.historyRecyclerDatasRight.visibility = View.INVISIBLE
                holder.binding.historyRecyclerDateRight.visibility = View.INVISIBLE
                holder.binding.lineRight.visibility = View.INVISIBLE
            }
            1 -> {
                holder.binding.historyRecyclerDatasLeft.visibility = View.INVISIBLE
                holder.binding.historyRecyclerDateLeft.visibility = View.INVISIBLE
                holder.binding.lineLeft.visibility = View.INVISIBLE

            }
            else -> {
                holder.binding.historyRecyclerDateRight.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @BindingAdapter("imgUrl")
    fun loadHistoryImage(view: ImageView, imgUrl: String){
        Glide.with(view.context)
            .load(imgUrl)
            .error(R.drawable.img_sample)
            .into(view)
    }
}