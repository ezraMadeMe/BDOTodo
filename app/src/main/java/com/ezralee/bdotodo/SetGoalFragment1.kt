package com.ezralee.bdotodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentSetGoal1Binding

class SetGoalFragment1: Fragment() {
    lateinit var binding: FragmentSetGoal1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = FragmentSetGoal1Binding.inflate(inflater, container, false)

        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}