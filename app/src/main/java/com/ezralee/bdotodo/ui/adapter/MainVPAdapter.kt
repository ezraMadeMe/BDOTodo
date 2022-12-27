package com.ezralee.bdotodo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment

class MainVPAdapter(fragmentManager: FragmentManager,
                    lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments = listOf(
        DailyFragment.newInstance(),
        HistoryFragment.newInstance(),
        GoallistFragment.newInstance()
    )

    private val tabs: Int = fragments.size

    override fun getItemCount(): Int {
        return tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragments[0]
            1 -> fragments[1]
            2 -> fragments[2]
            else -> error("error")
        }
    }
}