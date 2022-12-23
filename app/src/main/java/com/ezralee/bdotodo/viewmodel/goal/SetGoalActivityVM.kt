package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.GoalRepo
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2
import com.ezralee.bdotodo.viewmodel.main.Event

class SetGoalActivityVM(application: Application): AndroidViewModel(application) {

    class Factory(val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SetGoalActivityVM(application) as T
        }
    }

    private val repository = GoalRepo(application)
    private val db = Room.databaseBuilder(application, GoalDB::class.java, "goalList")
                         .allowMainThreadQueries()
                         .build()

    private val _goalList = MutableLiveData<List<GoalItem>>()
    val goalList: LiveData<List<GoalItem>> get() = _goalList

    //이벤트 처리용 객체
    private val _openEvent = MutableLiveData<Event<GoalItem>>()
    val openEvent: LiveData<Event<GoalItem>> get() = _openEvent
    val data: MutableLiveData<GoalItem> = MutableLiveData()

    //XML 데이터바인딩 함수
    fun onClickEvent(data: GoalItem) {
        _openEvent.value = Event(data)
    }

    val adapter

    //SetGoalActivity의 Fragment에서 작성한 데이터 저장
    fun createGoal(){

    }

    //Activity 종료
    fun cancel(){

    }

    // VP 포지션
    val fragments: LiveData<List<Fragment>> get() = _fragments
    val position: LiveData<Int> get() = _position

    private val _fragments: MutableLiveData<List<Fragment>> = MutableLiveData()
    private val _position: MutableLiveData<Int> = MutableLiveData()

    companion object {
        private val FRAG_ITEMS = listOf(
            SetGoalFragment1(),SetGoalFragment2()
        )
    }

    init {
        _fragments.postValue(FRAG_ITEMS)
    }

    fun selectPosition(position: Int) {
        _position.postValue(position)
    }
}