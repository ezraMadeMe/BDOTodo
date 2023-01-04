package com.ezralee.bdotodo.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.ezralee.bdotodo.databinding.BottomSheetHistoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HistoryBottomSheetDialog : BottomSheetDialogFragment() {
    val binding: BottomSheetHistoryBinding by lazy { BottomSheetHistoryBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}