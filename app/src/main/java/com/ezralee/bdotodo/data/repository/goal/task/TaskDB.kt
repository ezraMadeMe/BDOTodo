package com.ezralee.bdotodo.data.repository.goal.task

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezralee.bdotodo.data.model.TaskData

@Database(entities = [TaskData::class], version = 1)
abstract class TaskDB: RoomDatabase() {
    abstract fun taskDAO() : TaskDAO

    companion object{
        private var instance: TaskDB? = null

        fun getInstance(context: Context): TaskDB?{
            if (instance == null) {
                synchronized(TaskDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDB::class.java,
                        "taskData"
                    ).build()
                }
            }
            return instance
        }
    }
}