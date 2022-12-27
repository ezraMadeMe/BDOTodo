package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.*
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.GoalRepo

class MainGoalVM(application: Application): AndroidViewModel(application) {

    private val repository = GoalRepo(application)
    private val db = Room.databaseBuilder(application, GoalDB::class.java, "goalList")
                         .allowMainThreadQueries()
                         .build()

    //종속성을 가지는 데이터 객체
    var _goalItem = MutableLiveData<GoalItem>()
    val goalITem: LiveData<GoalItem> get() = _goalItem

    var _planList = MutableLiveData<PlanItem>()
    val planList: LiveData<PlanItem> get() = _planList

//    var _taskList = MutableLiveData<TaskItem>()
//    val taskList: LiveData<TaskItem> get() = _taskList

    //독립된 goal plan task 객체
    var _goalData = MutableLiveData<GoalData>()
    val goalData: LiveData<GoalData> get() = _goalData

    var _planData = MutableLiveData<PlanData>()
    val planData: LiveData<PlanData> get() = _planData

    var _taskData = MutableLiveData<TaskData>()
    val taskData: LiveData<TaskData> get() = _taskData

    // VP
    private var _fragments = MutableLiveData<ArrayList<Fragment>>()
    val fragments: LiveData<ArrayList<Fragment>> get() = _fragments

    // task 리사이클러뷰
    var _tasks = MutableLiveData<List<TaskData>>()
    val tasks: LiveData<List<TaskData>> get() = _tasks

    val today = Info.date
    var isGone: Boolean

    init {
        db.goalDAO().getGoalItem(KakaoLogin.USER_ID).value.apply {
            _goalItem.value
        }
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