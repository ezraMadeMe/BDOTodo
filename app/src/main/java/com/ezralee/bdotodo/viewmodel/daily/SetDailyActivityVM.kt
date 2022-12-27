package com.ezralee.bdotodo.viewmodel.daily

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.AccureItem
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.model.PlanItem
import com.ezralee.bdotodo.data.model.TaskItem
import com.ezralee.bdotodo.data.repository.daily.DailyDB
import com.ezralee.bdotodo.data.repository.daily.DailyRepo

class SetDailyActivityVM(application: Application): AndroidViewModel(application) {
    private val repository = DailyRepo(application)
    private val db = Room.databaseBuilder(application, DailyDB::class.java, "dailyList")
                         .allowMainThreadQueries()
                         .build()

    //종속성을 가지는 데이터 객체
    var _goalItem = MutableLiveData<GoalItem>()
    val goalITem: LiveData<GoalItem> get() = _goalItem

    var _planList = MutableLiveData<PlanItem>()
    val planList: LiveData<PlanItem> get() = _planList

    var _taskList = MutableLiveData<TaskItem>()
    val taskList: LiveData<TaskItem> get() = _taskList

    var _accureList = MutableLiveData<AccureItem>()
    val accureList: LiveData<AccureItem> get() = _accureList

    init {

    }

    companion object {

    }
}