package com.ezralee.bdotodo.ui.activity.daily

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment.STYLE_NO_TITLE
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.TaskAccureData
import com.ezralee.bdotodo.data.repository.goal.daily.DailyDB
import com.ezralee.bdotodo.databinding.ActivityDailyAddTodoBinding
import com.ezralee.bdotodo.ui.adapter.daily.AddTodoAdapter
import com.ezralee.bdotodo.viewmodel.daily.MainDailyVM

class DailyAddTodoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDailyAddTodoBinding
    private lateinit var viewModel: MainDailyVM
    private lateinit var db: DailyDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = DailyDB.getInstance(this@DailyAddTodoActivity)!!
        viewModel = ViewModelProvider(this)[MainDailyVM::class.java]
        binding = DataBindingUtil
            .setContentView(this@DailyAddTodoActivity,R.layout.activity_daily_add_todo)

        binding.apply {
            lifecycleOwner = this@DailyAddTodoActivity
            viewModel = viewModel
            this.todoListToAddRecycler.adapter = AddTodoAdapter(
                viewModel, this@DailyAddTodoActivity,
                object : AddTodoAdapter.OnDailyItemClickListener<TaskAccureData>{
                    override fun onDeleteDataClick(position: Int) {
                        viewModel.deleteData(position)
                    }
                }
            )
            todoCancel.setOnClickListener { this@DailyAddTodoActivity.finish() }
            close.setOnClickListener { this@DailyAddTodoActivity.finish() }
        }
    }
}