package com.ezralee.bdotodo.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.databinding.ActivityTestBinding
import retrofit2.*

class TestActivity : AppCompatActivity() {

    val binding : ActivityTestBinding by lazy { ActivityTestBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //binding.send.setOnClickListener { postData() }
    }

//    fun postData(){
//        var name = binding.name.text.toString()
//        var title = binding.title.text.toString()
//        var message = binding.msg.text.toString()
//        var price = binding.price.text.toString()
//
//        val retrofit: Retrofit = RetrofitHelper().getRetrofitInstance()
//        val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)
//
//        var dataPart = hashMapOf<String, String>()
//        dataPart.put("name", name)
//        dataPart.put("title", title)
//        dataPart.put("msg", message)
//        dataPart.put("price", price)
//
//        var call: Call<String> = retrofitService.postData(dataPart)
//        call.enqueue(object : Callback<String>{
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                Toast.makeText(this@TestActivity, "업로드 성공", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Toast.makeText(this@TestActivity, "업로드 실패", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}