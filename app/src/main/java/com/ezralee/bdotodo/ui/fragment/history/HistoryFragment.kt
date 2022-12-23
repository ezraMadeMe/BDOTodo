package com.ezralee.bdotodo.ui.fragment.history

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.Util.RetrofitHelper
import com.ezralee.bdotodo.data.Util.RetrofitService
import com.ezralee.bdotodo.data.model.HistoryData
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.ui.adapter.history.HistoryAdapter
import com.ezralee.bdotodo.ui.activity.history.SetHistoryActivity
import com.ezralee.bdotodo.ui.activity.history.ShowHistoryActivity
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.viewmodel.history.MainHistoryVM
import com.ezralee.bdotodo.viewmodel.main.eventObserve
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    lateinit var viewModel: MainHistoryVM
    lateinit var binding: FragmentHistoryBinding
    lateinit var db: HistoryDB

    var items: MutableList<HistoryData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_history)
        viewModel =
            ViewModelProvider(this, MainHistoryVM.Factory(requireContext() as Application))[MainHistoryVM::class.java]
        binding.historyRecycler.adapter = viewModel.recyclerAdapter
        db = HistoryDB.getInstance(requireContext())!!

        binding.apply {
            lifecycleOwner = this@HistoryFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyRecycler.adapter = HistoryAdapter()

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(activity, SetHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val ARG_TEXT = "fragmentTAG"

        fun newInstance() =
            DailyFragment().apply {
                arguments =
                    Bundle().apply {
                        putString(ARG_TEXT, "히스토리")
                    }
            }
    }

    // 정보 삭제시 옵저버가 감지하여 updateUserList 함수를 호출하기 때문에 자동으로 뷰 갱신
    fun deleteHistory(historyData: HistoryData) {
        CoroutineScope(Dispatchers.Main).launch {
            val delete = async(Dispatchers.IO) {
                db.hisDAO().delete(historyData)
            }
            delete.await()
        }
    }
}