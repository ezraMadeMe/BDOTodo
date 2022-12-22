package com.ezralee.bdotodo.viewmodel.goal

import androidx.viewpager2.widget.ViewPager2

class SetGoalActivityVM {

    val adapter

    //SetGoalActivity의 Fragment에서 작성한 데이터 저장
    fun createGoal(){

    }

    //Activity 종료
    fun cancel(){

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