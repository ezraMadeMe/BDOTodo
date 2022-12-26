package com.ezralee.bdotodo.viewmodel.main

import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.ezralee.bdotodo.ui.adapter.MainVPAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment

class MainActivityVM(val contract: MainActivityContract) {

    interface MainActivityContract {
        fun getFragmentManager(): FragmentManager
    }

    var adapter = MainVPAdapter(
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