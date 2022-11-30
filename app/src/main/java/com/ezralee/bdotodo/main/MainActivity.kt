package com.ezralee.bdotodo.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivityMainBinding
import com.ezralee.bdotodo.databinding.ActivityMainBinding.inflate
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //선택된 BNV 반응
        binding.mainBNV.setOnNavigationItemSelectedListener(this)
        //ViewPager에 FragmentManager 붙이기
        binding.mainVP.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        //ViewPager에 페이지 변화를 감지하는 콜백메서드 오버라이딩
        binding.mainVP.registerOnPageChangeCallback(ViewPagerPageChangeCallback())
    }

    //선택된 BNV 감지하는 리스너 오버라이딩
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_daily -> {
                binding.mainVP.currentItem = 0
                true
            }
            R.id.menu_history -> {
                binding.mainVP.currentItem = 1
                true
            }
            R.id.menu_goal -> {
                binding.mainVP.currentItem = 2
                true
            }
            else -> false
        }
    }

    //ViewPager의 페이지 변화 감지 콜백메서드 오버라이딩
    inner class ViewPagerPageChangeCallback : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            binding.mainBNV.selectedItemId = when (position) {
                0 -> R.id.menu_daily
                1 -> R.id.menu_history
                2 -> R.id.menu_goal
                else -> error("no menu")
            }
        }
    }
}