package com.ezralee.bdotodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.ezralee.bdotodo.databinding.ActivityMainBinding
import com.ezralee.bdotodo.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {

    var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding= inflate(layoutInflater)
        setContentView(binding!!.root)
    }
}