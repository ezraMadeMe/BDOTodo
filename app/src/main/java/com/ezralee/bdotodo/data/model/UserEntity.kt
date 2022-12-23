package com.ezralee.bdotodo.data.model

import androidx.room.*

//사용자 기본 정보
@Entity(tableName = "userInfo")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="userId")
    var userId : String?,
    @ColumnInfo(name ="date")
    var joinDate: String
)

//미리 설정된 goalPreset 리스트
@Entity(tableName = "goalPreset")
data class GoalPreset(
    @ColumnInfo(name = "goal")
    var goalPreset: String
)

//ViewModel
//앱 회전이나 언어 변경등의 앱 종료(Destroy)에도 사라지지 않는 UI를 위한 데이터를 가지고 있다.
// AsyncTask는 액티비티나 프래그먼트의 생명주기에서 자유로울 수 없지만,
// ViewModel은 View와 분리되어 있기 때문에 액티비티가 Destroy 되었다가 다시 Create 되어도
// 종료되지 않고 데이터를 여전히 가지고 있다.

