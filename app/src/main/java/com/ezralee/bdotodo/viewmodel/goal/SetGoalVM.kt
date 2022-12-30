package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.data.model.*
import com.ezralee.bdotodo.data.repository.goal.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.goal.GoalRepo
import com.ezralee.bdotodo.data.repository.goal.plan.PlanRepo
import com.ezralee.bdotodo.data.repository.goal.task.TaskRepo
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class SetGoalVM(application: Application): AndroidViewModel(application) {

    private val goalRepo = GoalRepo(application)
    private val planRepo = PlanRepo(application)
    private val taskRepo = TaskRepo(application)

    private val db = Room.databaseBuilder(application, GoalDB::class.java, "goalList")
                         .allowMainThreadQueries()
                         .build()

    //종속성을 가지는 데이터 객체
    private var _goalItem = MutableLiveData<GoalData>()
    val goalItem: LiveData<GoalData> get() = _goalItem

    private var _planList = MutableLiveData<List<PlanData>>()
    val planList: LiveData<List<PlanData>> get() = _planList

    private var _taskList = MutableLiveData<List<TaskData>>()
    val taskList: LiveData<List<TaskData>> get() = _taskList

    // VP
    private var _fragments = MutableLiveData<ArrayList<Fragment>>()
    val fragments: LiveData<ArrayList<Fragment>> get() = _fragments

    init {
        //VP 아이템 초기화
        _fragments.value = FRAG_ITEMS
        //task 리사이클러 아이템 초기화
        _taskList.value = TASK_ITEMS
    }

    //완료 버튼
    fun createGoal(goalData: GoalData, planList: List<PlanData>, taskList: List<TaskData>){
        CoroutineScope(Dispatchers.IO).launch {
            goalRepo.insertGoal(goalData)
            planRepo.insertPlan(planList)
            taskRepo.insertTask(taskList)
        }
    }

    fun setStart(){

    }

    fun setEnd(){

    }

    //and or 설정
    fun setNor(){

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
        _taskList.value = TASK_ITEMS
    }
    //task 삭제
    fun deleteTask(position: Int){
        if (TASK_ITEMS.size <= 2){
            Toast.makeText(getApplication(), "달성방법은 하나 이상 생성해야 합니다.", Toast.LENGTH_SHORT).show()
        } else {
            TASK_ITEMS.removeAt(position)
            _taskList.value = TASK_ITEMS
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