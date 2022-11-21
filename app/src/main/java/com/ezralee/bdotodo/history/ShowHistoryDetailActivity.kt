package com.ezralee.bdotodo.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityShowHistoryBinding

class ShowHistoryDetailActivity: AppCompatActivity() {

    lateinit var binding: ActivityShowHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent()
        binding.historyTitle.text = intent.getStringExtra("title")
        binding.historyDate.text = intent.getStringExtra("date")
        binding.historyCategory.text = intent.getStringExtra("category")
        binding.historyImage.setImageResource(intent.getIntExtra("image",0))
        binding.historyMemo.text = intent.getStringExtra("memo")
    }
}