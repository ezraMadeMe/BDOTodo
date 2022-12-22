package com.ezralee.bdotodo.data.repository.history

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ezralee.bdotodo.data.model.HistoryData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.chrono.HijrahChronology.INSTANCE

@Database(entities = [HistoryData::class], version = 1)
abstract class HistoryDB : RoomDatabase() {

    abstract fun hisDAO() : HistoryDAO

    companion object{
        private var Instance : HistoryDB? = null

        fun getInstance(context: Context): HistoryDB?{
            if (Instance == null) {
                synchronized(HistoryDB::class) { //synchronized: 여러 스레드가 동시에 접근 불가. 동기적으로 접근
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDB::class.java,
                        "history")
                        .build()
                }
            }
            return Instance
        }
    }
}