package com.ezralee.bdotodo.main

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.ezralee.bdotodo.history.UserId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.user.UserApiClient

public final class UserInfo(var context: Context) {

    //유저 고유 아이디 저장
    fun setUserId(id: String): SharedPreferences {
        var saveUserInfo: SharedPreferences =
            context.getSharedPreferences("userInfo", Activity.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = saveUserInfo.edit()

        editor.putString("userId", id)
        editor.commit()

        return saveUserInfo
    }

    //유저 고유 아이디를 String으로 반환
    fun getUserId(saveUserInfo: SharedPreferences): String {
        var saveUserInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val UserId = saveUserInfo.getString("userId", "없음").toString()

        return UserId
    }

    fun UserId(): String{
        lateinit var result: String
        UserApiClient.instance.me { user, error ->
            if (user != null){
                result = user.id.toString()
            }else{
                result = error.toString()
            }
        }
        return result
    }

    //유저 Firebase 저장경로
    fun getUserDB(id: String): DocumentReference{
        var string = getUserId(setUserId(id))
        val UserDB: DocumentReference =
            FirebaseFirestore.getInstance().collection("userData").document(string)

        return UserDB
    }
}

