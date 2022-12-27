package com.ezralee.bdotodo.viewmodel.main

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.Util.RetrofitHelper
import com.ezralee.bdotodo.data.Util.RetrofitService
import com.ezralee.bdotodo.data.model.UserInfo
import com.ezralee.bdotodo.data.repository.user.UserDB
import com.ezralee.bdotodo.data.repository.user.UserRepo
import com.ezralee.bdotodo.ui.activity.intro.MainActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainVM(application: Application): AndroidViewModel(application) {

    private val repository = UserRepo(application)
    private val db = Room.databaseBuilder(application, UserDB::class.java,"userInfo")
                         .allowMainThreadQueries()
                         .build()

    var _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> get() = _userInfo

    fun kakaoLogin(): (OAuthToken?, Throwable?) -> Unit {
        var new: UserInfo

        val keyhash: String = Utility.getKeyHash(getApplication())
        Log.d("%%%%keyhash", keyhash)

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