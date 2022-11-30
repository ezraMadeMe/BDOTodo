package com.ezralee.bdotodo.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.main.HistoryItem
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment() {
    val binding: FragmentHistoryBinding by lazy { FragmentHistoryBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyBtn.setOnClickListener {
            //ShowHistoryDetailActivity().show(parentFragmentManager, "History Dialog")

            SetHistoryActivity().show(parentFragmentManager,"set history")

        }
    }
}