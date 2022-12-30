package com.ezralee.bdotodo.ui.fragment.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.repository.goal.goal.GoalDB
import com.ezralee.bdotodo.databinding.FragmentGoallistBinding
import com.ezralee.bdotodo.ui.adapter.goal.GoalAdapter
import com.ezralee.bdotodo.viewmodel.goal.MainGoalVM

//대목표 단
class GoallistFragment : Fragment() {

    private lateinit var binding: FragmentGoallistBinding
    lateinit var viewModel: MainGoalVM
    lateinit var db: GoalDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = GoalDB.getInstance(requireContext())!!
        viewModel = ViewModelProvider(this)[MainGoalVM::class.java]
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_goallist)

        binding.apply {
            lifecycleOwner = this@GoallistFragment
            viewModel = viewModel
            goalRecycler.adapter = GoalAdapter(requireContext(),
                object : GoalAdapter.OnGoalItemClickListener<GoalItem> {
                    override fun onGoalClick(data: GoalItem) {

                    }
                })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance() =
            GoallistFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, "목표")
                    }
            }
    }
}