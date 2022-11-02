package com.ezralee.bdotodo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ezralee.bdotodo.databinding.ActivityShowHistoryBinding

class ShowHistoryDetailActivity: DialogFragment() {

    lateinit var binding: ActivityShowHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.history_dialog)

        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ActivityShowHistoryBinding.inflate(inflater,container,false)

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