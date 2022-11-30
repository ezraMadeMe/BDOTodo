package com.ezralee.bdotodo.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    val binding: ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        KakaoLogin.prefs = PreferenceUtil(applicationContext)
    }//onCreate

    override fun onResume() {
        super.onResume()
        if (KakaoLogin.USER_ID.equals("")) {
            //Log.i("isFirst",KakaoLogin.prefs.getString("userId",""))

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}