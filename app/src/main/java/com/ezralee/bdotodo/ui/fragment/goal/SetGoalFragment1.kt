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
import com.ezralee.bdotodo.ui.dialog.ColorPickerActivity
import com.ezralee.bdotodo.ui.dialog.DatePickerDialog
import com.ezralee.bdotodo.viewmodel.goal.SetGoalVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetGoalFragment1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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