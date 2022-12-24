package com.ezralee.bdotodo.viewmodel.goal

import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.GoalPreset
import com.ezralee.bdotodo.ui.adapter.BindAdapter
import com.ezralee.bdotodo.ui.adapter.ViewPagerAdapter
import com.ezralee.bdotodo.ui.fragment.goal.GoalPresetListFragment

class GoalPresetActivityVM(val contract: GoalPresetContract) {

    interface GoalPresetContract{
        fun getFragmentManager(): FragmentManager
    }

    /////////////////////////////////////////////////// 뷰페이저
    var viewPagerAdapter = ViewPagerAdapter(
        contract.getFragmentManager(),
        listOf("보물","생활", "장비"),
        listOf(
            GoalPresetListFragment.newInstance("보물"),
            GoalPresetListFragment.newInstance("생활"),
            GoalPresetListFragment.newInstance("장비")
        )
    )

    var currentPosition = 0

    var pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            TODO("Not yet implemented")
        }

        override fun onPageSelected(position: Int) {
            currentPosition = position
        }

        override fun onPageScrollStateChanged(state: Int) {
            TODO("Not yet implemented")
        }

    }


}