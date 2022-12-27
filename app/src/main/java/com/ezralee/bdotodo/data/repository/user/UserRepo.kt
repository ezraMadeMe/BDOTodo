package com.ezralee.bdotodo.data.repository.user

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ezralee.bdotodo.data.model.UserInfo

class UserRepo(application: Application) {

    private var userDAO: UserDAO

    init {
        var db: UserDB = UserDB.getInstance(application)!!
        userDAO = db.userDAO()
    }

    fun insert(userInfo: UserInfo){
        userDAO.insert(userInfo)
    }
}