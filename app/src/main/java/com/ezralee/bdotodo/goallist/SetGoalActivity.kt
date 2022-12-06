package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding
import com.ezralee.bdotodo.main.*

class SetGoalActivity : AppCompatActivity() {
    val binding: ActivitySetGoalBinding by lazy { ActivitySetGoalBinding.inflate(layoutInflater) }
    var items: MutableList<Fragment> = mutableListOf(SetGoalFragment1(), SetGoalFragment2())
    var adapter =
        GoalViewPagerAdapter(items, this@SetGoalActivity, supportFragmentManager, lifecycle)

    companion object{
        var goalItem: GoalItem = SetGoalFragment1().goalData()
        var planItem: PlanItem = SetGoalFragment2().planData()
        var taskItem: MutableList<TaskItem> = mutableListOf()

        var taskList: TaskList = TaskList(taskItem) //한 소목표 안의 달성방법 리스트
        var planList: PlanList = PlanList(planItem,taskList) //달성방법을 가진 한 소목표
        var planUnit: PlanUnit = PlanUnit(mutableListOf(planList)) //소목표들의 집합
        var goalList: GoalList = GoalList(goalItem,planUnit) //대목표(최종적으로 전달할 데이터)
    }//companion object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //목표 수정 시 Intent 될 곳
        binding.setGoalPager.adapter = adapter
        binding.setGoalPager.getChildAt(binding.setGoalPager.currentItem)
    }


    //뷰페이저 동적 추가
    fun addPage() {
        items.add(SetGoalFragment2())
        binding.setGoalPager.adapter?.notifyItemInserted(items.size)
        binding.setGoalPager.currentItem = items.lastIndex
        Toast.makeText(this, items.size.toString(), Toast.LENGTH_SHORT).show()
    }

    //뷰페이저 동적 제거
    fun deletePage() {
        if (items.size <= 2) {
            Toast.makeText(this, "소목표는 하나 이상 생성해야 합니다.", Toast.LENGTH_LONG).show()
        } else {
            items.removeAt(binding.setGoalPager.currentItem)
            binding.setGoalPager.adapter?.notifyDataSetChanged()
            Toast.makeText(this, items.size.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}