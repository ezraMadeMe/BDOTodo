package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.GoalRepo

class GoalPresetActivityVM(application: Application) : AndroidViewModel(application) {

    private val repository = GoalRepo(application)
    private val db = Room.databaseBuilder(application, GoalDB::class.java, "goalList")
                         .allowMainThreadQueries()
                         .build()

    class Factory(val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GoalPresetActivityVM(application) as T
        }
    }

    //프리셋 리스트 리사이클러뷰 아답터
    val recyclerAdapter

    //뷰페이저 아답터
    val vpAdapter

    //선택한 프리셋에 해당하는 세부 정보가 입력된 목표가 하나 새로 생성됨(HistoryFragment로 이동)
    fun getPresetData(preset: String) {

    }
}