package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.model.PlanItem
import com.ezralee.bdotodo.data.model.TaskItem
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.GoalRepo

class MainGoalVM(application: Application): AndroidViewModel(application) {

    private val repository = GoalRepo(application)
    private val db = Room.databaseBuilder(application, GoalDB::class.java, "goalList")
                         .allowMainThreadQueries()
                         .build()

    private val _goalList = MutableLiveData<List<GoalItem>>()
    val goalList : LiveData<List<GoalItem>> get() = _goalList

    private val _planList = MutableLiveData<List<PlanItem>>()
    val planList : LiveData<List<PlanItem>> get() = _planList

    private val _taskList = MutableLiveData<List<TaskItem>>()
    val taskList : LiveData<List<TaskItem>> get() = _taskList

    val today = Info.date
    var isGone: Boolean

    init {
        _goalList.value = db.goalDAO().getGoalItem(KakaoLogin.USER_ID).value
         isGone = false
    }

    fun showDetail(){

    }

    fun arrowAnimate(){

    }


    fun popupFAB() {
        isGone = !isGone
    }

    fun openPreset(){

    }

    fun openMyself(){

    }

    fun colorPick(){

    }
}