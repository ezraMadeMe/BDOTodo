package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ezralee.bdotodo.data.model.GoalData
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.GoalRepo
import com.ezralee.bdotodo.viewmodel.Event

class SetGoalFragment1VM(val contract: SEtGoalFragment1Contract, application: Application)
    : AndroidViewModel(application) {

    interface SEtGoalFragment1Contract{

    }

    private val repository = GoalRepo(application)
    private val db = Room.databaseBuilder(application, GoalDB::class.java, "goalList")
                         .allowMainThreadQueries()
                         .build()
    private val _goalList = MutableLiveData<List<GoalData>>()
    val goalList: LiveData<List<GoalData>> get() = _goalList

    private val _openEvent = MutableLiveData<Event<GoalData>>()
    val openEvent: LiveData<Event<GoalData>> get() = _openEvent

    val data: MutableLiveData<GoalData> = MutableLiveData()

    fun colorPick(){

    }

    val today

    fun datePick(){

    }

    fun setCategory(){

    }
}