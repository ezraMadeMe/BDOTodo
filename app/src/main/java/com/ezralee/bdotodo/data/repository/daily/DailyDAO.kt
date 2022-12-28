package com.ezralee.bdotodo.data.repository.daily

import androidx.lifecycle.LiveData
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
interface DailyDAO {
///////////////////////////////daily 테이블
    //유저가 등록한 모든 할일/한일 목록 쿼리
    @Query("SELECT * FROM taskAccureData WHERE userId = :userId")
    fun getAll(userId: String): MutableLiveData<List<AccureItem>>

    //유저가 등록한 해당 날짜의 누적 할일/한일 task 목록 쿼리
    @Query("SELECT * FROM taskAccureData AS a, goalData AS b WHERE a.userId = :userId AND b.userId = :userId AND a.taskAccureDate = :date AND a.goalBelong = b.goal")
    fun getAccureList(userId: String, date: String): MutableLiveData<List<AccureItem>>

///////////////////////////////taskAccureData 테이블
    //goal 삽입
    @Insert
    fun insert(accureData: TaskAccureData)

    //goal 수정
    @Update
    fun update(accureData: TaskAccureData)

    //goal 삭제
    @Delete
    fun delete(accureData: TaskAccureData)
}