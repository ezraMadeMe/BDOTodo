package com.ezralee.bdotodo.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import java.text.SimpleDateFormat
import java.util.Date

class LoginActivity : AppCompatActivity() {

    val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //키해시 값 얻어오기
        val keyhash: String = Utility.getKeyHash(this)
        Log.d("keyhash", keyhash)

        binding.kakaoLogin.setOnClickListener { clickedKakaoLogin() }
    }//onCreate

    //카카오 로그인
    fun clickedKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.i("choco",token.toString())
            if (token != null) {
                //유저 정보를 가져와 DB,SharedPreference 생성
                loadUserInfo()

                //오늘 날짜
                var sdf = SimpleDateFormat("yyyy/MM/dd")
                var timeStamp = sdf.format(Date())

                //유저의 최초 히스토리 DB 생성
                var firstHistory: MutableMap<String, String> = mutableMapOf(
                    "title" to "앱 생성일",
                    "date" to timeStamp,
                    "category" to "히스토리",
                    "memo" to "이것만 하고 자야지 와 함께 검창의 세계로"
                )

                //첫 히스토리 생성
                //KakaoLogin.USER_DB.setCollection(KakaoLogin.USER_ID,"history")
                //var collection = KakaoLogin.USER_DB.getCollection(KakaoLogin.USER_ID,"history")
                //collection.document(firstHistory[title]!!).set(firstHistory)

                //로그인 성공 시 메인액티비티로 intent
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else if (error != null) {
                AlertDialog.Builder(this).setMessage(error.toString()).show()
            }
        }//callback

        //카카오톡 로그인 먼저 시도한 후 카카오계정 로그인 시도
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }

        //return UserData(UserInfo.USER_ID, UserInfo.USER_DB)
    }//카카오 로그인

    fun loadUserInfo() {
        UserApiClient.instance.me { user, error ->
            //사용자 정보를 제대로 가져왔을 경우

            if (user != null) {

                var id = user.id.toString()
                var date = user.connectedAt.toString()

                //prefs에 유저 아이디 저장
                KakaoLogin.prefs.setString("userInfo", id)
                //prefs에 저장된 아이디를 변수에 저장
                //KakaoLogin.USER_ID = KakaoLogin.prefs.getString("userInfo","null")
                //유저 아이디로 된 최상위 DB 문서 생성
                KakaoLogin.USER_DB.setDocument(id)

                //최상위 문서 바로 아래 필드에 저장할 유저 데이터
                var userInfo: Map<String, String> = mapOf(
                    "userId" to id,
                    "joinDate" to date
                )
                KakaoLogin.USER_DB.getDocument(id).set(userInfo)

                Log.d("check", "${user.id}_${user.connectedAt}")
            }
        }
    }//loadUserInfo
}