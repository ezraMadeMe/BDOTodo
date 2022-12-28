package com.ezralee.bdotodo.viewmodel.history

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.data.repository.history.HistoryRepo
import com.ezralee.bdotodo.viewmodel.Event

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