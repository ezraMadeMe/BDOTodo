package com.ezralee.bdotodo.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.main.DailyTodoItem
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentDaliyBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

class DailyFragment : Fragment() {
    var _binding: FragmentDaliyBinding? = null
    val binding get() = _binding!!

    var items: MutableList<DailyTodoItem> = mutableListOf(
        DailyTodoItem(R.color.colorPicker01,"소목표1","대목표1",22,80),
        DailyTodoItem(R.color.colorPicker01,"소목표1","대목표1",22,80),
        DailyTodoItem(R.color.colorPicker01,"소목표1","대목표1",22,80),
        DailyTodoItem(R.color.colorPicker01,"소목표1","대목표1",22,80),
        DailyTodoItem(R.color.colorPicker01,"소목표1","대목표1",22,80)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDaliyBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.dailyCalendarMonth.selectedDate = CalendarDay.today()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dailyRecycler.adapter = DailyAdapter(requireContext(), items)
        binding.addTodo.setOnClickListener{
            DailyAddTodoFragment().show(parentFragmentManager,"daily add todo")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}