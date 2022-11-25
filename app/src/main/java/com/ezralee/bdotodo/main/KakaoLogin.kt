package com.ezralee.bdotodo.main

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class KakaoLogin : Application() {
    override fun onCreate() {
        super.onCreate()
        //카카오SDK 초기화
        KakaoSdk.init(this,"3d3bd2ed347c5b04c668e03c479785f3")
    }
}