package com.ezralee.bdotodo.viewmodel

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.ui.adapter.main.MainActivityVPAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment

class MainActivityVM(val contract: MainActivityContract) {
    interface MainActivityContract {
        fun getFragmentManager() : FragmentManager
        fun getLifecycle() : Lifecycle
    }

    var adapter = MainActivityVPAdapter(
        contract.getFragmentManager(),
        contract.getLifecycle(),
        listOf(DailyFragment.newInstance(),
        HistoryFragment.newInstance(),
        GoallistFragment.newInstance()
        )
    )

    fun setUserInfo(){

    }

    var currentPosition = 0

    var pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
//            super.onPageScrollStateChanged(state)
        }

        override fun onPageSelected(position: Int) {
            currentPosition = position
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
//            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    }

}