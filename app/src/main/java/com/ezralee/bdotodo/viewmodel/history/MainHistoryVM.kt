package com.ezralee.bdotodo.viewmodel.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.data.repository.history.HistoryRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
//뷰와 Repository(Model) 사이의 인터페이스, 데이터바인딩 전달하여 뷰를 그리기 위한 데이터 처리

class MainHistoryVM(application: Application) : AndroidViewModel(application) {

    private val repository = HistoryRepo(application)
    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "historyList")
                         .allowMainThreadQueries()
                         .build()

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainHistoryVM(application) as T
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

    val historyAdapter

    val historyDetailAdapter

    // 특정 history 클릭시 해당 history의 상세정보창 팝업
    fun showDetailHistory(){

    }

    // historyFAB 클릭 시 history 생성창 팝업
    fun setHistory(){

    }

    // 검색어/카테고리/날짜를 통해 history 필터링 쿼리
    fun filterHistory(keyword: String){

    }

    //BottomSheetDialog 닫기
    fun cancelBSD(){

    }

    // textview를 오늘 날짜로 설정
    fun setToday(){

    }
}