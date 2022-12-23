package com.ezralee.bdotodo.data.model

import androidx.room.*

//accure이 속한 대목표의 색상/디데이/토탈개수
@Entity(tableName = "daily")
data class AccureItem(
    @Embedded
    val taskAccureData: TaskAccureData,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val goalData: GoalData
)

///////////////////////////////accure 정보
@Entity(tableName = "taskAccureData")
data class TaskAccureData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="userId")
    var userId : String?,

    @ColumnInfo(name ="taskBelong")
    var task: String,
    @ColumnInfo(name ="taskAccureDate")
    var date: String,
    @ColumnInfo(name ="count")
    var count: Int
){
    constructor() : this("","","",0)
}