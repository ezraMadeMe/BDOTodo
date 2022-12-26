package com.ezralee.bdotodo.ui.activity.goal

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding
import com.ezralee.bdotodo.ui.adapter.SetGoalVPAdapter
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2
import com.ezralee.bdotodo.viewmodel.goal.SetGoalActivityVM

class SetGoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetGoalBinding
    private lateinit var viewModel: SetGoalActivityVM
    private lateinit var db: GoalDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = GoalDB.getInstance(this@SetGoalActivity)!!
        viewModel = ViewModelProvider(this)[SetGoalActivityVM::class.java]
        binding = DataBindingUtil
            .setContentView(this@SetGoalActivity, R.layout.activity_set_goal)

        binding.apply {
            lifecycleOwner = this@SetGoalActivity
            viewModel = viewModel
            setGoalPager.adapter = SetGoalVPAdapter(
                            viewModel.fragments,
                            supportFragmentManager,
                            lifecycle)
        }
    }
}