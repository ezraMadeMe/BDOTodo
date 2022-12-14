package com.ezralee.bdotodo.goallist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
    lateinit var newTaskList: TaskList
    lateinit var newPlan: PlanItem
    lateinit var newPlanList: PlanList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //목표 수정 시 Intent 될 곳
        binding.setGoalPager.adapter = GoalViewPagerAdapter(items, this@SetGoalActivity, supportFragmentManager, lifecycle)
        binding.setGoalPager.getChildAt(binding.setGoalPager.currentItem)

        //소목표 fragment가 추가될 때마다 데이터 추가시키기
        newTaskList = MyGoalFragment.getTaskList(MyGoalFragment.taskItem)
        newPlan = MyGoalFragment.planItem
        newPlanList = MyGoalFragment.getPlanList(newPlan,newTaskList)
        MyGoalFragment.planUnit = MyGoalFragment.getPlanUnit(newPlanList)
        Toast.makeText(this@SetGoalActivity,""+MyGoalFragment.planList.taskList.tasks.size, Toast.LENGTH_SHORT).show()
    }


    //뷰페이저 동적 추가
    fun addPage() {
        items.add(SetGoalFragment2())
        binding.setGoalPager.adapter?.notifyItemInserted(items.size)
        binding.setGoalPager.currentItem = items.lastIndex
        Toast.makeText(this, items.size.toString(), Toast.LENGTH_SHORT).show()
        //새로운 PlanItem, TaskList, PlanList 생성
        //소목표 fragment가 추가될 때마다 데이터 추가시키기
        newTaskList = MyGoalFragment.getTaskList(MyGoalFragment.taskItem)
        newPlan = MyGoalFragment.planItem
        newPlanList = MyGoalFragment.getPlanList(newPlan,newTaskList)
        MyGoalFragment.planUnit = MyGoalFragment.getPlanUnit(newPlanList)
        Log.i("@@@@PLAN",""+newPlan.plan.length)
    }

    //뷰페이저 동적 제거
    fun deletePage() {
        if (items.size <= 2) {
            Toast.makeText(this, "소목표는 하나 이상 생성해야 합니다.", Toast.LENGTH_LONG).show()
        } else {
            items.removeAt(binding.setGoalPager.currentItem)
            binding.setGoalPager.adapter?.notifyDataSetChanged()
            Toast.makeText(this, items.size.toString(), Toast.LENGTH_SHORT).show()
            //해당 번째 데이터 지우기
            MyGoalFragment.planUnit.plans.removeAt(binding.setGoalPager.currentItem-1)
        }
    }
}