package com.ezralee.bdotodo.data.repository.goal.daily

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ezralee.bdotodo.data.model.*

@Dao
interface DailyDAO {

    //유저가 등록한 모든 할일/한일 목록 쿼리
    @Query("SELECT * FROM taskAccureData WHERE userId = :userId")
    fun getAllAccure(userId: String): MutableLiveData<List<TaskAccureData>>

    //유저가 등록한 해당 날짜의 누적 할일/한일 task 목록 쿼리
    @Query("SELECT * FROM taskAccureData WHERE userId = :userId AND taskAccureDate = :date")
    fun getDaliyAccureList(userId: String, date: String): MutableLiveData<List<TaskAccureData>>

    //할일/한일 삽입
    @Insert
    fun insertAccureData(accureData: TaskAccureData)

    //할일/한일 수정
    @Update
    fun updateAccureData(accureData: TaskAccureData)

    //할일/한일 삭제
    @Delete
    fun deleteAccureData(accureData: TaskAccureData)
}