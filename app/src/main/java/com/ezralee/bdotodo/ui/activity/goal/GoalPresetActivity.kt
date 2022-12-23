package com.ezralee.bdotodo.ui.activity.goal

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityGoalPresetBinding
import com.ezralee.bdotodo.ui.adapter.goal.GoalPresetViewPagerAdapter
import com.ezralee.bdotodo.viewmodel.goal.GoalPresetActivityVM
import com.google.android.material.bottomnavigation.BottomNavigationView

class GoalPresetActivity : AppCompatActivity() {

    lateinit var binding: ActivityGoalPresetBinding
    lateinit var viewModel: GoalPresetActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_goal_preset)
        viewModel = ViewModelProvider(this)[GoalPresetActivityVM::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

//        binding.presetBNV.setOnNavigationItemSelectedListener(this)
//        binding.presetVP.adapter = GoalPresetViewPagerAdapter(supportFragmentManager, lifecycle)
//        binding.presetVP.registerOnPageChangeCallback(ViewPagerPageChangeCallback())
//
//        binding.close.setOnClickListener {
//            finish()
//        }

    }

//    //선택된 BNV 감지하는 리스너 오버라이딩
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.menu_treasure -> {
//                binding.presetVP.currentItem = 0
//                true
//            }
//            R.id.menu_lifeskill -> {
//                binding.presetVP.currentItem = 1
//                true
//            }
//            R.id.menu_gear -> {
//                binding.presetVP.currentItem = 2
//                true
//            }
//            else -> false
//        }
//    }
//
//    //ViewPager의 페이지 변화 감지 콜백메서드 오버라이딩
//    inner class ViewPagerPageChangeCallback : ViewPager2.OnPageChangeCallback() {
//        override fun onPageSelected(position: Int) {
//            binding.presetBNV.selectedItemId = when (position) {
//                0 -> R.id.menu_treasure
//                1 -> R.id.menu_lifeskill
//                2 -> R.id.menu_gear
//                else -> error("no menu")
//            }
//        }
//    }

}