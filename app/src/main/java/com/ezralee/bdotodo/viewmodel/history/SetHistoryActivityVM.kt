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
    private val db = Room.databaseBuilder(application, HistoryDB::class.java, "goalList")
        .allowMainThreadQueries()
        .build()

    init {

    }

}