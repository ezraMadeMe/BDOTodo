package com.ezralee.bdotodo.viewmodel.main

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.ui.adapter.ViewPagerAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment
import com.ezralee.bdotodo.viewmodel.history.SetHistoryActivityVM
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityVM(val contract: MainActivityContract) {

    interface MainActivityContract {
        fun getFragmentManager(): FragmentManager
    }

    var adapter = ViewPagerAdapter(
        contract.getFragmentManager(),
        listOf("할 일", "히스토리", "목표"),
        listOf(
            DailyFragment.newInstance(),
            HistoryFragment.newInstance(),
            GoallistFragment.newInstance()
        )
    )

    var currentPosition = 0

    var pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            //
        }

        override fun onPageSelected(position: Int) {
            currentPosition = position
        }

        override fun onPageScrollStateChanged(state: Int) {
            //
        }
    }
}