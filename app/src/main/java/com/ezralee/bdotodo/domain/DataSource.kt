package com.ezralee.bdotodo.domain

import org.json.JSONObject

interface DataSource {
    //info 데이터를 불러올때, LoadInfoCallback 을 사용
    interface LoadInfoCallback {
        //성공했을때 Listener
        fun onInfoLoaded(info: JSONObject)
        //오류가 났을 때 Listener
        fun onDataNotAvailable()
    }
    //이와 관련된 콜백함수&get 함수
    //이를 Repository 와 local/remote Data Source 에 implement 하여 사용
    fun getInfo(callback: LoadInfoCallback)
    fun saveInfo(info: JSONObject)

//    Repository : 로컬/서버 중 어떤 데이터를 불러올지 정의하고, 메모리 캐시를 포함한다.
//    Remote data source : 서버에서 데이터를 받아온다
//    Local data source : 로컬에서 데이터를 받아온다
}