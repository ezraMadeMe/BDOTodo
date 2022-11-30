package com.ezralee.bdotodo.goallist

import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class GoalViewPagerAdapter(var fragmentManager: FragmentManager, lifecycle: Lifecycle, var context: Context) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = fragmentManager.fragments.size

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0, 1 -> (context as SetGoalActivity).items[position]
            else -> SetGoalFragment2()
        }
    }
}