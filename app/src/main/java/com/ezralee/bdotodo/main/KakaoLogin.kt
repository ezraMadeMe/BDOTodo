package com.ezralee.bdotodo.main

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.common.KakaoSdk

class KakaoLogin : Application() {

    //싱글톤 패턴(prefs/DB주소/카톡고유아이디)
    companion object {
        lateinit var prefs: PreferenceUtil
        lateinit var USER_DB: FirebaseUtil
        lateinit var USER_ID: String
    }

    override fun onCreate() {
        super.onCreate()
        //카카오SDK 초기화
        KakaoSdk.init(this, "3d3bd2ed347c5b04c668e03c479785f3")

        prefs = PreferenceUtil(applicationContext)
        USER_DB = FirebaseUtil()
        USER_ID = prefs.getString("userInfo", "null")
    }
}

class FirebaseUtil {
    private val USER_DB: CollectionReference =
        FirebaseFirestore.getInstance().collection("userData")

    fun getCollection(docKey: String, colKey: String): CollectionReference {
        return getDocument(docKey).collection(colKey)
    }

    fun setCollection(docKey: String, colKey: String) {
        getDocument(docKey).collection(colKey)
    }

    fun getDocument(key: String): DocumentReference {
        return USER_DB.document(key)
    }

    fun setDocument(key: String) {
        USER_DB.document(key)
    }
}

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }
}