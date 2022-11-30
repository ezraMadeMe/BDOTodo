package com.ezralee.bdotodo.main

import java.text.SimpleDateFormat
import java.util.*

//히스토리
//바깥 리사이클러 날짜정보
//data class HistoryDateItem constructor(var date: String)
//안쪽 리사이클러 미리보기 정보(제목/이미지)
//data class HistoryItem constructor(var title: String, var image: Int)
//히스토리 초기 세팅 시 필요한 종합 정보
data class HistoryItem constructor(var title: String, var date: String, var category: String, var image: String, var memo: String)

//데일리
//
data class DailyTodoItem constructor(var color: Int, var todo: String, var title: String, var dday: Int, var percent: Int)
data class AddTodoItem constructor(var date: String, var task: String, var count: Int)

//목표
data class GoalPresetItem constructor(var title: String)
data class GoalListItem constructor(var color: Int, var title: String, var percent: Int, var dday: Int, var isExpanded: Boolean)

data class GoalItem constructor(var goal: String, var color: String, var start: String, var end: String, var category: String, var memo: String, var andor: Boolean, var planItems: MutableList<PlanItem>)
data class PlanItem constructor(var plan: String, var start: String, var end: String, var taskItems: MutableList<TaskItem>)
data class TaskItem constructor(var task: String, var total: Int, var count: Int)

class Info{

    companion object{
        val now by lazy { System.currentTimeMillis() }
        lateinit var now2: Date
        lateinit var sdf: SimpleDateFormat
        lateinit var date: String
    }

    //오늘 날짜 가져오기
    fun getDate(): String{
        now2 = Date(now)
        sdf = SimpleDateFormat("yyyy/MM/dd")
        date = sdf.format(now2)
        return date
    }
}