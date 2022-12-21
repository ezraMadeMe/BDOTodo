package com.ezralee.bdotodo.ui.fragment.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityShowHistoryBinding

class ShowHistoryDetailFragment: DialogFragment() {

    val binding: ActivityShowHistoryBinding by lazy { ActivityShowHistoryBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle(STYLE_NO_TITLE, R.style.history_dialog)

        isCancelable = true

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyOk.setOnClickListener {
            this.dismiss()
        }
        binding.historyClose.setOnClickListener {
            this.dismiss()
        }
    }
}