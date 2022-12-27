package com.ezralee.bdotodo.ui.adapter.goal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezralee.bdotodo.ui.fragment.goal.GoalPresetListFragment

class PresetVPAdapter(
    var items: List<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GoalPresetListFragment.newInstance("보물")
            1 -> GoalPresetListFragment.newInstance("생활")
            2 -> GoalPresetListFragment.newInstance("장비")
            else -> error("error")
        }
    }
}