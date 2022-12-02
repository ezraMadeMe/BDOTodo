package com.ezralee.bdotodo.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.databinding.ActivityShowHistoryBinding

class ShowHistoryActivity: AppCompatActivity() {

    val binding: ActivityShowHistoryBinding by lazy { ActivityShowHistoryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        var intent = Intent()
        binding.historyTitle.text = intent.getStringExtra("title")
        binding.historyDate.text = intent.getStringExtra("date")
        binding.historyCategory.text = intent.getStringExtra("category")
        binding.historyImage.setImageResource(intent.getIntExtra("image",0))
        binding.historyMemo.text = intent.getStringExtra("memo")
    }

    override fun onStart() {
        super.onStart()
    }
}