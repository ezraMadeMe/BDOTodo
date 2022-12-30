package com.ezralee.bdotodo.ui.fragment.goal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.model.GoalData
import com.ezralee.bdotodo.data.repository.goal.goal.GoalDB
import com.ezralee.bdotodo.databinding.FragmentSetGoal1Binding
import com.ezralee.bdotodo.ui.dialog.ColorPickerActivity
import com.ezralee.bdotodo.ui.dialog.DatePickerDialog
import com.ezralee.bdotodo.viewmodel.goal.SetGoalVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetGoalFragment1 : Fragment() {

    lateinit var binding: FragmentSetGoal1Binding
    lateinit var viewModel: SetGoalVM
    lateinit var db: GoalDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = GoalDB.getInstance(requireContext())!!
        viewModel = ViewModelProvider(this)[SetGoalVM::class.java]
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_set_goal_2)

        binding.apply {
            lifecycleOwner = this@SetGoalFragment1
            viewModel = viewModel
            this.goalColorPicker.setOnClickListener {
                var intentColor = Intent(requireContext(), ColorPickerActivity::class.java)
                startActivityForResult(intentColor,101)

                var bundle = requireArguments()
                if (bundle != null) {
                    val color = arguments?.getInt("color")
                    Log.i("get@@@@", color.toString())
                    binding.goalColorPicker.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            color!!
                        )
                    )
                }
            }//color picker
            goalStartDate.setOnClickListener {
                val datePickerDialog = DatePickerDialog()
                datePickerDialog.show(parentFragmentManager, "date")

                var bundle = requireArguments()
                if (bundle != null) {
                    var pickedDate = bundle.getString("date", Info.date)
                    binding.goalStartDate.text = pickedDate
                }
            }//start date
            goalEndDate.setOnClickListener {
                val datePickerDialog = DatePickerDialog()
                datePickerDialog.show(parentFragmentManager, "date")

                var bundle = requireArguments()
                if (bundle != null) {
                    var pickedDate = bundle.getString("date", Info.date)
                    binding.goalStartDate.text = pickedDate
                }
            }//end date
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    fun addGoal(){
        val goalData = GoalData(
            KakaoLogin.USER_ID,
            binding.historyTitleEdit.text.toString(),
            binding.goalStartDate.text.toString(),
            binding.goalEndDate.text.toString(),
            binding.goalColorPicker.solidColor.toString(),
            binding.historyCategory.selectedItem.toString(),
            binding.historyMemoEdit.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            db.goalDAO().insertGoal(goalData)
        }
    }

    companion object{
        fun newInstance() =
            SetGoalFragment1().apply {
                arguments =
                    Bundle().apply {
                        putString("any","goal페이지 생성")
                    }
            }
    }
}