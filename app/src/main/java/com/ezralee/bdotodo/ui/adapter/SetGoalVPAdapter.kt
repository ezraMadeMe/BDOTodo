package com.ezralee.bdotodo.ui.adapter

import android.content.Context
import android.database.DataSetObserver
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.databinding.ActivitySetGoalBinding
import com.ezralee.bdotodo.ui.adapter.history.HistoryAdapter
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment1
import com.ezralee.bdotodo.ui.fragment.goal.SetGoalFragment2

class SetGoalVPAdapter(var items: LiveData<ArrayList<Fragment>>,
                       fragmentManager: FragmentManager,
                       lifecycle: Lifecycle) :
            FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return items.value!!.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SetGoalFragment1.newInstance()
            1 -> SetGoalFragment2.newInstance()
            else -> SetGoalFragment2.newInstance()
        }
    }
}