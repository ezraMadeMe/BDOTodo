package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentPresetListBinding
import com.ezralee.bdotodo.main.GoalItem

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