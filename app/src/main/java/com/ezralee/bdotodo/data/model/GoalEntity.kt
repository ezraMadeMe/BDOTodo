package com.ezralee.bdotodo.data.model

import androidx.room.*

//사용자의 모든 goal 정보
data class UserGoalData(
    @Embedded val userInfo: UserInfo,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val goalList: MutableList<GoalItem>
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
    var memo: String
){
    constructor() : this(null,"","","","","","")
}

///////////////////////////////plan 정보
@Entity(tableName = "planData")
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
    @ColumnInfo(name ="andor")
    var andor: Boolean

){
    constructor() : this("","","","","",false)
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
    var total: Int,
    @ColumnInfo(name ="count")
    var count: Int
){
    constructor() : this("","","",0,0)
}