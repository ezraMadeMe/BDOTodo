package com.ezralee.bdotodo.data.repository.goal

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ezralee.bdotodo.data.model.*

//RoomDatabase
//SQLite 데이터베이스를 편하게 사용할 수 있도록 해주는 라이브러리이다.
//SQLite의 코드를 직접 작성하는 경우, 직접 테이블을 Create하거나
// 쿼리문을 일일이 변수를 통해 작성해주어야 했지만
// Room을 쓰면 조금 더 직관적이고 편리하게 DB를 사용할 수 있다.

@Dao
interface GoalDAO {
    @Query("SELECT * FROM goalpreset")
    fun getPresets(): MutableLiveData<List<PresetItem>>

    //특정 유저의 goal 전체 쿼리
    @Query("SELECT * FROM goalData WHERE userId = :userId ORDER BY start DESC")
    fun getGoalItem(userId: String): MutableLiveData<List<GoalItem>>

    //특정 유저의 goal에 속한 plan 전체 쿼리
    @Query("SELECT * FROM planData WHERE userId = :userId AND goalBelong = :goal")
    fun getPlanItem(userId: String, goal: String): MutableLiveData<List<PlanItem>>

    //특정 유저의 plan에 속한 task 전체 쿼리
    @Query("SELECT * FROM taskData WHERE userId = :userId AND planBelong = :plan")
    fun getTaskItem(userId: String, plan: String): MutableLiveData<List<TaskItem>>

    //특정 유저의 goal 목록 쿼리
    @Query("SELECT goal FROM goalData WHERE userId = :userId ORDER BY start DESC")
    fun getGoalList(userId: String): MutableLiveData<List<GoalData>>

    //유저가 선택한 goal에 속한 plan 쿼리
    @Query("SELECT `plan` FROM planData WHERE userId = :userId AND goalBelong = :goal ORDER BY start DESC")
    fun getPlanList(userId: String, goal: String): MutableLiveData<List<PlanData>>

    //유저가 선택한 plan에 속한 task 쿼리
    @Query("SELECT task FROM taskData WHERE userId = :userId AND planBelong = :plan")
    fun getTaskList(userId: String, plan: String): MutableLiveData<List<TaskData>>

    //goal 삽입
    @Insert
    fun insert(goalItem: GoalItem)

    //goal 수정
    @Update
    fun update(goalItem: GoalItem)

    //goal 삭제
    @Delete
    fun delete(goalItem: GoalItem)
}