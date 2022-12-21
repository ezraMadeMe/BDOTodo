package com.ezralee.bdotodo.ui.fragment.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentPresetListBinding
import com.ezralee.bdotodo.main.GoalItem
import com.ezralee.bdotodo.ui.adapter.goal.GoalPresetAdapter
import com.ezralee.bdotodo.ui.activity.goal.GoalPresetActivity

class GoalPresetListFragment(): Fragment() {
    val items: MutableList<GoalItem> by lazy { mutableListOf() }
    val binding: FragmentPresetListBinding by lazy { FragmentPresetListBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.goallistPresetRecycler.adapter = GoalPresetAdapter(activity as GoalPresetActivity,items)

        return binding.root
    }
}