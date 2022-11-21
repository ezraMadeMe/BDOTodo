package com.ezralee.bdotodo.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ezralee.bdotodo.history.AddTodoItem
import com.ezralee.bdotodo.main.NumberPickerFragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentDailyAddTodoBinding
import com.ezralee.bdotodo.main.DatePickerDialog

class DailyAddTodoFragment: DialogFragment() {
    var _binding: FragmentDailyAddTodoBinding? = null
    val binding get() = _binding!!

    var items: MutableList<AddTodoItem> = mutableListOf(
        AddTodoItem("2022/10/20","달성방법1",20),
        AddTodoItem("2022/10/20","달성방법1",20),
        AddTodoItem("2022/10/20","달성방법1",20),
        AddTodoItem("2022/10/20","달성방법1",20),
        AddTodoItem("2022/10/20","달성방법1",20),
        AddTodoItem("2022/10/20","달성방법1",20)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.history_dialog)

        isCancelable = true

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDailyAddTodoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.todoDateEdit.setOnClickListener{
            DatePickerDialog().show(parentFragmentManager,"date picker")
        }

        binding.close.setOnClickListener { this.dismiss() }
        binding.todoCancel.setOnClickListener { this.dismiss() }
        binding.todoTaskCount.setOnClickListener { NumberPickerFragment().show(parentFragmentManager,"number picker") }
        binding.todoAddList.setOnClickListener {  }
        binding.todoListToAddRecycler.adapter = AddTodoAdapter(requireContext(),items)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}