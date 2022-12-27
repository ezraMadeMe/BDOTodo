package com.ezralee.bdotodo.ui.activity.intro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.Util.RetrofitHelper
import com.ezralee.bdotodo.databinding.ActivityLoginBinding
import com.ezralee.bdotodo.data.Util.RetrofitService
import com.ezralee.bdotodo.data.kakaoLogin
import com.ezralee.bdotodo.data.model.UserInfo
import com.ezralee.bdotodo.data.repository.user.UserDB
import com.ezralee.bdotodo.viewmodel.main.MainVM
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

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainVM
    private lateinit var db: UserDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = UserDB.getInstance(this@LoginActivity)!!
        viewModel = ViewModelProvider(this)[MainVM::class.java]
        binding = DataBindingUtil
            .setContentView(this@LoginActivity, R.layout.activity_login)

        viewModel.kakaoLogin().apply {
            kakaoLoginCallback(this)
        }
    }//onCreate

    fun kakaoLoginCallback(callback: (OAuthToken?, Throwable?) -> Unit){
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)

        } else {
            UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
        }
    }
}