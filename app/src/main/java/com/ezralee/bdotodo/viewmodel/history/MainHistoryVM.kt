package com.ezralee.bdotodo.viewmodel.history

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.room.Room
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.Util.RetrofitHelper
import com.ezralee.bdotodo.data.Util.RetrofitService
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.data.repository.history.HistoryRepo
import com.ezralee.bdotodo.ui.adapter.history.HistoryAdapter
import com.ezralee.bdotodo.viewmodel.main.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
//뷰와 Repository(Model) 사이의 인터페이스, 데이터바인딩 전달하여 뷰를 그리기 위한 데이터 처리

class MainHistoryVM(application: Application) : AndroidViewModel(application) {

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainHistoryVM(application) as T
        }
    }

    private val repository = HistoryRepo(application)
    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "historyList")
                         .allowMainThreadQueries()
                         .build()

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


    val recyclerAdapter = HistoryAdapter()

    val recyclerInnerAdapter

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

    //날짜별로 데이터를 묶어오는 거.... GET으로 userId 전송이 안됨...
    fun loadData() {
        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        var call: Call<MutableList<HistoryData>> = retrofitService.loadHistoryDateFromServer(
            KakaoLogin.USER_ID
        )

        call.enqueue(object : Call<MutableList<HistoryData>>, Callback<MutableList<HistoryData>> {
            override fun onResponse(
                call: Call<MutableList<HistoryData>>,
                response: Response<MutableList<HistoryData>>
            ) {
                Log.i("size####", "" + items.size)
                Log.i("title####", items[0].title)

                items.clear()
                //ConcurrentModificationException 오류
                binding.historyRecycler.adapter?.notifyDataSetChanged()

                var responseItems: MutableList<HistoryData> = response.body()!!
                Toast.makeText(activity, "" + items.size, Toast.LENGTH_SHORT).show()
                for (item: HistoryData in responseItems) {
                    items.add(0, item)
                    binding.historyRecycler.adapter?.notifyItemInserted(0)
                }
            }

            override fun onFailure(call: Call<MutableList<HistoryData>>, t: Throwable) {
                //Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                Log.i("failure####", t.message.toString())
                Log.i("send####", call.isExecuted.toString())
            }

            override fun clone(): Call<MutableList<HistoryData>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<MutableList<HistoryData>> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<MutableList<HistoryData>>) {
                TODO("Not yet implemented")
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        })
    }
}