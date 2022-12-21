package com.ezralee.bdotodo.data.model

import androidx.room.*

////history들의 날짜 정보
//@Entity(tableName = "historyList")
//data class HistoryDateData(
//    //autoGenerate null을 받으면 ID 값을 자동으로 할당
//    @PrimaryKey(autoGenerate = true)
//    var userId : String?,
//    @ColumnInfo(name ="date")
//    var date: String
//){
//    constructor() : this(null,"")
//}

//history 하나의 정보
@Entity(tableName = "historyList")
data class HistoryData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
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