package com.ezralee.bdotodo.history

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivitySetHistoryBinding
import com.ezralee.bdotodo.main.*
import com.ezralee.bdotodo.viewmodel.HistoryVM
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*

class SetHistoryActivity : AppCompatActivity() {
    private val viewModel: HistoryVM by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                HistoryVM(application) as T
        }
    }

    val binding: ActivitySetHistoryBinding by lazy {
        ActivitySetHistoryBinding.inflate(
            layoutInflater
        )
    }
    lateinit var imgPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.historyDateEdit.text = Info.date

        var intent = intent
        if (intent != null){
            binding.historyStatusbar.text = "히스토리 수정"

            binding.historyTitleEdit.setText(intent.getStringExtra("title"))
            binding.historyDateEdit.text = intent.getStringExtra("date")
            //binding.historyCategory.text = intent.getStringExtra("category")
            binding.historyMemoEdit.setText(intent.getStringExtra("memo"))
            //이미지 로드 안됨
            Glide.with(this@SetHistoryActivity).load(intent.getStringExtra("image")).into(binding.historyImage)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        //창을 띄울 때 오늘 날짜로 기본 설정이 안됨
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

    fun historyRetrofit() {
        var userId = KakaoLogin.USER_ID
        var title = binding.historyTitleEdit.text.toString()
        var date = binding.historyDateEdit.text.toString()
        var category = binding.historyCategory.selectedItem.toString()
        var memo = binding.historyMemoEdit.text.toString()

        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        var filePart: MultipartBody.Part? = null

        if (imgPath != null) {
            var file = File(imgPath)
            var requestBody = RequestBody.create(MediaType.parse("image/*"), file)
            filePart = MultipartBody.Part.createFormData("img", file.name, requestBody)
        }

        var dataPart = hashMapOf<String, String>()
        dataPart.put("userId", userId)
        dataPart.put("title", title)
        dataPart.put("date", date)
        dataPart.put("category", category)
        dataPart.put("memo", memo)

        val call: Call<String> = retrofitService.postHistoryToServer(dataPart, filePart!!)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                var result = response.body()
                if (response.isSuccessful){
                    Log.i("response####",result.toString())
                }else{
                    Log.i("failure####",result.toString())
                }
                Log.i("userId####",userId)
                Log.i("title####",title)
                Log.i("date####",date)
                Log.i("category####",category)
                Log.i("memo####",memo)

                finish()
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                AlertDialog.Builder(this@SetHistoryActivity).setMessage(t.message).show()
            }
        })//////enqueue
    }///////historyRetrofit

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































