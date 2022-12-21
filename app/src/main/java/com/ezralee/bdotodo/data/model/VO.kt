package com.ezralee.bdotodo.data.model

import java.text.SimpleDateFormat
import java.util.*

//히스토리
//바깥 리사이클러 날짜정보
//data class HistoryDateItem constructor(var date: String)
//안쪽 리사이클러 미리보기 정보(제목/이미지)
//data class HistoryItem constructor(var title: String, var image: Int)
//히스토리 초기 세팅 시 필요한 종합 정보
//data class HistoryItem constructor(
//    var userId: String,
//    var title: String,
//    var date: String,
//    var category: String,
//    var memo: String,
//    var image: String
//)
//
////데일리
////
//data class DailyTodoItem constructor(
//    var color: Int,
//    var todo: String,
//    var title: String,
//    var dday: Int,
//    var percent: Int
//)
//
//data class AddTodoItem constructor(var date: String, var task: String, var count: Int)
//
////목표
//data class GoalListItem constructor(
//    var color: Int,
//    var title: String,
//    var percent: Int,
//    var dday: Int,
//    var isExpanded: Boolean
//)
//
////입력한 데이터를 1차로 받음
//data class GoalItem constructor(
//    var goal: String,
//    var start: String,
//    var end: String,
//    var color: String,
//    var category: String,
//    var memo: String
//)
//
//data class PlanItem constructor(
//    var plan: String,
//    var start: String,
//    var end: String,
//    var andor: Boolean
//)
//
//data class TaskItem constructor(var task: String, var total: Int, var count: Int)
//
////입력된 데이터 2차 가공
//data class GoalList constructor(var goal: GoalItem, var plans: PlanUnit)
//data class PlanUnit constructor(var plans: MutableList<PlanList>)
//data class PlanList constructor(var plan: PlanItem, var taskList: TaskList)
//data class TaskList constructor(var tasks: MutableList<TaskItem>)