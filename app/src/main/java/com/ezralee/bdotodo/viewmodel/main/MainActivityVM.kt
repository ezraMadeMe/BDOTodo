package com.ezralee.bdotodo.viewmodel.main

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.ui.adapter.main.MainActivityVPAdapter
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.ui.fragment.goal.GoallistFragment
import com.ezralee.bdotodo.ui.fragment.history.HistoryFragment
import com.ezralee.bdotodo.viewmodel.history.SetHistoryActivityVM

class MainActivityVM(application: Application)
    : AndroidViewModel(application) {

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SetHistoryActivityVM(application) as T
        }
    }

    // BNV VP 포지션 연결
    val bnvItems: LiveData<List<String>> get() = _bnvItems
    val position: LiveData<Int> get() = _position

    private val _bnvItems: MutableLiveData<List<String>> = MutableLiveData()
    private val _position: MutableLiveData<Int> = MutableLiveData()

    companion object {
        private val TAB_ITEMS = listOf(
            "할 일", "히스토리", "목표"
        )
    }

    init {
        _bnvItems.postValue(TAB_ITEMS)
    }

    fun selectPosition(position: Int) {
        _position.postValue(position)
    }
}