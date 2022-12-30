package com.ezralee.bdotodo.data.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

//history 하나의 정보
@Entity(
    tableName = "historyData",
    foreignKeys = [
        ForeignKey(
            entity = UserInfo::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onUpdate = CASCADE
        )
    ]
)
data class HistoryData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: String?,

    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "memo")
    var memo: String,
    @ColumnInfo(name = "imgUrl")
    var imgUrl: String
) {
    constructor() : this(null, "", "", "", "", "")
}