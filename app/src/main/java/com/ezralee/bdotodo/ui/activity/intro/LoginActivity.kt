package com.ezralee.bdotodo.ui.activity.intro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.Util.RetrofitHelper
import com.ezralee.bdotodo.databinding.ActivityLoginBinding
import com.ezralee.bdotodo.data.Util.RetrofitService
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
            Log.i("choco", token.toString())
            if (token != null) {
                //유저 정보를 가져와 DB,SharedPreference 생성
                loadUserInfo()
                //유저의 최초 히스토리 DB 생성
                postFirstHistory()
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

    fun postFirstHistory() {
        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        var imgPath = R.drawable.img_sample

        var filePart: MultipartBody.Part? = null

        if (imgPath != null) {
            var file = File(imgPath.toString())
            var requestBody = RequestBody.create(MediaType.parse("image/*"), file)
            filePart = MultipartBody.Part.createFormData("img", file.name, requestBody)
        }

        var dataPart = HashMap<String, String>()
        dataPart.put("userId", KakaoLogin.USER_ID)
        dataPart.put("title", "앱 생성일")
        dataPart.put("date", Info.date)
        dataPart.put("category", "히스토리")
        dataPart.put("memo", "이것만 하고 자야지 와 함께 검창의 세계로")

        val call: Call<String> = retrofitService.postHistoryToServer(dataPart, filePart!!)
//        val call: Call<String> = retrofitService.postHistoryToServer(dataPart)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                var result: String? = response.body()
                Toast.makeText(this@LoginActivity, "저장 성공", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //Toast.makeText(this@SetHistoryActivity, ""+t.toString(), Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this@LoginActivity).setMessage(t.message).show()
            }

        })

        //"image" to R.drawable.img_sample
    }

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