package com.ezralee.bdotodo.viewmodel.daily

class SetDailyActivityVM {

    val adapter

    //userId로 쿼리한 goal의 이름 목록
    val goalList

    //유저가 선택한 goal의 이름
    fun selectedGoal(){

    }

    //selectedGoal로 쿼리한 plan의 이름 목록
    val planList

    //유저가선택한 plan의 이름
    fun selectedPlan(){

    }

    //selectedPlan으로 쿼리한 task의 이름 목록
    val taskList

    //유저가 선택한 task의 이름
    fun selectedTask(){

    }

    //선택한 task의 달성량
    fun setCount(){
        //number picker 실행
    }

    fun setToday(){

    }

    fun close(){

    }

    fun add(){

    }

    fun commit(){

    }
}