package com.ezralee.bdotodo.data.repository.history

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ezralee.bdotodo.data.model.HistoryData

@Dao
interface HistoryDAO {
    //특정 유저의 history 내역을 전부 쿼리
    @Query("SELECT * FROM historyList WHERE userId = :userId")
    fun getHistory(userId: String): LiveData<List<HistoryData>>

    //특정 유저의 history 내역 삽입
    @Insert
    fun insertHistory(history: HistoryData)

    //특정 유저의 history 내역 수정
    @Update
    fun updateHistory(history: HistoryData)

    //특정 유저의 history 내역 삭제
    @Delete
    fun deleteHistory(history: HistoryData)
}