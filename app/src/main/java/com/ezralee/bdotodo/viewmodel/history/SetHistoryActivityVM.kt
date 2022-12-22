package com.ezralee.bdotodo.viewmodel.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.data.repository.history.HistoryRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetHistoryActivityVM(application: Application) : AndroidViewModel(application) {

    private val repository = HistoryRepo(application)

    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "historyList")
        .allowMainThreadQueries()
        .build()

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SetHistoryActivityVM(application) as T
        }
    }

    // 새로운 유저정보 추가시 옵저버가 감지하여 updateUserList 함수를 호출하기 때문에 자동으로 뷰 갱신
    fun insert(historyData: HistoryData){
        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(historyData)
        }
    }

    // history 갱신
    fun update(historyData: HistoryData){

        CoroutineScope(Dispatchers.IO).launch {
            repository.update(historyData)
        }
    }

    fun onLanguageSpinnerItemSelected(){

    }

    fun setToday(){

    }

    fun setImage(){

    }

    fun deleteImage(){

    }

    fun done(historyData: HistoryData){

    }

    fun cancel(){

    }


}