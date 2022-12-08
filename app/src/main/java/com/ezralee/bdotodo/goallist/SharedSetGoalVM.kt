package com.ezralee.bdotodo.goallist

import android.app.Application
import androidx.lifecycle.*

class SharedSetGoalVM(application: Application, val savedStateHandle: SavedStateHandle): AndroidViewModel(application) {
    val mutableData = MutableLiveData<String>()
    val selectedData: LiveData<String> get() = mutableData

    fun setSelectedData(str: String) {
        //여기서 postValue는 Asynctask로 백그라운드에서 유저가 클릭한 Subject가 무엇인지
        // mutableSelctedSubject 라이브데이터에 업데이트시켜줍니다.
        //이렇게 하면 MainActivity에서 Observe가 가능해집니다.
        mutableData.postValue(str)
    }
}