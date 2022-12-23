package com.ezralee.bdotodo.data.Util

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

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
