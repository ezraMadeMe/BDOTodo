package com.ezralee.bdotodo.data.Util

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*

interface RetrofitService {
    //조회 - GET / 생성 - POST / 변경 - PUT / 삭제 - DELETE

    ////////////////////////////////////////// Goal ///////////////////////////////////////////////////

    ///////////////////////////////////////// History /////////////////////////////////////////////////
    //입력한 히스토리 정보를 저장 및 입력 //손대면 뒤짐
    @Multipart
    @POST("bdotodo/insertHistoryDB.php")
    fun postHistoryToServer(@PartMap dataPart: Map<String, String>,@Part filePart: MultipartBody.Part)
            : Call<String>
}