package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.data.model.PresetItem
import com.ezralee.bdotodo.data.repository.goal.GoalRepo
import com.ezralee.bdotodo.data.repository.goal.PresetDB

class GoalPresetVM(application: Application): AndroidViewModel(application) {

    private val repository = GoalRepo(application)
    private val db = Room.databaseBuilder(application, PresetDB::class.java, "goalPreset")
                         .allowMainThreadQueries()
                         .build()

    var _presets = MutableLiveData<PresetItem>()
    val presets: LiveData<PresetItem> get() = _presets

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