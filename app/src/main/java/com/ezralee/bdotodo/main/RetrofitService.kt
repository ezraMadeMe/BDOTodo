package com.ezralee.bdotodo.main

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*

interface RetrofitService {

    @GET("bdotodo/selectUserHistoryFromServer.php") //일자별로 데이터를 끊어서 가져오게
    fun getHistoryFromServer(@Header("userId") userId : String, @Query("date") date: String)
    : Call<String>

    //입력한 히스토리 정보를 저장 및 입력 //손대면 뒤짐
    @Multipart
    @POST("bdotodo/insertHistoryDB.php")
    fun postHistoryToServer(@PartMap dataPart: Map<String, String>,@Part filePart: MultipartBody.Part)
            : Call<String>

    @GET("bdotodo/loadHistoryDateDB.php") //저장된 히스토리 정보를 불러오기
    fun loadHistoryDateFromServer(): Call<MutableList<HistoryItem>>
}