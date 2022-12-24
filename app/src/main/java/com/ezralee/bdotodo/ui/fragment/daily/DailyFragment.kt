package com.ezralee.bdotodo.ui.fragment.daily

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.repository.daily.DailyDB
import com.ezralee.bdotodo.databinding.FragmentDaliyBinding
import com.ezralee.bdotodo.viewmodel.daily.MainDailyVM

class DailyFragment : Fragment() {

    lateinit var viewModel: MainDailyVM
    private var _binding: FragmentDaliyBinding? = null
    val binding: FragmentDaliyBinding get() = _binding!!
    lateinit var db: DailyDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_daliy)
        viewModel =
            ViewModelProvider(this, MainDailyVM.Factory(requireContext() as Application))[MainDailyVM::class.java]
        db = DailyDB.getInstance(requireContext())!!

        binding.apply {
            lifecycleOwner = this@DailyFragment
            viewModel = viewModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
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
