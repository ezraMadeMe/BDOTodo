package com.ezralee.bdotodo.viewmodel.daily

import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.ui.adapter.daily.DailyRecyclerAdapter

class MainDailyVM(private val contract: DailyFragmentContract) {
    interface DailyFragmentContract {
        fun selectDailyAccure(){ }
        fun showDetail(){ }
    }

    val today = Info.date

    val adapter = DailyRecyclerAdapter(
        id = R.layout.daily_recycler_item,
        items = listOf<GoalItem>(),
        listener = this
    )

    //한 일/할 일 추가 버튼 클릭 시 Set액티비티 발동
    fun addTask(){

    }

    //할 일 클릭시 완료 처리
    fun complete(){

    }

    //할 일 롱클릭시 수정/자세히보기/삭제 여부 묻는 다이얼로그 팝업
    fun additionalWork(){

    }

    fun setToday(){

    }

    fun setCount(){

    }

    fun deleteTask(){

    }

    //클릭이벤트에 반응하는 캘린더뷰
    fun selectDailyAccure(selDate: String){
        contract.selectDailyAccure()
        // 클릭한 캘린더의 날짜를 파라미터로 받아옴
        // 클릭한 날짜로 goalList 쿼리
        // 리사이클러뷰에 선택한 캘린더의 날짜에 등록한 한 일/할 일 목록 쿼리 결과 출력
        // 목표 색상/달성 방법/대목표/(마감일-오늘 날짜)/(소목표 목표개수-현재개수)
    }

    //클릭이벤트에 반응하는 리사이클러뷰
    fun showDetailTodo(selectedDate: String){
        contract.showDetail()
        //
    }
}