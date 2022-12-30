package com.ezralee.bdotodo.ui.activity.goal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.repository.goal.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.plan.PlanDB
import com.ezralee.bdotodo.data.repository.goal.task.TaskDB
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding
import com.ezralee.bdotodo.ui.adapter.SetGoalVPAdapter
import com.ezralee.bdotodo.viewmodel.goal.SetGoalVM

class SetGoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetGoalBinding
    private lateinit var viewModel: SetGoalVM
    private lateinit var gdb: GoalDB
    private lateinit var pdb: PlanDB
    private lateinit var tdb: TaskDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gdb = GoalDB.getInstance(this@SetGoalActivity)!!
        pdb = PlanDB.getInstance(this@SetGoalActivity)!!
        tdb = TaskDB.getInstance(this@SetGoalActivity)!!

        viewModel = ViewModelProvider(this)[SetGoalVM::class.java]
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

        binding.apply {
            goalDone.setOnClickListener {
                this@SetGoalActivity.finish()
            }
            goalCancel.setOnClickListener {
                this@SetGoalActivity.finish()
            }
        }
    }//onCreate
}