package com.ezralee.bdotodo.main

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.common.KakaoSdk
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class KakaoLogin : Application() {

    //싱글톤 패턴(prefs/DB주소/카톡고유아이디)
    companion object {
        lateinit var prefs: PreferenceUtil
        lateinit var USER_DB: FirebaseUtil
        val USER_ID: String by lazy { prefs.getString("userInfo", "") }
    }

    override fun onCreate() {
        super.onCreate()
        //카카오SDK 초기화
        KakaoSdk.init(this, "3d3bd2ed347c5b04c668e03c479785f3")

        USER_DB = FirebaseUtil()
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

class Info {

    companion object {
        val now by lazy { System.currentTimeMillis() }
        val now2: Date by lazy { Date(now) }
        val sdf: SimpleDateFormat by lazy { SimpleDateFormat("yyyy/MM/dd") }
        var date: String = sdf.format(now2)
    }
}