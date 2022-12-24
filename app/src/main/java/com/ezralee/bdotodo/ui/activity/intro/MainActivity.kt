package com.ezralee.bdotodo.ui.activity.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.BR
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityMainBinding
import com.ezralee.bdotodo.viewmodel.main.MainActivityVM

class MainActivity : AppCompatActivity() {

    private val viewModel = createVM()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).setVariable(BR._all, viewModel)
    } //onCreate

    private fun createVM() = MainActivityVM(object : MainActivityVM.MainActivityContract {
        override fun getFragmentManager(): FragmentManager {
            return supportFragmentManager
        }
    })
}