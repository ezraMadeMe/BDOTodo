package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.data.model.*
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.GoalRepo
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2

open class SetGoalActivityVM(application: Application): AndroidViewModel(application) {

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

    init {
        //andor 토글버튼 초기화
        _goalData.value?.andor = false
        //VP 아이템 초기화
        _fragments.value = FRAG_ITEMS
        //task 리사이클러 아이템 초기화
        _tasks.value = TASK_ITEMS
    }

    //완료 버튼
    fun createGoal(){

    }

    fun setStart(){

    }

    fun setEnd(){

    }

    //and or 설정
    fun setNor(){
        _goalData.value?.andor = !(_goalData.value?.andor)!!
    }

    //선택된 카테고리
    var selectedCategory: Int = 0

    fun setCategory(){

    }

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

    //SetGoal VP 페이지 리스너
    var currentPage = 0

    var pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            currentPage = position
        }
    }

    //최초생성 frag tsk 개수
    companion object {
        //fragment 개수
        private val FRAG_ITEMS = arrayListOf(
            SetGoalFragment1.newInstance(),
            SetGoalFragment2.newInstance()
        )
        //초기 task 개수
        private val TASK_ITEMS = arrayListOf(
            TaskData()
        )
    }
}