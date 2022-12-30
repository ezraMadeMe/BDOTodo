package com.ezralee.bdotodo.data.repository.history

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData

// 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
//뷰모델은 DB에 직접 접근하지 않아야함

//Repository
//ViewModel과 데이터를 주고받기 위해, 데이터 API를 포함하는 클래스다.
// 앱에 필요한 데이터, 즉 내장 데이터베이스나 외부 웹 서버 등에서 데이터를 가져온다.
// 따라서 ViewModel은 DB나 서버에 직접 접근하지 않고, Repository에 접근하는 것으로
// 앱의 데이터를 관리한다. Repository의 존재 덕분에 ViewModel은 데이터를 관리할 필요가 없게 된다.

//LiveData
//View가 ViewModel을 관찰할 때, 그 관찰 대상이 되는 관찰 가능한(Observable) 데이터 홀더 클래스이다.
// View에서 ViewModel의 LiveData를 관찰하게 되면 데이터가 변경될 때 내부적으로 자동으로 알려주게 된다.
// Live Data는 Activity 및 Fragment의 생명주기를 인지한다.
// 즉, Activity가 화면 위에 활성화되어 있을 때만 UI변경 등의 기능을 동작하게 되고,
// Destroy되 상태에서는 동작하지 않기 때문에 메모리 릭의 발생을 줄여준다.

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