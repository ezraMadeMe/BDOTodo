package com.ezralee.bdotodo.ui.activity.intro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityLoginBinding
import com.ezralee.bdotodo.data.repository.user.UserDB
import com.ezralee.bdotodo.viewmodel.eventObserve
import com.ezralee.bdotodo.viewmodel.main.MainVM
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient


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

        viewModel.kakaoLogin(viewModel.userInfo.value!!).apply {
            kakaoLoginCallback(this)
        }
    }//onCreate

    private fun kakaoLoginCallback(callback: (OAuthToken?, Throwable?) -> Unit){
        val keyhash: String = Utility.getKeyHash(this@LoginActivity)
        Log.d("%%%%keyhash", keyhash)

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)

        } else {
            UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
        }

        viewModel.loginEvent.eventObserve(this){ it ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userId",it.userId)
            intent.putExtra("joinDate", it.joinDate)
            startActivity(intent)
        }
    }
}