package com.ezralee.bdotodo.domain

import android.content.Context
import org.json.JSONObject

class LocalDataSource(context: Context) : DataSource {

    private val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun getInfo(callback: DataSource.LoadInfoCallback) {
        var info = sharedPreferences.getString("info", null)
        if(info != null) {
            callback.onInfoLoaded(JSONObject(info))
        }else {
            callback.onDataNotAvailable()
        }
    }

    override fun saveInfo(info: JSONObject) {
        editor.putString("info", info.toString())
        editor.commit()
    }
}