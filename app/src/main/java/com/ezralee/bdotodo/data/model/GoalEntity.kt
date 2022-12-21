package com.ezralee.bdotodo.data.model

import androidx.room.*

//미리 설정된 goalPreset 리스트
@Entity(tableName = "goalPreset")
data class GoalPreset(
    @ColumnInfo(name = "goal")
    var goalPreset: String
)

//goal하나+goal에 속한 plan의 리스트
data class GoalItem(
    @Embedded val goalData: GoalData,
    @Relation(
        parentColumn = "goal",
        entityColumn = "goalBelong"
    )
    val planList: MutableList<PlanItem>
)

//plan하나+plan에 속한 task의 리스트
data class PlanItem(
    @Embedded val planData: PlanData,
    @Relation(
        parentColumn = "plan",
        entityColumn = "planBelong"
    )
    val taskList: MutableList<TaskItem>
)

//task하나+task의 날짜별 누적 개수
data class TaskItem(
    @Embedded val taskData: TaskData,
    @Relation(
        parentColumn = "task",
        entityColumn = "taskBelong"
    )
    val taskAccureList: MutableList<TaskAccureData>
)

//accure이 속한 대목표의 색상/디데이/토탈개수
data class AccureList(
    @Embedded val goalData: GoalData,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val accureList: MutableList<TaskAccureData>
)

//daily에 보여질 task와 goal의 연관관계 표시


//goal의 기본 정보
@Entity(tableName = "goalData")
data class GoalData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
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
    var memo: String
){
    constructor() : this(null,"","","","","","")
}

//plan의 기본 정보
@Entity(tableName = "planData")
data class PlanData(
    @ColumnInfo(name ="goalBelong")
    var goal: String,
    @ColumnInfo(name ="plan")
    var plan: String,
    @ColumnInfo(name ="start")
    var start: String,
    @ColumnInfo(name ="end")
    var end: String,
    @ColumnInfo(name ="andor")
    var andor: Boolean

){
    constructor() : this("","","","",false)
}

//task의 기본 정보
@Entity(tableName = "taskData")
data class TaskData(
    @ColumnInfo(name ="planBelong")
    var plan: String,
    @ColumnInfo(name ="task")
    var task: String,
    @ColumnInfo(name ="total")
    var total: Int,
    @ColumnInfo(name ="count")
    var count: Int
){
    constructor() : this("","",0,0)
}

//날짜별로 완수한 task 개수
@Entity(tableName = "taskAccureData")
data class TaskAccureData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
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