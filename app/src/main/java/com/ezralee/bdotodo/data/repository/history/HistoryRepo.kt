package com.ezralee.bdotodo.data.repository.history

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData

class HistoryRepo(application: Application) {

    private var historyDAO: HistoryDAO
    private var historyData: MutableLiveData<List<HistoryData>>

    init {
        var db : HistoryDB = HistoryDB.getInstance(application)!!
        historyDAO = db.hisDAO()
        historyData = db.hisDAO().getAll(KakaoLogin.USER_ID)
    }

    fun getAll(userId: String): MutableLiveData<List<HistoryData>> {
        return historyDAO.getAll(userId)
    }
    fun insert(history: HistoryData){
        historyDAO.insert(history)
    }
    fun update(history: HistoryData){
        historyDAO.update(history)
    }
    fun delete(history: HistoryData){
        historyDAO.delete(history)
    }
    fun filter(search: String, keyword: String): MutableLiveData<List<HistoryData>> {
        return historyDAO.filter(search, keyword)
    }
}