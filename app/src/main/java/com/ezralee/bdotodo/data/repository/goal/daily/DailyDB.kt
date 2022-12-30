package com.ezralee.bdotodo.data.repository.goal.daily

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezralee.bdotodo.data.model.GoalData
import com.ezralee.bdotodo.data.model.TaskAccureData

@Database(entities = [TaskAccureData::class], version = 1)
abstract class DailyDB: RoomDatabase() {
    abstract fun dailyDAO() : DailyDAO

    companion object{
        private var instance: DailyDB? = null

        fun getInstance(context: Context): DailyDB?{
            if (instance == null) {
                synchronized(DailyDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DailyDB::class.java,
                        "taskAccureData"
                    ).build()
                }
            }
            return instance
        }
    }
}