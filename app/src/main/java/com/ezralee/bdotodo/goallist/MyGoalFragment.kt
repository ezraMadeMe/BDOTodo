package com.ezralee.bdotodo.goallist

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.main.GoalItem
import com.ezralee.bdotodo.main.PlanItem
import com.ezralee.bdotodo.main.TaskItem

open class MyGoalFragment : Fragment() {
    companion object{
        fun newInstance(title: String): MyGoalFragment{
            val args = Bundle()

            val fragment = MyGoalFragment()
            fragment.arguments = args
            return fragment
        }
        
        open fun getGoalData(gl: String, glS: String, glE: String, clr: String, ctgr: String, mm: String): GoalItem{
            //SetGoalActivity.goalItems.add(GoalItem(gl, glS, glE, clr, ctgr, mm))
            return GoalItem(gl,glS, glE, clr,ctgr,mm)
        }

        open fun getPlanData(pln: String, plS: String, plE: String, nor: Boolean): PlanItem {
            //SetGoalActivity.planItems.add(PlanItem(pln,plS, plE, nor))
            return PlanItem(pln,plS, plE, nor)
        }

        open fun getTaskData(tsk: String, ttl: Int, cnt: Int): TaskItem {
            //SetGoalActivity.taskItems.add(TaskItem(tsk,ttl, cnt))
            return TaskItem(tsk,ttl, cnt)
        }
    }
}