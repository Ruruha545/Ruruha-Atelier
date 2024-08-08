package com.android.imageresearcher.InterfaceService


import com.android.imageresearcher.DataClass.KakaoResponse
import com.android.imageresearcher.Object.APIClient
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIInterface {
    // 카카오 이미지 검색(요청)
    @GET("v2/search/image")
    suspend fun RequestImage(
        @Header("Authorization") apiKey: String = "",
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): MutableList<KakaoResponse>

    // 카카오 이미지 검색(수신)
    suspend fun GetImage(
    )
}