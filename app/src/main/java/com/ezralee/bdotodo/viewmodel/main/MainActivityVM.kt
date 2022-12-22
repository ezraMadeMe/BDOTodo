package com.ezralee.bdotodo.viewmodel.main

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.ui.adapter.main.MainActivityVPAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment
import com.ezralee.bdotodo.viewmodel.history.SetHistoryActivityVM

class MainActivityVM(application: Application, contract: MainActivityContract)
    : AndroidViewModel(application) {

    interface MainActivityContract {
        fun getFragmentManager() : FragmentManager
        fun getLifecycle() : Lifecycle
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SetHistoryActivityVM(application) as T
        }
    }

    var adapter = MainActivityVPAdapter(
        contract.getFragmentManager(),
        contract.getLifecycle(),
        listOf(DailyFragment.newInstance(),
        HistoryFragment.newInstance(),
        GoallistFragment.newInstance()
        )
    )

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