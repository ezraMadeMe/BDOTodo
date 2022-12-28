package com.ezralee.bdotodo.data.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE


///////////////////////////////goal 정보
@Entity(tableName = "goalData")
data class GoalData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="userId")
    var userId : String?,

    @ColumnInfo(name ="goal")
    var goal: String,
    @ColumnInfo(name ="start")
    var start: String,
    @ColumnInfo(name ="end")
    var end: String,
    @ColumnInfo(name ="color")
    var color: String,
    @ColumnInfo(name ="category")
    var category: String,
    @ColumnInfo(name ="memo")
    var memo: String,
    @ColumnInfo(name ="andor")
    var andor: Boolean


){
    constructor() : this(null,"","","","","","",false)
}

///////////////////////////////plan 정보
data class PlanData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="userId")
    var userId : String?,

    @ColumnInfo(name ="goalBelong")
    var goal: String,
    @ColumnInfo(name ="plan")
    var plan: String,
    @ColumnInfo(name ="start")
    var start: String,
    @ColumnInfo(name ="end")
    var end: String,
){
    constructor() : this("","","","","")
}

///////////////////////////////task 정보
@Entity(tableName = "taskData")
data class TaskData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="userId")
    var userId : String?,

    @ColumnInfo(name ="planBelong")
    var plan: String,
    @ColumnInfo(name ="task")
    var task: String,
    @ColumnInfo(name ="total")
    var total: String,
    @ColumnInfo(name ="count")
    var count: String
){
    constructor() : this("","","","0","0")
}

///////////////////////////////accure 정보
@Entity(tableName = "taskAccureData",
    foreignKeys = [
        ForeignKey(
            entity = TaskData::class,
            parentColumns = ["userId", "task"],
            childColumns = ["userId", "taskBelong"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ])
data class TaskAccureData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="userId")
    var userId : String?,

    @ColumnInfo(name ="goalBelong")
    var goal: String,
    @ColumnInfo(name ="taskBelong")
    var task: String,
    @ColumnInfo(name ="taskAccureDate")
    var date: String,
    @ColumnInfo(name ="count")
    var count: String
){
    constructor() : this("","","","","0")
}