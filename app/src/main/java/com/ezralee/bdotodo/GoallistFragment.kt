package com.ezralee.bdotodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentGoallistBinding

class GoallistFragment : Fragment() {
    var _binding: FragmentGoallistBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoallistBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.layoutPresetFAB.visibility = View.GONE
        binding.layoutMyselfFAB.visibility = View.GONE

        return view
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
        _binding = null
    }
}