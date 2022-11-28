package com.ezralee.bdotodo.goallist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.history.GoalListItem
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentGoallistBinding
import com.google.firebase.firestore.FirebaseFirestore

//대목표 단
class GoallistFragment : Fragment() {
    val binding: FragmentGoallistBinding by lazy { FragmentGoallistBinding.inflate(layoutInflater) }
    var items: MutableList<GoalListItem> = mutableListOf(
        GoalListItem(R.color.colorPicker01,"오도어의 정령수",20, 23,false),
        GoalListItem(R.color.colorPicker01,"오도어의 정령수",20, 23,false),
        GoalListItem(R.color.colorPicker01,"오도어의 정령수",20, 23,false),
        GoalListItem(R.color.colorPicker01,"오도어의 정령수",20, 23,false),
        GoalListItem(R.color.colorPicker01,"오도어의 정령수",20, 23,false)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.layoutPresetFAB.visibility = View.GONE
        binding.layoutMyselfFAB.visibility = View.GONE
        binding.finalGoalRecycler.adapter = GoallistAdapter(requireContext(),items)

//        binding.layoutMyselfFAB.setOnClickListener {
//            startActivity(Intent(activity,SetGoalActivity::class.java))
//        }
//
//        binding.layoutPresetFAB.setOnClickListener {
//            startActivity(Intent(activity,GoalPresetActivity::class.java))
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goallistMainFAB.setOnClickListener{

            if (binding.layoutPresetFAB.visibility == View.GONE){
                binding.layoutPresetFAB.visibility = View.VISIBLE
                binding.layoutMyselfFAB.visibility = View.VISIBLE
            }else{
                binding.layoutPresetFAB.visibility = View.GONE
                binding.layoutMyselfFAB.visibility = View.GONE
            }
        }

        binding.layoutMyselfFAB.setOnClickListener {
            startActivity(Intent(activity, SetGoalActivity::class.java))
        }

        binding.layoutPresetFAB.setOnClickListener {
            startActivity(Intent(activity, GoalPresetActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}