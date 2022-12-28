package com.ezralee.bdotodo.ui.activity.history

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.data.Util.Info
import com.ezralee.bdotodo.data.Util.KakaoLogin
import com.ezralee.bdotodo.data.Util.RetrofitHelper
import com.ezralee.bdotodo.data.Util.RetrofitService
import com.ezralee.bdotodo.data.repository.history.HistoryDB
import com.ezralee.bdotodo.databinding.ActivitySetHistoryBinding
import com.ezralee.bdotodo.ui.dialog.DatePickerDialog
import com.ezralee.bdotodo.viewmodel.history.HistoryVM
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*

class SetHistoryActivity : AppCompatActivity() {

    lateinit var viewModel: HistoryVM
    lateinit var binding: ActivitySetHistoryBinding
    lateinit var db: HistoryDB

    lateinit var imgPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = HistoryDB.getInstance(application)!!
        viewModel = ViewModelProvider(this)[HistoryVM::class.java]
        binding = DataBindingUtil
            .setContentView(this@SetHistoryActivity, R.layout.activity_set_history)
        binding.apply {
            lifecycleOwner = this@SetHistoryActivity
            viewModel = viewModel

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        binding.historyDateEdit.text = Info.date

        //창을 띄울 때 오늘 날짜로 기본 설정이 안됨
        binding.historyDateEdit.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(supportFragmentManager, "date")
        }

        var pickedDate = intent.getStringExtra("date")
        binding.historyDateEdit.text = pickedDate

        //입력한 정보들을 DB에 저장
        binding.historyDone.setOnClickListener {
            binding.historyTitleEdit.text = null
            binding.historyDateEdit.text = Info.date
            binding.historyMemoEdit.text = null

            finish()
        }
        //갤러리의 이미지 선택해 가져오기
        binding.historyAddImage.setOnClickListener {
            when {
                // 갤러리 접근 권한이 있는 경우
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                -> { // 갤러리 열기
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    resultLauncher.launch(intent)
                }
                // 권한 요청 하기(requestPermissions) -> 갤러리 접근(onRequestPermissionResult)
                else -> {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000
                    )
                }
            }
        }
        //불러온 이미지 지우기
        binding.historyDeleteImage.setOnClickListener {
            Glide.with(this@SetHistoryActivity).load(R.drawable.ic_baseline_delete_outline_24)
                .into(binding.historyImage)
        }
        binding.historyCancel.setOnClickListener {
            this.finish()
            binding.historyTitleEdit.text = null
            binding.historyDateEdit.text = Info.date
            binding.historyMemoEdit.text = null
        }
        binding.historyClose.setOnClickListener {
            this.finish()
            binding.historyTitleEdit.text = null
            binding.historyDateEdit.text = Info.date
            binding.historyMemoEdit.text = null
        }
        binding.historyDateToday.setOnClickListener {
            binding.historyDateEdit.text = Info.date
        }
    }

    //갤러리 앱 실행하여 선택한 결과 받아오기
    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult>() {
            if (it.resultCode != RESULT_CANCELED) {
                val uri = it.data?.data
                Glide.with(this@SetHistoryActivity).load(uri).into(binding.historyImage)
                imgPath = getRealPathFromUri(uri!!)
            }
        }
    )////

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메서드
    fun getRealPathFromUri(uri: Uri): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var loader = CursorLoader(this@SetHistoryActivity, uri, proj, null, null, null)
        var cursor = loader.loadInBackground()
        var column_index: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        var result = cursor?.getString(column_index!!)
        cursor?.close()

        return result!!
    }
}































