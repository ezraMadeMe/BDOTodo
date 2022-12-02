package com.ezralee.bdotodo.main

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
//    @POST("bdotodo/insertUserData.php")
//    fun postUserDataToServer(@PartMap)

    @Multipart
    @POST("bdotodo/insertHistoryDB.php")
    fun postHistoryToServer(
        @PartMap dataPart: Map<String, String>,
        @Part filePart: MultipartBody.Part
    ): Call<String>

    @GET("bdotodo/loadHistoryDB.php")
    fun loadHistoryFromServer(): Call<MutableList<HistoryItem>>
}