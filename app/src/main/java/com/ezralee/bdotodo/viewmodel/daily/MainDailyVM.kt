package com.ezralee.bdotodo.viewmodel.daily

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.TaskAccureData
import com.ezralee.bdotodo.data.repository.goal.daily.DailyDB
import com.ezralee.bdotodo.data.repository.goal.daily.DailyRepo

class MainDailyVM(application: Application): AndroidViewModel(application) {

    private val dailyRepo = DailyRepo(application)

    private val db = Room.databaseBuilder(application, DailyDB::class.java, "accureList")
        .allowMainThreadQueries()
        .build()

    private var _accureList = MutableLiveData<List<TaskAccureData>>()
    val accureList: LiveData<List<TaskAccureData>> get() = _accureList

    init {
        _accureList.value = ACCURE_ITEMS
    }


    fun deleteData(position: Int){
        ACCURE_ITEMS.removeAt(position)
        _accureList.value = ACCURE_ITEMS
    }

    companion object {
        private val ACCURE_ITEMS = arrayListOf<TaskAccureData>()
    }
}