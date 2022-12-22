package com.ezralee.bdotodo.data.repository.history

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ezralee.bdotodo.data.model.HistoryData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

// 데이터베이스에 접근해서 실질적으로 insert, delete 등을 수행하는 메소드를 포함
@Dao
interface HistoryDAO {
    //특정 유저의 history 내역을 전부 쿼리
    @Query("SELECT * FROM historyList WHERE userId = :userId ORDER BY date DESC")
    fun getAll(userId: String): LiveData<List<HistoryData>>

    //history 내역 삽입
    @Insert
    fun insert(historyData: HistoryData)

    //특정 유저의 history 내역 수정
    @Update
    fun update(historyData: HistoryData)

    //특정 유저의 history 내역 삭제
    @Delete
    fun delete(historyData: HistoryData)

    @Query("SELECT * FROM historyList WHERE :search LIKE :keyword ORDER BY date DESC")
    fun filter(search: String, keyword: String): LiveData<List<HistoryData>>

}