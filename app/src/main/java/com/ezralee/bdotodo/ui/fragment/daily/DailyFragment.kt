package com.ezralee.bdotodo.ui.fragment.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentDaliyBinding
import com.ezralee.bdotodo.viewmodel.daily.MainDailyVM

class DailyFragment : Fragment() {

    private val viewModel = createVm()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =
            DataBindingUtil.inflate<FragmentDaliyBinding>(
                inflater,
                R.layout.fragment_daliy,
                container,
                false
            )
        binding.setVariable(BR._all, viewModel)

        viewModel.adapter

        return binding.root
    }

    private fun createVm() = MainDailyVM(object : MainDailyVM.DailyFragmentContract {
//        override fun showToast(message: String) {
//            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
//        }
    })
}
