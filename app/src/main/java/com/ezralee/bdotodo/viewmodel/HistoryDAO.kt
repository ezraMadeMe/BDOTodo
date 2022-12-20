package com.ezralee.bdotodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HistoryDAO {
    @Query("SELECT * FROM historyList WHERE userId = :userId")
    fun getHistory(userId: String): LiveData<List<HistoryData>>

    @Insert
    //(onConflict = OnConflictStrategy.REPLACE) // 중복 ID일 경우 교체
    fun insertHistory(todo: HistoryData)

    @Update
    fun updateHistory(todo: HistoryData)

    @Delete
    fun deleteHistory(todo: HistoryData)
}