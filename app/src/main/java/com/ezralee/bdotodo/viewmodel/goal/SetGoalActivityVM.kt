package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.model.PlanItem
import com.ezralee.bdotodo.data.model.TaskData
import com.ezralee.bdotodo.data.model.TaskItem
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.GoalRepo
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2
import com.ezralee.bdotodo.viewmodel.Event

class SetGoalActivityVM(application: Application): AndroidViewModel(application) {

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

    // VP 포지션
    private val _fragments = MutableLiveData<ArrayList<Fragment>>()
    private val _tasks = MutableLiveData<ArrayList<TaskData>>()

    val fragments: LiveData<ArrayList<Fragment>> get() = _fragments
    val tasks: LiveData<ArrayList<TaskData>> get() = _tasks


    init {
        _fragments.apply {
            value = FRAG_ITEMS
        }
        _tasks.apply {
            value = TASK_ITEMS
        }
    }

    fun setStart(){

    }

    fun setEnd(){

    }

    val nor = false

    fun setNor(isNor: Boolean){
        if (isNor){

        }
    }

    var currentPage = 0

    //fragment 추가
    fun addPage(){
        FRAG_ITEMS.add(SetGoalFragment2.newInstance())
        _fragments.value = FRAG_ITEMS
    }
    //fragment 삭제
    fun deletePage(position: Int){
        if (FRAG_ITEMS.size <= 2){
            Toast.makeText(getApplication(), "소목표는 하나 이상 생성해야 합니다.", Toast.LENGTH_SHORT).show()
        } else {
            FRAG_ITEMS.removeAt(position)
            _fragments.value = FRAG_ITEMS
        }
    }

    //task 추가
    fun addTask(){
        TASK_ITEMS.add(TaskData())
        _tasks.value = TASK_ITEMS
    }
    //task 삭제
    fun deleteTask(position: Int){
        if (TASK_ITEMS.size <= 2){
            Toast.makeText(getApplication(), "달성방법은 하나 이상 생성해야 합니다.", Toast.LENGTH_SHORT).show()
        } else {
            TASK_ITEMS.removeAt(position)
            _tasks.value = TASK_ITEMS
        }
    }

    companion object {
        //fragment 개수
        private val FRAG_ITEMS = arrayListOf(
            SetGoalFragment1() as Fragment,
            SetGoalFragment2() as Fragment
        )
        //초기 task 개수
        private val TASK_ITEMS = arrayListOf( TaskData() )
    }
}