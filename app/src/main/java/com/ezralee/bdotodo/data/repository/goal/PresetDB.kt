package com.ezralee.bdotodo.data.repository.goal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.model.PresetItem

//RoomDatabase
//SQLite 데이터베이스를 편하게 사용할 수 있도록 해주는 라이브러리이다.
//SQLite의 코드를 직접 작성하는 경우, 직접 테이블을 Create하거나
// 쿼리문을 일일이 변수를 통해 작성해주어야 했지만
// Room을 쓰면 조금 더 직관적이고 편리하게 DB를 사용할 수 있다.

@Database(entities = [PresetItem::class], version = 1)
abstract class PresetDB: RoomDatabase() {
    abstract fun goalDAO() : GoalDAO

    companion object{
        private var instance: PresetDB? = null

        fun getInstance(context: Context): PresetDB?{
            if (instance == null) {
                synchronized(PresetDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PresetDB::class.java,
                        "goalPreset"
                    ).build()
                }
            }
            return instance
        }
    }
}