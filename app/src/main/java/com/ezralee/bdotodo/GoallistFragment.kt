package com.ezralee.bdotodo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentGoallistBinding

class GoallistFragment : Fragment() {
    val binding: FragmentGoallistBinding by lazy { FragmentGoallistBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.layoutPresetFAB.visibility = View.GONE
        binding.layoutMyselfFAB.visibility = View.GONE

        binding.layoutMyselfFAB.setOnClickListener {
            startActivity(Intent(activity,SetGoalFragment::class.java))
        }

        binding.layoutPresetFAB.setOnClickListener {
            startActivity(Intent(activity,GoalPresetActivity::class.java))
        }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}