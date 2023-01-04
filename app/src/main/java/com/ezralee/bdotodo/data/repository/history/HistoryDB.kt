package com.ezralee.bdotodo.data.repository.history

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezralee.bdotodo.data.model.HistoryData

@Database(entities = [HistoryData::class], version = 1, exportSchema = false)
abstract class HistoryDB : RoomDatabase() {

    abstract fun hisDAO() : HistoryDAO

    companion object{
        private var instance : HistoryDB? = null

        fun getInstance(context: Context): HistoryDB?{
            if (instance == null) {
                synchronized(HistoryDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDB::class.java,
                        "history_db")
                        .build()
                }
            }
            return instance
        }
    }
}