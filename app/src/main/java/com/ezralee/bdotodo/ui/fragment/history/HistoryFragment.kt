package com.ezralee.bdotodo.ui.fragment.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ezralee.bdotodo.BR
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.viewmodel.history.MainHistoryVM


class HistoryFragment : Fragment() {

    private val viewModel = createVM()
    lateinit var db: HistoryDB

    var items: MutableList<HistoryData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = HistoryDB.getInstance(requireContext())!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<FragmentHistoryBinding>(inflater,
            R.layout.fragment_history,
            container,
            false)
        binding.setVariable(BR._all,viewModel)

        return binding.root
    }

    private fun createVM() = MainHistoryVM(object : MainHistoryVM.MainHistoryContract {
        override fun getFragmentManager(): FragmentManager {
            return parentFragmentManager
        }
    })


    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance() =
            DailyFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, "히스토리")
                    }
            }
    }
}