package com.ezralee.bdotodo.viewmodel

import androidx.room.Embedded
import androidx.room.*

//사용자 기본 정보
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    var userId : String?,
    @ColumnInfo(name ="date")
    var joinDate: String
)

//사용자의 모든 goal 정보
data class UserGoalData(
    @Embedded val userInfo: UserInfo,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val goalUnit: MutableList<GoalItem>
)

//사용자의 모든 history 정보
data class UserHistoryData(
    @Embedded val userInfo: UserInfo,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val historyUnit: MutableList<HistoryUnit>
)


////////////////////////////////////// goal 관련 data class //////////////////////////////////////////////////

////유저의 모든 goal 목록
//data class GoalUnit(
//    @Embedded val userInfo: UserInfo,
//    @Relation(
//        parentColumn = "userId",
//        entityColumn = "userId"
//    )
//    val goalUnit: MutableList<GoalItem>
//)

//goal하나+goal에 속한 plan의 리스트
data class GoalItem(
    @Embedded val goalData: GoalData,
    @Relation(
        parentColumn = "goal",
        entityColumn = "goalBelong"
    )
    val planUnit: MutableList<PlanItem>
)

//plan하나+plan에 속한 task의 리스트
data class PlanItem(
    @Embedded val planData: PlanData,
    @Relation(
        parentColumn = "plan",
        entityColumn = "planBelong"
    )
    val taskUnit: MutableList<TaskItem>
)

//task하나+task의 날짜별 누적 개수
data class TaskItem(
    @Embedded val taskData: TaskData,
    @Relation(
        parentColumn = "task",
        entityColumn = "taskBelong"
    )
    val taskAccure: MutableList<TaskAccureData>
)

//goal의 기본 정보
@Entity(tableName = "goalList")
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
@Entity
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
@Entity
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
@Entity
data class TaskAccureData(
    @ColumnInfo(name ="taskBelong")
    var task: String,
    @ColumnInfo(name ="taskAccureDate")
    var date: String,
    @ColumnInfo(name ="count")
    var count: Int,
){
    constructor() : this("","",0)
}


////////////////////////////////////// history 관련 data class //////////////////////////////////////////////////

//유저의 모든 history 목록
data class HistoryUnit(
    @Embedded val userInfo: UserInfo,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val historyUnit: MutableList<HistoryData>
)

//history들의 날짜 정보
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