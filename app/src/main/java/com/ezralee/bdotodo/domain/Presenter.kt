package com.ezralee.bdotodo.domain

import org.json.JSONObject

class Presenter(
    val view: Contract.View,
    val repository: DataRepository
) : Contract.Presenter {

    override fun initInfo() {
        repository.getInfo(object : DataSource.LoadInfoCallback {
            override fun onInfoLoaded(info: JSONObject) {
                view.showInfo(info)
            }

            override fun onDataNotAvailable() {
                // 아무것도 하지 않음
            }
        })
    }

    override fun setInfo(info: JSONObject) {
        view.showInfo(info)
    }

    override fun saveInfo(info: JSONObject) {
        repository.saveInfo(info)
    }
}