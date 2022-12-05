package com.ezralee.bdotodo.goallist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding
import com.ezralee.bdotodo.main.GoalItem
import com.ezralee.bdotodo.main.KakaoLogin
import com.ezralee.bdotodo.main.PlanItem
import com.ezralee.bdotodo.main.TaskItem

class SetGoalActivity : AppCompatActivity() {
    val binding: ActivitySetGoalBinding by lazy { ActivitySetGoalBinding.inflate(layoutInflater) }
    var items: MutableList<Fragment> = mutableListOf(SetGoalFragment1(), SetGoalFragment2())
    var adapter =
        GoalViewPagerAdapter(items, this@SetGoalActivity, supportFragmentManager, lifecycle)

    var goalItems: MutableList<GoalItem> = mutableListOf()
    var planItems: MutableList<PlanItem> = mutableListOf()
    var taskItems: MutableList<TaskItem> = mutableListOf()

    companion object{
        //goal
        const val GOAL = "goal"
        const val GOALSTART = "goalStart"
        const val GOALEND = "goalEnd"
        const val COLOR = "color"
        const val CATEGORY = "category"
        const val MEMO = "memo"
        //plan
        const val PLAN = "plan"
        const val PLANSTART = "planStart"
        const val PLANEND = "planEnd"
        const val ANDOR = "andor"
        //task
        const val TASK = "task"
        const val TOTAL = "total"

        fun newGoalInstance(gl: String, glS: String, glE: String, clr: String, ctgr: String, mm: String) =
            SetGoalFragment2().apply {
                arguments = bundleOf(
                    GOAL to gl,
                    GOALSTART to glS,
                    GOALEND to glE,
                    COLOR to clr,
                    CATEGORY to ctgr,
                    MEMO to mm
                )
            }

        fun newTaskInstance(tsk: String, ttl: Int) =
            SetGoalFragment2().apply {
                arguments = bundleOf(
                    TASK to tsk,
                    TOTAL to ttl
                )
            }

        fun findFragment2(fm: FragmentManager, @IdRes id: Int) : SetGoalFragment2? {
            return fm.findFragmentById(id) as? SetGoalFragment2
        }

        fun findFragment1(fm: FragmentManager, @IdRes id: Int) : SetGoalFragment1? {
            return fm.findFragmentById(id) as? SetGoalFragment1
        }
    }//companion object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //목표 수정 시 Intent 될 곳
        binding.setGoalPager.adapter = adapter
        binding.setGoalPager.getChildAt(binding.setGoalPager.currentItem)
        binding.goalDone.setOnClickListener { postGoalRetrofit() }
    }

    override fun onResume() {
        super.onResume()

    }

    //생성된 goal/plan/task개수만큼 객체 생성
    fun postGoalRetrofit() {
        binding.setGoalPager.adapter
        this.finish()
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