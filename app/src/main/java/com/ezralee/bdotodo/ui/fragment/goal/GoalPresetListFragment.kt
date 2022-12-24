package com.ezralee.bdotodo.ui.fragment.goal


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.BR
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentPresetListBinding
import com.ezralee.bdotodo.viewmodel.goal.GoalPresetFragmentVM

class GoalPresetListFragment : Fragment() {
    private val viewModel = createVM()
    private var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_TEXT) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil
                .inflate<FragmentPresetListBinding>(inflater,
                    R.layout.fragment_preset_list,
                    container,
                    false)
        binding.setVariable(BR._all, viewModel)

        return binding.root
    }

    private fun createVM() = GoalPresetFragmentVM(object : GoalPresetFragmentVM.GoalPresetFragmentContract {

    })

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