package com.ezralee.bdotodo.data.repository.goal.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ezralee.bdotodo.data.model.*

@Dao
interface TaskDAO {

    //특정 유저의 plan에 속한 task 전체 쿼리
    @Query("SELECT * FROM taskData WHERE userId = :userId AND planBelong = :plan")
    fun getTaskData(userId: String, plan: String): LiveData<List<TaskData>>

    @Insert
    fun insertTask(taskData: List<TaskData>)

    @Update
    fun updateTask(taskData: List<TaskData>)

    //task 삭제
    @Query("DELETE FROM taskData WHERE task=:task")
    fun deleteTask(task: String)
}