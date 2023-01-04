package com.ezralee.bdotodo.ui.fragment.goal


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.ui.activity.goal.SetGoalActivity
import com.ezralee.bdotodo.ui.adapter.goal.PresetAdapter
import com.ezralee.bdotodo.viewmodel.goal.GoalPresetVM

class GoalPresetListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance(text: String) =
            GoalPresetListFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, text)
                    }
            }
    }
}