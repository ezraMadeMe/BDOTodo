package com.ezralee.bdotodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment() {
    var _binding: FragmentHistoryBinding? = null
    val binding get() = _binding!!
    var historyItems: Array<HistoryItem> = arrayOf(
        HistoryItem("2022/10/10"),
        HistoryItem("2022/10/12"),
        HistoryItem("2022/10/13"),
        HistoryItem("2022/10/14"),
        HistoryItem("2022/10/15")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.historyRecycler.adapter = HistoryAdapter(historyItems)

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