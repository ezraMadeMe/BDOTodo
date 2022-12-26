package com.ezralee.bdotodo.viewmodel.history

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.data.repository.history.HistoryRepo
import com.ezralee.bdotodo.viewmodel.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
//뷰와 Repository(Model) 사이의 인터페이스, 데이터바인딩 전달하여 뷰를 그리기 위한 데이터 처리

class MainHistoryVM(application: Application): AndroidViewModel(application) {

    private val repository = HistoryRepo(application)
    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "historyList")
                         .allowMainThreadQueries()
                         .build()

    private val _historyList = MutableLiveData<List<HistoryData>>()
    val historyList : LiveData<List<HistoryData>> get() = _historyList

    init {
        _historyList.value = db.hisDAO().getAll(KakaoLogin.USER_ID).value
    }

    //이벤트 처리용 객체
    private val _openEvent = MutableLiveData<Event<HistoryData>>()
    val openEvent: LiveData<Event<HistoryData>> get() = _openEvent
    //XML 데이터바인딩 함수
    fun onClickEvent(data: HistoryData) {
        _openEvent.value = Event(data)
    }
}