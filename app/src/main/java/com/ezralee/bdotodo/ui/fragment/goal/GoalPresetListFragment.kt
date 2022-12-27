package com.ezralee.bdotodo.ui.fragment.goal


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.model.GoalItem
import com.ezralee.bdotodo.data.repository.goal.GoalDB
import com.ezralee.bdotodo.data.repository.goal.PresetDB
import com.ezralee.bdotodo.databinding.FragmentPresetListBinding
import com.ezralee.bdotodo.ui.activity.goal.SetGoalActivity
import com.ezralee.bdotodo.ui.adapter.goal.PresetAdapter
import com.ezralee.bdotodo.viewmodel.goal.GoalPresetVM

class GoalPresetListFragment : Fragment() {

    lateinit var binding: FragmentPresetListBinding
    lateinit var viewModel: GoalPresetVM
    lateinit var db: PresetDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = PresetDB.getInstance(requireContext())!!
        viewModel = ViewModelProvider(this)[GoalPresetVM::class.java]
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_preset_list)

        binding.apply {
            lifecycleOwner = this@GoalPresetListFragment
            viewModel = viewModel
            goalPresetRecycler.adapter = PresetAdapter( requireContext(),
            object : PresetAdapter.OnPresetClickListener<GoalItem>{
                override fun onPresetClick(data: GoalItem) {
                    data.apply {
                        //클릭한 프리셋에 세팅된 데이터를 setgoal 에 넘겨줌
                    }
                    startActivity(Intent(requireContext(),SetGoalActivity::class.java))
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

        fun newInstance(text: String) =
            GoalPresetListFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, text)
                    }
            }
    }
}