package com.ezralee.bdotodo.history

//히스토리
//바깥 리사이클러 날짜정보
//data class HistoryDateItem constructor(var date: String)
//안쪽 리사이클러 미리보기 정보(제목/이미지)
//data class HistoryItem constructor(var title: String, var image: Int)
//히스토리 초기 세팅 시 필요한 종합 정보
data class HistoryItem constructor(var title: String, var date: String, var category: String, var image: Int, var memo: String)

//데일리
//
data class DailyTodoItem constructor(var color: Int, var todo: String, var title: String, var dday: Int, var percent: Int)

data class AddTodoItem constructor(var date: String, var task: String, var count: Int)

//목표
data class GoalPresetItem constructor(var title: String)
data class GoalListItem constructor(var color: Int, var title: String, var percent: Int, var dday: Int)
data class GoalListInnerItem constructor(var title: String, var percent: Int)
