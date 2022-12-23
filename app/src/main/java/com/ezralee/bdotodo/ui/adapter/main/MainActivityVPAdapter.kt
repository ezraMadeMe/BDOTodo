package com.ezralee.bdotodo.ui.adapter.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment

class MainActivityVPAdapter(
    fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DailyFragment()
            1 -> HistoryFragment()
            2 -> GoallistFragment()
            else -> error("No Fragment")
        }
    }
}