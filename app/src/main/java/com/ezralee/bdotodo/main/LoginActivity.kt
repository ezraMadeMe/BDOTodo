package com.ezralee.bdotodo.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.databinding.ActivityLoginBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
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
        val keyhash :String =Utility.getKeyHash(this)
        Log.d("keyhash",keyhash)

        binding.kakaoLogin.setOnClickListener { clickedKakaoLogin() }
    }//onCreate

    private fun clickedKakaoLogin(){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (token != null){
                //Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                //로그인 성공 시 firebase DB에 컬렉션 생성하는 메서드
                loadUserInfo()
                //로그인 성공 시 메인액티비티로 intent
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }else{
                //Toast.makeText(this,error.toString(), Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this).setMessage(error.toString()).show()
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }//카카오 로그인

    fun loadUserInfo(){
        UserApiClient.instance.me { user, error ->
            if(user != null){
                Log.i("user",user.toString())
                //로그인 성공한 유저의 id와 가입날짜정보
                var id: String = user.id.toString()
                var date: String = user.connectedAt.toString()
                //firestore 에 유저 데이터 저장
                val firebaseFirestore = FirebaseFirestore.getInstance()
                var userInfo: Map<String, String> = mapOf(
                    "userId" to id,
                    "joinDate" to date)
                //오늘 날짜로 컬렉션 제목 설정
                var sdf = SimpleDateFormat("yyyyMMdd")
                var timeStamp =sdf.format(Date())
                //컬렉션 생성
                val userIdRef: CollectionReference = firebaseFirestore.collection("${timeStamp}_$id")
                userIdRef.document("history").set(userInfo)
                userIdRef.document("goal").set(userInfo)
            }
        }
    }
}