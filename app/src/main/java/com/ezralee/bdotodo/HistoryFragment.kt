package com.ezralee.bdotodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.databinding.HistoryRecyclerItemBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment() {
    val items: MutableList<HistoryItem> = mutableListOf<HistoryItem>()
    lateinit var recyclerItemBinding: HistoryRecyclerItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_history,container, false)
        items.add(HistoryItem("2022/10/28"))
        recyclerItemBinding = HistoryRecyclerItemBinding.bind(rootView)


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}