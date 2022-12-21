package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityGoalPresetItemBinding
import com.ezralee.bdotodo.main.GoalItem
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class GoalPresetAdapter(var context: Context, var items: MutableList<GoalItem>) :
    RecyclerView.Adapter<GoalPresetAdapter.VH>() {

    //goalPreset 컬렉션
    private val goalPreset: CollectionReference =
        FirebaseFirestore.getInstance().collection("goalPreset")

    //goalPreset 컬렉션 안의 보물/생활/장비 document 리스트
    private val preset: Task<QuerySnapshot> =
        goalPreset.get()

    //
    lateinit var category: MutableList<QueryDocumentSnapshot>

    inner class VH(itemView: View) : ViewHolder(itemView) {
        val binding: ActivityGoalPresetItemBinding = ActivityGoalPresetItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var view: View =
            LayoutInflater.from(context).inflate(R.layout.activity_goal_preset_item, parent, false)

        preset
            .addOnSuccessListener { result ->
                Log.i("result",result.size().toString())
                for (document in result) {
                    category.add(document)
                    Log.i("result",document.get("goal").toString())
                }
            }
            .addOnFailureListener {
                AlertDialog.Builder(context).setMessage(it.message).create()
            }

        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        //프리셋-카테고리(보물 생활 장비)별 필드 목록 불러오기
        goalPreset.document("goalPreset/${category[position]}").get().addOnCompleteListener {
            if (it.isSuccessful) {

            }
        }

        holder.binding.goalName.text = items[position].goal
    }

    override fun getItemCount(): Int {
        return items.size
    }
}