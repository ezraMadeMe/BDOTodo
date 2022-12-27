package com.ezralee.bdotodo.data.repository.user

import androidx.room.Dao
import androidx.room.Insert
import com.ezralee.bdotodo.data.model.UserInfo

@Dao
interface UserDAO {
    @Insert
    fun insert(userInfo: UserInfo)
}