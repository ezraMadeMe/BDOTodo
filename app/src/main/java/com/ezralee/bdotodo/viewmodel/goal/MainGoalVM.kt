package com.ezralee.bdotodo.viewmodel.goal

import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.data.Util.Info

class MainGoalVM(private val contract: GoalFragmentContract) {
    interface GoalFragmentContract {

    }

    //최상위 리사이클러뷰 아답터
    val goalAdapter

    //소목표단 리사이클러뷰 아답터
    val planAdapter

    //task단 리사이클러뷰 아답터
    val taskAdapter

    val today = Info.date

    fun showDetail(){

    }

    fun arrowAnimate(){

    }

    //메인 FAB 클릭시 작은 FAB 2개 팝업
    fun popupFAB(){

    }

    fun openPreset(){

    }

    fun openMyself(){

    }

    fun colorPick(){

    }

    fun setCategory(){

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