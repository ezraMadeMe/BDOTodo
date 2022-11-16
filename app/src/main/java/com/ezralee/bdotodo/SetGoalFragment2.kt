package com.ezralee.bdotodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentSetGoal2Binding

class SetGoalFragment2: Fragment() {
    val binding: FragmentSetGoal2Binding by lazy { FragmentSetGoal2Binding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = FragmentSetGoal2Binding.inflate(inflater, container, false)

        return view.root
    }

}