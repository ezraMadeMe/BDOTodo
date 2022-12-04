package com.ezralee.bdotodo.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ezralee.bdotodo.databinding.FragmentHistoryBinding
import com.ezralee.bdotodo.main.HistoryItem
import com.ezralee.bdotodo.main.KakaoLogin
import com.ezralee.bdotodo.main.RetrofitHelper
import com.ezralee.bdotodo.main.RetrofitService
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {
    val binding: FragmentHistoryBinding by lazy { FragmentHistoryBinding.inflate(layoutInflater) }
    var items: MutableList<HistoryItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setHistoryRetrofit()
        binding.historyRecycler.adapter = HistoryAdapter(requireContext(),items)
        loadData()

        binding.swipeRefresh.setOnRefreshListener {
            loadData()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.historyBtn.setOnClickListener {
            val intent = Intent(activity,SetHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    fun setHistoryRetrofit(){
        var userId = KakaoLogin.USER_ID

        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val call: Call<String> = retrofitService.getHistoryFromServer(userId)
        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    fun loadData(){
        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        var call : Call<MutableList<HistoryItem>> = retrofitService.loadHistoryDateFromServer()
        call.enqueue(object : Call<MutableList<HistoryItem>>, Callback<MutableList<HistoryItem>> {

            override fun onResponse(
                call: Call<MutableList<HistoryItem>>,
                response: Response<MutableList<HistoryItem>>
            ) {
                items.clear()
                //ConcurrentModificationException 오류
                binding.historyRecycler.adapter?.notifyDataSetChanged()

                var responseItems: MutableList<HistoryItem> = response.body()!!
                Toast.makeText(activity, ""+items.size, Toast.LENGTH_SHORT).show()
                for (item : HistoryItem in responseItems){
                    items.add(0,item)
                    binding.historyRecycler.adapter?.notifyItemInserted(0)
                }
            }
            override fun onFailure(call: Call<MutableList<HistoryItem>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

            override fun clone(): Call<MutableList<HistoryItem>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<MutableList<HistoryItem>> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<MutableList<HistoryItem>>) {
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