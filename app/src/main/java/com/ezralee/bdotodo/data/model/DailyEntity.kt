package com.ezralee.bdotodo.data.model

import androidx.room.*

//accure이 속한 대목표의 색상/디데이/토탈개수
data class AccureItem(
    @Embedded
    val taskAccureData: TaskAccureData,
    @Relation(
        parentColumn = "goalBelong",
        entityColumn = "goal"
    )
    val goalData: GoalData
)

