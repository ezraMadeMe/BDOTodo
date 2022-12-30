package com.ezralee.bdotodo.data.repository.goal.plan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezralee.bdotodo.data.model.PlanData
import com.ezralee.bdotodo.data.repository.goal.goal.GoalDAO

@Database(entities = [PlanData::class], version = 1)
abstract class PlanDB: RoomDatabase() {
    abstract fun planDAO() : PlanDAO

    companion object{
        private var instance: PlanDB? = null

        fun getInstance(context: Context): PlanDB?{
            if (instance == null) {
                synchronized(PlanDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlanDB::class.java,
                        "planData"
                    ).build()
                }
            }
            return instance
        }
    }
}