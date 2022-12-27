package com.ezralee.bdotodo.data

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.Util.RetrofitHelper
import com.ezralee.bdotodo.data.Util.RetrofitService
import com.ezralee.bdotodo.data.model.UserInfo
import com.ezralee.bdotodo.ui.activity.intro.MainActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

fun kakaoLogin(): UserInfo {
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
}

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
}