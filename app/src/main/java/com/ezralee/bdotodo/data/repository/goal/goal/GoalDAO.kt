package com.ezralee.bdotodo.data.repository.goal.goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ezralee.bdotodo.data.model.*

@Dao
interface GoalDAO {

    //특정 유저의 goal 전체 쿼리
    @Query("SELECT * FROM goalData WHERE userId = :userId ORDER BY start ASC")
    fun getGoalData(userId: String): LiveData<List<GoalData>>

    //goal 삽입
    @Insert
    fun insertGoal(goalData: GoalData)

    //goal 수정
    @Update
    fun updateGoal(goalData: GoalData)

    //goal 삭제
    @Query("DELETE FROM goalData WHERE goal=:goal")
    fun deleteGoal(goal: String)

}