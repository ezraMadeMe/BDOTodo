package com.ezralee.bdotodo.ui.adapter.goal

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2

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