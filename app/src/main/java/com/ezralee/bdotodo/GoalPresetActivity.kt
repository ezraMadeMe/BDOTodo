package com.ezralee.bdotodo

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.ActivityGoalPresetBinding

class GoalPresetActivity: AppCompatActivity() {

    val binding: ActivityGoalPresetBinding by lazy { ActivityGoalPresetBinding.inflate(layoutInflater) }
    var goalFragments: MutableList<Fragment> = mutableListOf(SetGoalFragment1(),SetGoalFragment2())
    var items : MutableList<GoalPresetItem> = mutableListOf(
        GoalPresetItem("오네트의 정령수"),
        GoalPresetItem("오도어의 정령수"),
        GoalPresetItem("라피 머시기의 나침반"),
        GoalPresetItem("고고학자의 지도"),
        GoalPresetItem("중범선-비상"),
        GoalPresetItem("크로그달로의 마구-바다")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goallistPresetRecycler.adapter = GoalPresetAdapter(this@GoalPresetActivity,items)

    }

}