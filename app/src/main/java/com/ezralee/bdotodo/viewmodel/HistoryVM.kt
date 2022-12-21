package com.ezralee.bdotodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB

class HistoryVM(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "historyList")
                         .allowMainThreadQueries()
                         .build()

    fun getHistory(): LiveData<List<HistoryData>> {
        return db.hisDAO().getHistory(KakaoLogin.USER_ID)
    }

    fun insertHistory(historyData: HistoryData) {
        db.hisDAO().insertHistory(historyData)
    }

    fun updateHistory(historyData: HistoryData) {
        db.hisDAO().updateHistory(historyData)
    }

    fun deleteHistory(historyData: HistoryData) {
        db.hisDAO().deleteHistory(historyData)
    }

    fun setToday(){

    }

    fun setImage(){

    }

    fun deleteImage(){

    }

    fun done(){

    }

    fun cancel(){

    }

    fun edit(){

    }

}