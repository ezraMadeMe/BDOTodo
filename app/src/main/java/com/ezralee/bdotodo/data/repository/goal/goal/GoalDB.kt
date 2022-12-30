package com.ezralee.bdotodo.data.repository.goal.goal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezralee.bdotodo.data.model.GoalData

@Database(entities = [GoalData::class], version = 1)
abstract class GoalDB: RoomDatabase() {
    abstract fun goalDAO() : GoalDAO

    companion object{
        private var instance: GoalDB? = null

        fun getInstance(context: Context): GoalDB?{
            if (instance == null) {
                synchronized(GoalDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GoalDB::class.java,
                        "goalData"
                    ).build()
                }
            }
            return instance
        }
    }
}