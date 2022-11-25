package com.ezralee.bdotodo.goallist

import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class GoalViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, var context: Context) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = (context as SetGoalActivity).items.size

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0, 1 -> (context as SetGoalActivity).items[position]
            else -> SetGoalFragment2()
        }
    }


//    fun addItem(){
//        items.add(SetGoalFragment2())
//        notifyDataSetChanged()
//    }
}