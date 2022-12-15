package com.ezralee.bdotodo.goallist

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.main.*

open class MyGoalFragment : Fragment() {
    companion object{
        fun newInstance(title: String): MyGoalFragment{
            val args = Bundle()

            val fragment = MyGoalFragment()
            fragment.arguments = args
            return fragment
        }

        var goalItem: GoalItem = GoalItem("",Info.date, Info.date, R.color.colorPicker02.toString(),"","")
        var planItem: PlanItem = PlanItem("",Info.date,Info.date,true)
        var taskItem: TaskItem = TaskItem("",0,0)

        var taskList: TaskList = getTaskList(taskItem) //한 소목표 안의 달성방법 리스트
        var planList: PlanList = getPlanList(planItem, taskList) //달성방법을 가진 한 소목표
        var planUnit: PlanUnit = getPlanUnit(planList) //소목표들의 집합
        var goalList: GoalList = getGoalList(goalItem, planUnit) //대목표(최종적으로 전달할 데이터)
        
        open fun getGoalList(goalItem: GoalItem, planUnit: PlanUnit): GoalList{
            var glist = GoalList(goalItem,planUnit)
            return glist
        }

        open fun getPlanUnit(plan: PlanList) : PlanUnit{
            var plist = plan
            var punit = PlanUnit(mutableListOf())
            punit.plans.add(plist)
            return punit
        }

        open fun getPlanList(pln: PlanItem, tsk: TaskList): PlanList {
            var plist = PlanList(pln,tsk)
            return plist
        }

        open fun getTaskList(tsk: TaskItem): TaskList {
            var titem = tsk
            var tlist = TaskList(mutableListOf())
            tlist.tasks.add(titem)
            return tlist
        }
    }//companion object
}