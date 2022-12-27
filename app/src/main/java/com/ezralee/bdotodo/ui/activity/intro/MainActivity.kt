package com.ezralee.bdotodo.ui.activity.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityMainBinding
import com.ezralee.bdotodo.ui.adapter.MainVPAdapter
import com.ezralee.bdotodo.viewmodel.main.MainVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainVM::class.java]
        binding = DataBindingUtil
            .setContentView(this@MainActivity, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = viewModel
            mainVP.adapter = MainVPAdapter(supportFragmentManager, lifecycle)
        }
    } //onCreate
}