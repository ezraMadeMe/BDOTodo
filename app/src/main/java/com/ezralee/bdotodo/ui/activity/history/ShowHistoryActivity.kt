package com.ezralee.bdotodo.ui.activity.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.databinding.ActivityShowHistoryBinding
import com.ezralee.bdotodo.viewmodel.history.HistoryVM

class ShowHistoryActivity: AppCompatActivity() {

    lateinit var binding: ActivityShowHistoryBinding
    lateinit var viewModel: HistoryVM
    lateinit var db: HistoryDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_history)
        viewModel = ViewModelProvider(this)[HistoryVM::class.java]
        db = HistoryDB.getInstance(this@ShowHistoryActivity)!!
    }
}