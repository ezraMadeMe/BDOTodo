package com.ezralee.bdotodo.viewmodel.history

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.data.repository.history.HistoryRepo
import com.ezralee.bdotodo.ui.adapter.BindAdapter
import com.ezralee.bdotodo.viewmodel.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response


// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
//뷰와 Repository(Model) 사이의 인터페이스, 데이터바인딩 전달하여 뷰를 그리기 위한 데이터 처리

class MainHistoryVM(val contract: MainHistoryContract, application: Application): AndroidViewModel(application) {

    interface MainHistoryContract{
        fun getFragmentManager(): FragmentManager
    }

    private val repository = HistoryRepo(application)
    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "historyList")
                         .allowMainThreadQueries()
                         .build()

    var recyclerAdapter = BindAdapter(
        id = R.layout.fragment_history,
        items = listOf<HistoryData>(),
        listener = this
    )

    private val _historyList = MutableLiveData<List<HistoryData>>()
    val historyList : LiveData<List<HistoryData>> get() = _historyList

    //이벤트 처리용 객체
    private val _openEvent = MutableLiveData<Event<HistoryData>>()
    val openEvent: LiveData<Event<HistoryData>> get() = _openEvent
    val data: MutableLiveData<HistoryData> = MutableLiveData()
    //XML 데이터바인딩 함수
    fun onClickEvent(data: HistoryData) {
        _openEvent.value = Event(data)
    }

    // 리스트를 받아서 뷰에 표시해줌
    fun updateHistoryList(historyData : List<HistoryData>){
        var title: String
        var date: String
        var category: String
        var image: String
        var memo: String
        CoroutineScope(Dispatchers.Main).launch {
            val load = async(Dispatchers.IO) {
                for(i in historyData){
                    title = i.title
                    date = i.date
                    category = i.category
                    image = i.imgUrl
                    memo = i.memo
                }
            }
            load.await()
        }
    }

    fun getAll() : LiveData<List<HistoryData>> {
        return repository.getAll(KakaoLogin.USER_ID)
    }

    // history 필터
    fun filter(search: String, keyword: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.filter(search, keyword)
        }
    }

    // history 삭제
    fun delete(historyData: HistoryData){
        CoroutineScope(Dispatchers.IO).launch {
            repository.delete(historyData)
        }
    }
}