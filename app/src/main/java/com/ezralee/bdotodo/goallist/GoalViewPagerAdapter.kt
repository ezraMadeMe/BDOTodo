package com.ezralee.bdotodo.goallist

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class GoalViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, var context: Context) : FragmentStateAdapter(fragmentManager, lifecycle) {

    var items: MutableList<Fragment> = mutableListOf(SetGoalFragment1(),SetGoalFragment2())

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> SetGoalFragment1()
            else -> SetGoalFragment2()
        }
    }

    fun addItem(){
        items.add(SetGoalFragment2())
        notifyDataSetChanged()
    }

//    override fun getItemId(position: Int): Long {
//        return items[position] as Long
//    }
//
//    override fun containsItem(itemId: Long): Boolean {
//        return items.map { it.hashCode().toLong() }.contains(itemId)
//    }
}