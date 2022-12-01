package com.ezralee.bdotodo.history

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.ezralee.bdotodo.R
import com.ezralee.bdotodo.databinding.ActivitySetHistoryBinding
import com.ezralee.bdotodo.main.DatePickerDialog
import com.ezralee.bdotodo.main.HistoryItem
import com.ezralee.bdotodo.main.RetrofitHelper
import com.ezralee.bdotodo.main.RetrofitService
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

class SetHistoryActivity : DialogFragment() {
    val binding: ActivitySetHistoryBinding by lazy {
        ActivitySetHistoryBinding.inflate(
            layoutInflater
        )
    }
    lateinit var item: HistoryItem
    lateinit var imgPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.history_dialog)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyCancel.setOnClickListener {
            this.dismiss()
        }

        binding.historyClose.setOnClickListener {
            this.dismiss()
        }

//        binding.historyColorPicker.setOnClickListener {
//            val colorPicker = ColorPickerFragment()
//            colorPicker.show(parentFragmentManager, "set color")
//            //컬러피커 색상 변경적용 안됨
//            //colorPicker.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        }

        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        binding.historyDateEdit.setOnClickListener {
            //var intent = Intent(context, DatePickerDialog::class.java)
            //startActivityForResult(intent, 2)
            val datePickerDialog = DatePickerDialog()
            datePickerDialog.show(requireFragmentManager(),"date")
            datePickerDialog.dialog

            //var getIntent = Intent().getStringExtra("date")
            //binding.historyDateEdit.text = getIntent
        }

        binding.historyDateToday.setOnClickListener {
            var now = System.currentTimeMillis()
            var today = Date(now)
            var sdf = SimpleDateFormat("yyyy/MM/dd")
            binding.historyDateEdit.text = sdf.format(today)
        }

        //갤러리의 이미지 선택해 가져오기
        binding.historyAddImage.setOnClickListener {
            // 갤러리 열기
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            resultLauncher.launch(intent)
        }

        //불러온 이미지 지우기
        binding.historyDeleteImage.setOnClickListener {
            Glide.with(this@SetHistoryActivity).load(R.drawable.ic_baseline_delete_outline_24).into(binding.historyImage)
        }

        //입력한 정보들을 DB에 저장
        binding.historyDone.setOnClickListener {
            historyRetrofit()
        }
    }//OnViewCreated

    fun historyRetrofit(){
        item.title = binding.historyTitleEdit.text.toString()
        item.date = binding.historyDateEdit.text.toString()
        item.category = binding.historyCategory.selectedItem.toString()
        item.memo = binding.historyMemoEdit.text.toString()

        val retrofit = RetrofitHelper().getRetrofitInstance()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val filePart: MultipartBody.Part? = null

        if (imgPath != null){
            var file = File(imgPath)
            var requestBody = RequestBody.create(MediaType.parse("image/*"), file)
            var filePart = MultipartBody.Part.createFormData("img",file.name,requestBody)
        }

        var dataPart = HashMap<String, String>()
        dataPart.put("title",item.title)
        dataPart.put("date", item.date)
        dataPart.put("category", item.category)
        dataPart.put("memo", item.memo)

        val call: Call<String> = retrofitService.postHistoryToServer(dataPart, filePart!!)
        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                var result :String? = response.body()
                //Toast.makeText(this@SetHistoryActivity, "저장 성공", Toast.LENGTH_SHORT).show()
                dismiss()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //Toast.makeText(this@SetHistoryActivity, ""+t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    //갤러리 앱 실행하여 선택한 결과 받아오기
    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult>(){
            if (it.resultCode != RESULT_CANCELED){
                val uri = it.data?.data
                Glide.with(this@SetHistoryActivity).load(uri).into(binding.historyImage)
                imgPath = getRealPathFromUri(uri!!)
            }
        }
    )////

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메서드
    fun getRealPathFromUri(uri: Uri): String{
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var loader = CursorLoader(requireContext(), uri, proj, null, null, null)
        var cursor = loader.loadInBackground()
        var column_index : Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        var result= cursor?.getString(column_index!!)
        cursor?.close()

        return result!!
    }
}































