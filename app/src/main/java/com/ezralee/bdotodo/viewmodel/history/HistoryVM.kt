package com.ezralee.bdotodo.viewmodel.history

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.data.repository.history.HistoryRepo
import com.ezralee.bdotodo.viewmodel.Event

// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
//뷰와 Repository(Model) 사이의 인터페이스, 데이터바인딩 전달하여 뷰를 그리기 위한 데이터 처리

class HistoryVM(application: Application): AndroidViewModel(application) {

    private val repository = HistoryRepo(application)
    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "historyList")
                         .allowMainThreadQueries()
                         .build()

    private val _historyList = MutableLiveData<List<HistoryData>>()
    val historyList : LiveData<List<HistoryData>> get() = _historyList

    var currentHistory: MutableLiveData<HistoryData> = MutableLiveData()

    init {
        _historyList.value = repository.getAll(KakaoLogin.USER_ID).value
    }

}