package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding

class SetGoalActivity : AppCompatActivity() {
    val binding: ActivitySetGoalBinding by lazy { ActivitySetGoalBinding.inflate(layoutInflater) }
    val fragments: MutableList<Fragment> = mutableListOf(SetGoalFragment1(), SetGoalFragment2())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.setGoalPager.adapter = GoalViewPagerAdapter(supportFragmentManager, lifecycle, this@SetGoalActivity)
        //binding.setGoalPager.registerOnPageChangeCallback(pageChangeCallback)

//        binding.deleteDetailGoalPage.visibility = View.VISIBLE
//        binding.addDetailGoalPage.visibility = View.VISIBLE
//
//        binding.addDetailGoalPage.setOnClickListener {
//
//        }
//
//        binding.deleteDetailGoalPage.setOnClickListener {
//            supportFragmentManager.fragments.removeAt(binding.setGoalPager.currentItem)
//        }
    }

//      fun aaa(){
//          supportFragmentManager.fragments.removeAt(binding.setGoalPager.currentItem)
//          binding.setGoalPager.adapter?.notifyDataSetChanged()
//      }
}