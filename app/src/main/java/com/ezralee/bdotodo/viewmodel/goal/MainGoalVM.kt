package com.ezralee.bdotodo.viewmodel.goal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.viewmodel.history.SetHistoryActivityVM

class MainGoalVM(application: Application): AndroidViewModel(application) {

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SetHistoryActivityVM(application) as T
        }
    }

    //최상위 리사이클러뷰 아답터
    val goalAdapter

    //소목표단 리사이클러뷰 아답터
    val planAdapter

    //task단 리사이클러뷰 아답터
    val taskAdapter

    val today = Info.date

    fun showDetail(){

    }

    fun arrowAnimate(){

    }

    //메인 FAB 클릭시 작은 FAB 2개 팝업
    fun popupFAB(){

    }

    fun openPreset(){

    }

    fun openMyself(){

    }

    fun colorPick(){

    }

    fun setCategory(){

    }

    var currentPosition = 0

    var pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
//            super.onPageScrollStateChanged(state)
        }

        override fun onPageSelected(position: Int) {
            currentPosition = position
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
//            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    }
}