package com.ezralee.bdotodo.viewmodel

import androidx.room.Embedded
import androidx.room.*

//ViewModel
//앱 회전이나 언어 변경등의 앱 종료(Destroy)에도 사라지지 않는 UI를 위한 데이터를 가지고 있다.
// AsyncTask는 액티비티나 프래그먼트의 생명주기에서 자유로울 수 없지만,
// ViewModel은 View와 분리되어 있기 때문에 액티비티가 Destroy 되었다가 다시 Create 되어도
// 종료되지 않고 데이터를 여전히 가지고 있다.

//LiveData
//View가 ViewModel을 관찰할 때, 그 관찰 대상이 되는 관찰 가능한(Observable) 데이터 홀더 클래스이다.
// View에서 ViewModel의 LiveData를 관찰하게 되면 데이터가 변경될 때 내부적으로 자동으로 알려주게 된다.
// Live Data는 Activity 및 Fragment의 생명주기를 인지한다.
// 즉, Activity가 화면 위에 활성화되어 있을 때만 UI변경 등의 기능을 동작하게 되고,
// Destroy되 상태에서는 동작하지 않기 때문에 메모리 릭의 발생을 줄여준다.

//Repository
//ViewModel과 데이터를 주고받기 위해, 데이터 API를 포함하는 클래스다.
// 앱에 필요한 데이터, 즉 내장 데이터베이스나 외부 웹 서버 등에서 데이터를 가져온다.
// 따라서 ViewModel은 DB나 서버에 직접 접근하지 않고, Repository에 접근하는 것으로
// 앱의 데이터를 관리한다. Repository의 존재 덕분에 ViewModel은 데이터를 관리할 필요가 없게 된다.

//RoomDatabase
//SQLite 데이터베이스를 편하게 사용할 수 있도록 해주는 라이브러리이다.
//SQLite의 코드를 직접 작성하는 경우, 직접 테이블을 Create하거나
// 쿼리문을 일일이 변수를 통해 작성해주어야 했지만
// Room을 쓰면 조금 더 직관적이고 편리하게 DB를 사용할 수 있다.

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
@Entity(tableName = "historyList")
data class HistoryDateData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    var userId : String?,
    @ColumnInfo(name ="date")
    var date: String
){
    constructor() : this(null,"")
}

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