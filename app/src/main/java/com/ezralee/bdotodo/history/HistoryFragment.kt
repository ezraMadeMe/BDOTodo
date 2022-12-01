package com.ezralee.bdotodo.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.main.HistoryItem
import com.ezralee.bdotodo.main.RetrofitHelper
import com.ezralee.bdotodo.main.RetrofitService
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class HistoryFragment : Fragment() {
    val binding: FragmentHistoryBinding by lazy { FragmentHistoryBinding.inflate(layoutInflater) }
    var items: ArrayList<HistoryItem> = arrayListOf(
        HistoryItem("제목11","2022/11/01","보물","메모메모메모"),
        HistoryItem("제목33","2022/11/02","보물","메모메모메모"),
        HistoryItem("제목44","2022/11/03","보물","메모메모메모"),
        HistoryItem("제목22","2022/11/01","보물","메모메모메모")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.historyRecycler.adapter = HistoryAdapter(requireContext(),items)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyBtn.setOnClickListener {
            //ShowHistoryDetailActivity().show(parentFragmentManager, "History Dialog")

            SetHistoryActivity().show(parentFragmentManager,"set history")
        }

        loadData()
    }

    fun loadData(){
        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        var call : Call<ArrayList<HistoryItem>> = retrofitService.loadHistoryFromServer()
        call.enqueue(object : Call<ArrayList<HistoryItem>>, Callback<ArrayList<HistoryItem>> {
            override fun onResponse(call: Call<ArrayList<HistoryItem>>, response: Response<ArrayList<HistoryItem>>) {
                items.clear()
                binding.historyRecycler.adapter?.notifyDataSetChanged()

                items = response.body()!!
                for (item : HistoryItem in items){
                    items.add(0, item)
                    binding.historyRecycler.adapter?.notifyItemInserted(0)
                }
            }

            override fun onFailure(call: Call<ArrayList<HistoryItem>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

            override fun clone(): Call<ArrayList<HistoryItem>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<ArrayList<HistoryItem>> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<ArrayList<HistoryItem>>) {
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