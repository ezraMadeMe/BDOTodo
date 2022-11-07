package com.ezralee.bdotodo

data class HistoryItem constructor(var date: String)
data class DailyTodoItem constructor(var color: Int, var todo: String, var title: String, var dday: Int, var percent: Int)
data class AddTodoItem constructor(var date: String, var task: String, var count: Int)
data class GoalPresetItem constructor(var title: String)