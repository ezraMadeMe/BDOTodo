package com.ezralee.bdotodo.data.repository.history

import android.app.Application
import androidx.lifecycle.LiveData
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData

class HistoryRepo(application: Application) {
    private val historyDB: HistoryDB = HistoryDB.getInstance(application)!!
    private val historyDAO: HistoryDAO = historyDB.hisDAO()

    fun getAllHistory(): LiveData<List<HistoryData>> {
        return historyDAO.getHistory(KakaoLogin.USER_ID)
    }
    fun insertHistory(history: HistoryData){
        historyDAO.insertHistory(history)
    }
    fun deleteHistory(history: HistoryData){
        historyDAO.deleteHistory(history)
    }

    fun updateHistory(history: HistoryData){
        historyDAO.updateHistory(history)
    }
}