package com.ezralee.bdotodo.history

import android.app.Activity.RESULT_CANCELED
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivitySetHistoryBinding
import com.ezralee.bdotodo.main.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class SetHistoryActivity : AppCompatActivity() {
    val binding: ActivitySetHistoryBinding by lazy { ActivitySetHistoryBinding.inflate(layoutInflater) }

    //lateinit var item: HistoryItem
    lateinit var imgPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.historyDateEdit.text = Info.date
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        binding.historyDateEdit.setOnClickListener {
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(supportFragmentManager, "date")
        }

        var pickedDate = intent.getStringExtra("date")
        binding.historyDateEdit.text = pickedDate

        //입력한 정보들을 DB에 저장
        binding.historyDone.setOnClickListener {
            historyRetrofit()

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
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
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

    fun historyRetrofit() {

        var userId = KakaoLogin.USER_ID
        var title = binding.historyTitleEdit.text.toString()
        var date = binding.historyDateEdit.text.toString()
        var category = binding.historyCategory.selectedItem.toString()
        var memo = binding.historyMemoEdit.text.toString()

        if (title != null && date != null && category != null) {
            val retrofit = RetrofitHelper().getRetrofitInstance()
            val retrofitService = retrofit.create(RetrofitService::class.java)
            var filePart: MultipartBody.Part? = null

            if (imgPath != null) {
                var file = File(imgPath)
                var requestBody = RequestBody.create(MediaType.parse("image/*"), file)
                filePart = MultipartBody.Part.createFormData("img", file.name, requestBody)
            }

            var dataPart = HashMap<String, String>()
            dataPart.put("userId", userId)
            dataPart.put("title", title)
            dataPart.put("date", date)
            dataPart.put("category", category)
            dataPart.put("memo", memo)

            val call: Call<String> = retrofitService.postHistoryToServer(dataPart, filePart!!)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(this@SetHistoryActivity, "저장 성공", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    AlertDialog.Builder(this@SetHistoryActivity).setMessage(t.message).show()
                }
            })
        } else {
            var s =""
            when {
                title == null
                -> s+="제목"
                date == null
                -> s+="날짜"
                category == null
                -> s+="카테고리"
            }
            Toast.makeText(this@SetHistoryActivity, "$s 를 입력해 주세요", Toast.LENGTH_SHORT).show()
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































