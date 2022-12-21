package com.ezralee.bdotodo.viewmodel

import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.data.Util.Info

class GoalVM(private val contract: GoalFragmentContract) {
    interface GoalFragmentContract {

    }

    val adapter

    val today = Info.date

    fun createGoal(){

    }

    fun cancel(){

    }

    fun selectPreset(){

    }

    fun colorPick(){

    }

    fun setCategory(){

    }

    fun deletePlan(){

    }

    fun addPlan(){

    }

    fun deleteTask(){

    }

    fun addTask(){

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