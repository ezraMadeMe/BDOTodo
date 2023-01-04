package com.ezralee.bdotodo.ui.fragment.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.ui.adapter.goal.GoalAdapter
import com.ezralee.bdotodo.viewmodel.goal.MainGoalVM

//대목표 단
class GoallistFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance() =
            GoallistFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, "목표")
                    }
            }
    }
}