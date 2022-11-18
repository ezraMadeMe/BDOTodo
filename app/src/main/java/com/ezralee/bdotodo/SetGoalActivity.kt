package com.ezralee.bdotodo

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.indices
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding

class SetGoalActivity : AppCompatActivity() {
    val binding: ActivitySetGoalBinding by lazy { ActivitySetGoalBinding.inflate(layoutInflater) }
    val fragments: MutableList<Fragment> = mutableListOf(SetGoalFragment1(), SetGoalFragment2())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.setGoalPager.adapter = GoalViewPagerAdapter(supportFragmentManager, lifecycle, this@SetGoalActivity)
        //binding.setGoalPager.registerOnPageChangeCallback(pageChangeCallback)

        binding.deleteDetailGoalPage.visibility = View.VISIBLE
        binding.addDetailGoalPage.visibility = View.VISIBLE

        binding.addDetailGoalPage.setOnClickListener {

        }

        binding.deleteDetailGoalPage.setOnClickListener {
            supportFragmentManager.fragments.removeAt(binding.setGoalPager.currentItem)
        }
    }
}