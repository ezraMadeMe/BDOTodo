package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentPresetListBinding
import com.ezralee.bdotodo.main.GoalPresetItem

class GoalPresetListFragment(): Fragment() {
    var items: MutableList<GoalPresetItem> = mutableListOf(
        GoalPresetItem("오네트의 정령수"),
        GoalPresetItem("오도어의 정령수"),
        GoalPresetItem("라피 머시기의 나침반"),
        GoalPresetItem("고고학자의 지도"),
        GoalPresetItem("중범선-비상"),
        GoalPresetItem("크로그달로의 마구-바다")
    )
    val binding: FragmentPresetListBinding by lazy { FragmentPresetListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.goallistPresetRecycler.adapter = GoalPresetAdapter(activity as GoalPresetActivity,items)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}