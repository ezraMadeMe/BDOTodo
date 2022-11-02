package com.ezralee.bdotodo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3 //menu 개수

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> DailyFragment()
            1 -> HistoryFragment()
            2 -> GoallistFragment()
            else -> error("No Fragment")
        }
    }

}