package com.ezralee.bdotodo.ui.fragment.goal

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.databinding.FragmentGoallistBinding
import com.ezralee.bdotodo.ui.activity.goal.GoalPresetActivity
import com.ezralee.bdotodo.ui.activity.goal.SetGoalActivity
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.viewmodel.goal.MainGoalVM

//대목표 단
class GoallistFragment : Fragment() {

    lateinit var viewModel: MainGoalVM
    private var _binding: FragmentGoallistBinding? = null
    val binding: FragmentGoallistBinding get() = _binding!!
    lateinit var db: GoalDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_history)
        viewModel =
            ViewModelProvider(
                this,
                MainGoalVM.Factory(requireContext() as Application)
            )[MainGoalVM::class.java]

        db = GoalDB.getInstance(requireContext())!!

        binding.apply {
            lifecycleOwner = this@GoallistFragment
            viewModel = viewModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goallistMainFAB.setOnClickListener {

            if (binding.layoutPresetFAB.visibility == View.GONE) {
                binding.layoutPresetFAB.visibility = View.VISIBLE
                binding.layoutMyselfFAB.visibility = View.VISIBLE
            } else {
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

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance() =
            DailyFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, "목표")
                    }
            }
    }
}