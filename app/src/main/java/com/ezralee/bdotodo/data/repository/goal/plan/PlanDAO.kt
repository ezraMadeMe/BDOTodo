package com.ezralee.bdotodo.data.repository.goal.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ezralee.bdotodo.data.model.*

@Dao
interface PlanDAO {

    //특정 유저의 goal에 속한 plan 전체 쿼리
    @Query("SELECT * FROM planData WHERE userId = :userId AND goalBelong = :goal")
    fun getPlanData(userId: String, goal: String): MutableLiveData<List<PlanData>>

    @Insert
    fun insertPlan(planData: List<PlanData>)

    @Update
    fun updatePlan(planData: List<PlanData>)

    @Query("DELETE FROM planData WHERE `plan`= :plan")
    fun deletePlan(plan: String)

}