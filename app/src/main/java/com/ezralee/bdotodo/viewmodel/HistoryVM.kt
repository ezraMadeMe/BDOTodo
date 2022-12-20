package com.ezralee.bdotodo.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HistoryVM(application: Application) : ViewModel() {

    private val repository = HistoryRepo(application)

    fun getAll(): LiveData<List<HistoryData>> {
        return repository.getAllHistory()
    }

    fun insert(history: HistoryData){
        repository.insertHistory(history)
    }
    fun delete(history: HistoryData){
        repository.deleteHistory(history)
    }
    fun updateHistory(history: HistoryData){
        repository.updateHistory(history)
    }

    override fun onCleared() {
        super.onCleared()
    }
}