package com.ezralee.bdotodo.data.repository.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezralee.bdotodo.data.model.UserInfo

@Database(entities = [UserInfo::class], version = 1)
abstract class UserDB: RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        private var instance: UserDB? = null
        fun getInstance(context: Context): UserDB? {
            if (instance == null){
                synchronized(UserDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java,
                        "userInfo").build()
                }
            }
            return instance
        }
    }
}