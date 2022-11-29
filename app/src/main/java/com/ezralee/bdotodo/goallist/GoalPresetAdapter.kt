package com.ezralee.bdotodo.goallist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.main.GoalPresetItem
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityGoalPresetItemBinding
import com.ezralee.bdotodo.main.KakaoLogin
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class GoalPresetAdapter(var context: Context, var items: MutableList<GoalPresetItem>):Adapter<GoalPresetAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView){
        val binding: ActivityGoalPresetItemBinding = ActivityGoalPresetItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var view: View = LayoutInflater.from(context).inflate(R.layout.activity_goal_preset_item,parent,false)

        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.goalName.text = items[position].title
    }

    override fun getItemCount(): Int {
        return items.size
    }
}