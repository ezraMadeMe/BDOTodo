package com.ezralee.bdotodo.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    //var items: Array<HashMap<String, String>> = arrayOf()

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

    //날짜별로 데이터를 묶어오는 거.... GET으로 userId 전송이 안됨...
    fun loadData(){
        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        var call : Call<MutableList<HistoryItem>> = retrofitService.loadHistoryDateFromServer(KakaoLogin.USER_ID)

        call.enqueue(object : Call<MutableList<HistoryItem>>, Callback<MutableList<HistoryItem>> {
            override fun onResponse(
                call: Call<MutableList<HistoryItem>>,
                response: Response<MutableList<HistoryItem>>
            ) {
                Log.i("size####",""+items.size)
                Log.i("title####", items[0].title)

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
                //Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                Log.i("failure####",t.message.toString())
                Log.i("send####",call.isExecuted.toString())
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