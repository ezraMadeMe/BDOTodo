package com.ezralee.bdotodo.goallist

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class GoalViewPagerAdapter(var items: MutableList<Fragment>, var context: Context, var fm: FragmentManager, var ls: Lifecycle) : FragmentStateAdapter(fm, ls) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> SetGoalFragment1()
            1 -> SetGoalFragment2()
            else -> SetGoalFragment2()
        }
    }
}