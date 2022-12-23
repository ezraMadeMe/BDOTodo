package com.ezralee.bdotodo.data.model

import androidx.room.*

//사용자의 모든 history 목록
data class HistoryUnit(
    @Embedded val userInfo: UserInfo,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val historyList: MutableList<HistoryData>
)

//history 하나의 정보
@Entity(tableName = "historyList")
data class HistoryData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="userId")
    var userId : String?,

    @ColumnInfo(name ="title")
    var title: String,
    @ColumnInfo(name ="date")
    var date: String,
    @ColumnInfo(name ="category")
    var category: String,
    @ColumnInfo(name ="memo")
    var memo: String,
    @ColumnInfo(name ="imgUrl")
    var imgUrl: String
){
    constructor() : this(null,"","","","","")
}