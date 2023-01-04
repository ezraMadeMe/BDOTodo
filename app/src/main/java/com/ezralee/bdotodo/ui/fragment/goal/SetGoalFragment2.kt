package com.ezralee.bdotodo.ui.fragment.goal

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.TaskData
import com.ezralee.bdotodo.databinding.FragmentSetGoal2Binding
import com.ezralee.bdotodo.ui.adapter.goal.TaskAdapter
import com.ezralee.bdotodo.ui.adapter.goal.TaskAdapter.OnGoalItemClickListener
import com.ezralee.bdotodo.viewmodel.goal.SetGoalVM

class SetGoalFragment2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }//onCreate

    companion object {
        fun newInstance() =
            SetGoalFragment2().apply {
                arguments =
                    Bundle().apply {
                        putString("any", "새 페이지 생성")
                    }
            }
    }
}