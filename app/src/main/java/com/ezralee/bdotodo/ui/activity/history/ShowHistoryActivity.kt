package com.ezralee.bdotodo.ui.activity.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.databinding.ActivityShowHistoryBinding

class ShowHistoryActivity: AppCompatActivity() {

    val binding: ActivityShowHistoryBinding by lazy { ActivityShowHistoryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        //전달받은 intent로 특정 history에 대한 상세 정보 표시
        var intent = intent
        if (intent != null){
            binding.historyTitle.text = intent.getStringExtra("title")
            binding.historyDate.text = intent.getStringExtra("date")
            binding.historyCategory.text = intent.getStringExtra("category")
            binding.historyMemo.text = intent.getStringExtra("memo")
            Glide.with(this@ShowHistoryActivity).load(intent.getStringExtra("image")).into(binding.historyImage)
        }

        binding.historyEdit.setOnClickListener {
            var intent = Intent(this@ShowHistoryActivity, SetHistoryActivity::class.java)
            intent.putExtra("title",binding.historyTitle.text)
            intent.putExtra("date", binding.historyDate.text)
            intent.putExtra("category",binding.historyCategory.text)
            intent.putExtra("memo",binding.historyMemo.text)
            intent.putExtra("image",intent.getStringExtra("image"))
            startActivity(intent)
        }
    }
}