package com.ezralee.bdotodo.main

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.databinding.ActivityLoginBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import java.text.SimpleDateFormat
import java.util.Date

class LoginActivity : AppCompatActivity() {

    val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val pref: UserInfo by lazy { UserInfo(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //키해시 값 얻어오기
        val keyhash: String = Utility.getKeyHash(this)
        Log.d("keyhash", keyhash)

        binding.kakaoLogin.setOnClickListener { clickedKakaoLogin() }
    }//onCreate

    //카카오 로그인
    private fun clickedKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (token != null) {
                //Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                //로그인 성공 시 firebase DB에 컬렉션 생성하는 메서드
                loadUserInfo()
            } else {
                //Toast.makeText(this,error.toString(), Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this).setMessage(error.toString()).show()
            }
        }
        Log.i("context", this.toString())

        //카카오톡 로그인 먼저 시도한 후 카카오계정 로그인 시도
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }//카카오 로그인

    //로그인 성공한 사용자 정보 가져와서 Firebase에 저장
    fun loadUserInfo() {
        UserApiClient.instance.me { user, error ->
            //사용자 정보를 제대로 가져왔을 경우
            if (user != null) {
                //로그인 성공한 유저의 id와 가입날짜정보
                var id: String = user.id.toString()
                var date: String = user.connectedAt.toString()

                //유저 정보 영구 저장
                pref.setUserId(id)

                //FirebaseFirestore 객체 생성
                Log.i("user", user.toString())

                //firestore 에 유저 데이터 저장
                var userInfo: Map<String, String> = mapOf(
                    "userId" to id,
                    "joinDate" to date
                )

                //유저를 구분하는 최상위 컬렉션 생성
                pref.getUserDB(id).set(userInfo)

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
                pref.getUserDB(id).collection("history").document("앱 생성일").set(firstHistory)

                //로그인 성공 시 메인액티비티로 intent
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                /* 사용자가 재 로그인 시 document가 새로 생성되는 문제...
                //사용자 정보가 존재하는지 체크
                if (firebaseFirestore.collection("userData").whereEqualTo("userId", user.id) == null){
                    //firestore 에 유저 데이터 저장
                    var userInfo: Map<String, String> = mapOf(
                        "userId" to id,
                        "joinDate" to date)

                    //오늘 날짜로 컬렉션 제목 설정
                    var sdf = SimpleDateFormat("yyyyMMdd")
                    var timeStamp =sdf.format(Date())

                    //컬렉션 생성
                    val userIdRef: DocumentReference = firebaseFirestore.collection("userData").document("${timeStamp}_$id")
                    userIdRef.set(userInfo)

                    //앱시작 시 히스토리 컬렉션 생성
                    var startHistory: MutableMap<String, String> = mutableMapOf(
                        "title" to "앱 생성일",
                        "date" to date,
                        "category" to "히스토리",
                        "memo" to "이것만 하고 자야지 - "
                    )
                    val userHistoryRef: DocumentReference = firebaseFirestore.document("${timeStamp}_$id").collection("histroy").document("앱 생성일")
                    userHistoryRef.set(startHistory)
                }
                */
            }//신규 사용자DB 생성
        }
    }
}