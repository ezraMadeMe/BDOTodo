package com.ezralee.bdotodo.viewmodel.daily

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.AccureItem

class SetDailyActivityVM(application: Application): AndroidViewModel(application) {

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SetDailyActivityVM(application) as T
        }
    }

    val adapter = AddTodoAdapter(
        id = R.layout.add_todo_recycler_item,
        items = listOf<AccureItem>(),
        listener = this
    )

}