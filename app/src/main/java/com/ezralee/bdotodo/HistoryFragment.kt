package com.ezralee.bdotodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    var _binding: FragmentHistoryBinding? = null
    val binding get() = _binding!!
    var historyDateItems: Array<HistoryDateItem> = arrayOf(
        HistoryDateItem("2022/10/10"),
        HistoryDateItem("2022/10/12"),
        HistoryDateItem("2022/10/13"),
        HistoryDateItem("2022/10/14"),
        HistoryDateItem("2022/10/15")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.historyRecycler.adapter = HistoryAdapter(requireContext(),historyDateItems)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyBtn.setOnClickListener {
            //ShowHistoryDetailActivity().show(parentFragmentManager, "History Dialog")

            SetHistoryActivity().show(parentFragmentManager,"set history")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}