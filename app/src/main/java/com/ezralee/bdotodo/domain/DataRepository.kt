package com.ezralee.bdotodo.domain

import android.content.Context
import org.json.JSONObject

//싱글톤 필수
class DataRepository(context: Context) : DataSource {

    private val localDataSource = LocalDataSource(context)

    override fun getInfo(callback: DataSource.LoadInfoCallback) {
        localDataSource.getInfo(callback)
    }

    override fun saveInfo(info: JSONObject) {
        localDataSource.saveInfo(info)
    }
}