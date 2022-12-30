package com.ezralee.bdotodo.data.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

///////////////////////////////goal 정보
@Entity(
    tableName = "goalData",
    foreignKeys = [
        ForeignKey(
            entity = UserInfo::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onUpdate = CASCADE
        )
    ]
)
data class GoalData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: String?,

    @ColumnInfo(name = "goal")
    var goal: String,
    @ColumnInfo(name = "start")
    var start: String,
    @ColumnInfo(name = "end")
    var end: String,
    @ColumnInfo(name = "color")
    var color: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "memo")
    var memo: String


) {
    constructor() : this(null, "", "", "", "", "", "")
}

///////////////////////////////plan 정보
@Entity(
    tableName = "planData",
    foreignKeys = [
        ForeignKey(
            entity = GoalData::class,
            parentColumns = ["userId", "goal"],
            childColumns = ["userId", "goalBelong"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class PlanData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: String?,

    @ColumnInfo(name = "goalBelong")
    var goal: String,
    @ColumnInfo(name = "plan")
    var plan: String,
    @ColumnInfo(name = "start")
    var start: String,
    @ColumnInfo(name = "end")
    var end: String,
    @ColumnInfo(name = "andor")
    var andor: Boolean
) {
    constructor() : this("", "", "", "", "", false)
}

///////////////////////////////task 정보
@Entity(
    tableName = "taskData",
    foreignKeys = [
        ForeignKey(
            entity = PlanData::class,
            parentColumns = ["usrId", "plan"],
            childColumns = ["userId", "planBelong"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class TaskData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: String?,

    @ColumnInfo(name = "planBelong")
    var plan: String,
    @ColumnInfo(name = "task")
    var task: String,
    @ColumnInfo(name = "total")
    var total: String,
    @ColumnInfo(name = "count")
    var count: String
) {
    constructor() : this("", "", "", "0", "0")
}

///////////////////////////////accure 정보
@Entity(
    tableName = "taskAccureData",
    foreignKeys = [
        ForeignKey(
            entity = TaskData::class,
            parentColumns = ["userId", "task", "total"],
            childColumns = ["userId", "taskBelong", "totalBelong"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = GoalData::class,
            parentColumns = ["userId", "goal", "color"],
            childColumns = ["userId", "goalBelong", "colorBelong"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = PlanData::class,
            parentColumns = ["userId", "end"],
            childColumns = ["userId", "endBelong"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class TaskAccureData(
    //autoGenerate null을 받으면 ID 값을 자동으로 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: String?,

    @ColumnInfo(name = "goalBelong")
    var goal: String,
    @ColumnInfo(name = "colorBelong")
    var color: String,
    @ColumnInfo(name = "taskBelong")
    var task: String,
    @ColumnInfo(name = "totalBelong")
    var total: String,
    @ColumnInfo(name = "taskAccureDate")
    var date: String,
    @ColumnInfo(name = "endBelong")
    var end: String,
    @ColumnInfo(name = "count")
    var count: String
) {
    constructor() : this("", "", "", "", "0", "", "", "0")
}