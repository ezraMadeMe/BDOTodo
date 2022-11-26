package com.ezralee.bdotodo.main

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ezralee.bdotodo.databinding.ActivityIntroBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import java.text.SimpleDateFormat
import java.util.*

class IntroActivity: AppCompatActivity() {
    val binding: ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }
    //var pref: SharedPreferences = getSharedPreferences("isFirst", Activity.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        if (!pref.getBoolean("isFirst",false)){
//            var editor: SharedPreferences.Editor = pref.edit()
//            editor.putBoolean("isFirst", true)
//            editor.commit()
//
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//
//        }else{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        Toast.makeText(this, pref.getBoolean("isFirst",false).toString(), Toast.LENGTH_SHORT).show()

    }//onCreate
}