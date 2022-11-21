package com.ezralee.bdotodo.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment() {
    var _binding: FragmentHistoryBinding? = null
    val binding get() = _binding!!

    val now = System.currentTimeMillis()
    val date = Date(now)
    val sdf = SimpleDateFormat("yyyy/MM/dd")
    val createdDate = sdf.format(date)

    var historyItems: Array<HistoryItem> = arrayOf(
        HistoryItem("앱 생성일",createdDate,"카테고리11", R.drawable.img_sample,"메모메모메모")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.historyRecycler.adapter = HistoryAdapter(requireContext(),historyItems)

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