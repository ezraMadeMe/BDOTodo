package com.ezralee.bdotodo.viewmodel.main

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.data.model.UserInfo
import com.ezralee.bdotodo.data.repository.user.UserDB
import com.ezralee.bdotodo.data.repository.user.UserRepo
import com.ezralee.bdotodo.viewmodel.Event
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class MainVM(application: Application): AndroidViewModel(application) {

    private val repository = UserRepo(application)
    private val db = Room.databaseBuilder(application, UserDB::class.java,"userInfo")
                         .allowMainThreadQueries()
                         .build()

    var _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> get() = _userInfo

    private val _loginEvent = MutableLiveData<Event<UserInfo>>()
    val loginEvent: LiveData<Event<UserInfo>> get() = _loginEvent

    fun kakaoLogin(data: UserInfo): (OAuthToken?, Throwable?) -> Unit {

        _loginEvent.value = Event(data)

        var new: UserInfo

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.i("####loginavailable", token.toString())
            if (token != null) {
                UserApiClient.instance.me { user, error ->
                    if (user != null) {
                        new = UserInfo(user.id.toString(), user.connectedAt.toString())
                        repository.insert(new)
                    }
                }
            } else if (error != null) {
                AlertDialog.Builder(getApplication()).setMessage(error.toString()).show()
            }
        }
        return callback
    }

    var currentPosition = 0

    var pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            currentPosition = position
        }
    }
}