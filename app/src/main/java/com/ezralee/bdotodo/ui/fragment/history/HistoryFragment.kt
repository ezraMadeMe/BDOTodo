package com.ezralee.bdotodo.ui.fragment.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.ui.adapter.history.HistoryAdapter
import com.ezralee.bdotodo.viewmodel.history.HistoryVM


class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding
    lateinit var viewModel: HistoryVM
    lateinit var db: HistoryDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = HistoryDB.getInstance(requireContext())!!
        viewModel = ViewModelProvider(this)[HistoryVM::class.java]
        binding = DataBindingUtil
            .setContentView(requireActivity(),R.layout.fragment_history)
        binding.apply {
            historyRecycler.adapter = HistoryAdapter(requireContext())
            lifecycleOwner = this@HistoryFragment
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

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance() =
            HistoryFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, "히스토리")
                    }
            }
    }
}