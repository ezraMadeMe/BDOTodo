package com.ezralee.bdotodo.data.repository.history

import android.app.Application
import androidx.lifecycle.LiveData
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData

// 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
//뷰모델은 DB에 직접 접근하지 않아야함

class HistoryRepo(application: Application) {

    private var historyDAO: HistoryDAO
    private var historyList: LiveData<List<HistoryData>>

    init {
        var db : HistoryDB = HistoryDB.getInstance(application)!!
        historyDAO = db.hisDAO()
        historyList = db.hisDAO().getAll(KakaoLogin.USER_ID)
    }

    fun getAll(userId: String): LiveData<List<HistoryData>> {
        return historyDAO.getAll(userId)
    }
    fun insert(history: HistoryData){
        historyDAO.insert(history)
    }
    fun delete(history: HistoryData){
        historyDAO.delete(history)
    }
    fun update(history: HistoryData){
        historyDAO.update(history)
    }
    fun filter(search: String, keyword: String): LiveData<List<HistoryData>> {
        return historyDAO.filter(search, keyword)
    }
}