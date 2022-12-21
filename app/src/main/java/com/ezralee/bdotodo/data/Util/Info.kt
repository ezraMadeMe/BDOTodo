package com.ezralee.bdotodo.data.Util

import java.text.SimpleDateFormat
import java.util.*

class Info {
    companion object {
        val now by lazy { System.currentTimeMillis() }
        val now2: Date by lazy { Date(now) }
        val sdf: SimpleDateFormat by lazy { SimpleDateFormat("yyyy/MM/dd") }
        var date: String = sdf.format(now2)
    }
}