package com.ezralee.bdotodo.data.Util

import com.ezralee.bdotodo.ui.main.HistoryItem
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*

interface RetrofitService {
    //조회 - GET / 생성 - POST / 변경 - PUT / 삭제 - DELETE

    @GET("bdotodo/selectUserHistoryFromServer.php") //일자별로 데이터를 끊어서 가져오게
    fun getHistoryFromServer(@Header("userId") userId : String, @Query("date") date: String)
    : Call<String>

    //입력한 히스토리 정보를 저장 및 입력 //손대면 뒤짐
    @Multipart
    @POST("bdotodo/insertHistoryDB.php")
    fun postHistoryToServer(@PartMap dataPart: Map<String, String>,@Part filePart: MultipartBody.Part)
            : Call<String>

//    @GET("bdotodo/loadHistoryDateDB.php") //저장된 히스토리 정보를 불러오기
//    fun loadHistoryDateFromServer(@Query("userId") userId: String): Call<Array<HashMap<String, String>>>

    @GET("bdotodo/loadHistoryDateDB.php") //저장된 히스토리 정보를 불러오기
    fun loadHistoryDateFromServer(@Query("userId") userId: String): Call<MutableList<HistoryItem>>

    @POST("bdotodo/insertGoalDB.php")
    fun postGoalToServer(@PartMap dataPart: Map<String, String>) : Call<String>
}