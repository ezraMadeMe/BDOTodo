package com.ezralee.bdotodo.goallist

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding

class SetGoalActivity : AppCompatActivity() {
    val binding: ActivitySetGoalBinding by lazy { ActivitySetGoalBinding.inflate(layoutInflater) }
    var adapter = GoalViewPagerAdapter(supportFragmentManager, lifecycle, this@SetGoalActivity)
    var items: MutableList<Fragment> = mutableListOf(SetGoalFragment1(),SetGoalFragment2())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.setGoalPager.adapter = adapter

        binding.setGoalPager.getChildAt(binding.setGoalPager.currentItem)
    }

    fun addPage() {
        //supportFragmentManager.fragments.add(SetGoalFragment2())
        items.add(SetGoalFragment2())
        binding.setGoalPager.adapter?.notifyDataSetChanged()
        Toast.makeText(this, items.size.toString(), Toast.LENGTH_SHORT).show()
    }

    fun deletePage() {
        if (binding.setGoalPager.childCount == 2){
            Toast.makeText(this, "소목표는 하나 이상 생성해야 합니다.", Toast.LENGTH_LONG).show()
        }else{
            items.removeAt(binding.setGoalPager.currentItem)
            binding.setGoalPager.adapter?.notifyDataSetChanged()
            Toast.makeText(this, items.size.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}