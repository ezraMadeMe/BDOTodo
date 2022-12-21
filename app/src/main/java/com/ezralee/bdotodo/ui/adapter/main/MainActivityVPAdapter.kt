package com.ezralee.bdotodo.ui.adapter.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment

class MainActivityVPAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val content: List<Fragment>
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {

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