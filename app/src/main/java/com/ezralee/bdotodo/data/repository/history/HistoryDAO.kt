package com.ezralee.bdotodo.data.repository.history

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.ezralee.bdotodo.data.model.HistoryData

@Dao
interface HistoryDAO {
    //특정 유저의 history 내역을 날짜 내림차순으로 전부 쿼리
    @Query("SELECT * FROM historyData WHERE userId = :userId ORDER BY date DESC")
    fun getAll(userId: String): MutableLiveData<List<HistoryData>>

    @Insert
    fun insert(historyData: HistoryData)

    @Update
    fun update(historyData: HistoryData)

    @Delete
    fun delete(historyData: HistoryData)

    @Query("SELECT * FROM historyData WHERE :search LIKE :keyword ORDER BY date DESC")
    fun filter(search: String, keyword: String): MutableLiveData<List<HistoryData>>
}