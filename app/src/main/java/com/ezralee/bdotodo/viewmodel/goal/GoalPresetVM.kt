package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2

class GoalPresetVM(application: Application): AndroidViewModel(application) {

    init {

    }

    var currentItem = 0

    fun createPreset(position: Int){

    }

    //현재 페이지
    var currentPage = 0

    var pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            currentPage = position
        }
    }
}