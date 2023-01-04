package com.ezralee.bdotodo.ui.fragment.daily

import android.os.Bundle
import androidx.fragment.app.Fragment

class DailyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance() =
            DailyFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, "할 일")
                    }
            }
    }
}
