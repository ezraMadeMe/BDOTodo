package com.ezralee.bdotodo.data.repository.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.ezralee.bdotodo.data.model.HistoryData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

// 데이터베이스에 접근해서 실질적으로 insert, delete 등을 수행하는 메소드를 포함
//RoomDatabase
//SQLite 데이터베이스를 편하게 사용할 수 있도록 해주는 라이브러리이다.
//SQLite의 코드를 직접 작성하는 경우, 직접 테이블을 Create하거나
// 쿼리문을 일일이 변수를 통해 작성해주어야 했지만
// Room을 쓰면 조금 더 직관적이고 편리하게 DB를 사용할 수 있다.

@Dao
interface HistoryDAO {
    //특정 유저의 history 내역을 날짜 내림차순으로 전부 쿼리
    @Query("SELECT * FROM historyData WHERE userId = :userId ORDER BY date DESC")
    fun getAll(userId: String): MutableLiveData<List<HistoryData>>

    //history 삽입
    @Insert
    fun insert(historyData: HistoryData)

    //history 내역 수정
    @Update
    fun update(historyData: HistoryData)

    //history 내역 삭제
    @Delete
    fun delete(historyData: HistoryData)

    //히스토리를 특정 키워드/카테고리/날짜로 필터
    @Query("SELECT * FROM historyData WHERE :search LIKE :keyword ORDER BY date DESC")
    fun filter(search: String, keyword: String): MutableLiveData<List<HistoryData>>

}