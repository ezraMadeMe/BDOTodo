package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding
import com.ezralee.bdotodo.main.*

class SetGoalActivity : AppCompatActivity() {
    val binding: ActivitySetGoalBinding by lazy { ActivitySetGoalBinding.inflate(layoutInflater) }
    lateinit var viewModel: SharedSetGoalVM

    var items: MutableList<Fragment> = mutableListOf()
    lateinit var newTaskList: TaskList
    lateinit var newPlan: PlanItem
    lateinit var newPlanList: PlanList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //뷰모델
        viewModel = ViewModelProvider(this).get(SharedSetGoalVM::class.java)
        //observe를 통해 liveData가 바뀔때마다 체크하는 함수
        viewModel.selectedData.observe(this, Observer {

        })

        fragmentAdapt()
        addData()

        binding.goalDone.setOnClickListener{
            MyVPListener()
            finish()
        }

    }

    fun fragmentAdapt(){
        items.add(SetGoalFragment1())
        items.add(SetGoalFragment2())
        binding.setGoalPager.adapter = GoalViewPagerAdapter(items, this@SetGoalActivity, supportFragmentManager, lifecycle)
        binding.setGoalPager.getChildAt(binding.setGoalPager.currentItem)
    }

    fun addData(){
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