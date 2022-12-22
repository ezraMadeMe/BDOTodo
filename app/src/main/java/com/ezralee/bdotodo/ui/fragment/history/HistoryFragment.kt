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
import com.ezralee.bdotodo.ui.fragment.daily.DailyFragment
import com.ezralee.bdotodo.viewmodel.history.MainHistoryVM
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

    lateinit var viewModel: MainHistoryVM
    lateinit var binding: FragmentHistoryBinding
    lateinit var db: HistoryDB
    var items: MutableList<HistoryData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_history)
        viewModel =
            ViewModelProvider(this, MainHistoryVM.Factory(requireContext() as Application)).get(
                MainHistoryVM::class.java
            )
        binding.viewModel = viewModel
        binding.historyRecycler.adapter = viewModel.historyAdapter
        db = HistoryDB.getInstance(requireContext())!!

        viewModel.getAll().observe(this, Observer {
            viewModel.updateHistoryList(it)
        })
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
        binding.historyRecycler.adapter = HistoryAdapter(requireContext(), items)
        loadData()

        binding.swipeRefresh.setOnRefreshListener {
            loadData()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(activity, SetHistoryActivity::class.java)
            startActivity(intent)
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

    //날짜별로 데이터를 묶어오는 거.... GET으로 userId 전송이 안됨...
    fun loadData() {
        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        var call: Call<MutableList<HistoryData>> = retrofitService.loadHistoryDateFromServer(
            KakaoLogin.USER_ID
        )

        call.enqueue(object : Call<MutableList<HistoryData>>, Callback<MutableList<HistoryData>> {
            override fun onResponse(
                call: Call<MutableList<HistoryData>>,
                response: Response<MutableList<HistoryData>>
            ) {
                Log.i("size####", "" + items.size)
                Log.i("title####", items[0].title)

                items.clear()
                //ConcurrentModificationException 오류
                binding.historyRecycler.adapter?.notifyDataSetChanged()

                var responseItems: MutableList<HistoryData> = response.body()!!
                Toast.makeText(activity, "" + items.size, Toast.LENGTH_SHORT).show()
                for (item: HistoryData in responseItems) {
                    items.add(0, item)
                    binding.historyRecycler.adapter?.notifyItemInserted(0)
                }
            }

            override fun onFailure(call: Call<MutableList<HistoryData>>, t: Throwable) {
                //Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                Log.i("failure####", t.message.toString())
                Log.i("send####", call.isExecuted.toString())
            }

            override fun clone(): Call<MutableList<HistoryData>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<MutableList<HistoryData>> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<MutableList<HistoryData>>) {
                TODO("Not yet implemented")
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        })
    }
}