package com.ezralee.bdotodo.ui.activity.goal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.repository.goal.PresetDB
import com.ezralee.bdotodo.databinding.ActivityGoalPresetBinding
import com.ezralee.bdotodo.ui.adapter.goal.PresetVPAdapter
import com.ezralee.bdotodo.ui.fragment.goal.GoalPresetListFragment
import com.ezralee.bdotodo.viewmodel.goal.GoalPresetVM

class GoalPresetActivity : AppCompatActivity() {

    lateinit var binding: ActivityGoalPresetBinding
    lateinit var viewModel: GoalPresetVM
    lateinit var db: PresetDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_goal_preset)
        viewModel = ViewModelProvider(this)[GoalPresetVM::class.java]
        db = PresetDB.getInstance(this@GoalPresetActivity)!!

        binding.apply {
            lifecycleOwner = this@GoalPresetActivity
            viewModel = viewModel
            presetVP.adapter = PresetVPAdapter(
                PRESET_FRAG,
                supportFragmentManager,
                lifecycle
            )
        }
    }

    companion object {
        val PRESET_FRAG = listOf(
            GoalPresetListFragment.newInstance("보물"),
            GoalPresetListFragment.newInstance("생활"),
            GoalPresetListFragment.newInstance("장비")
        )
    }
}