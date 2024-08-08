package com.android.imageresearcher.InterfaceService


import com.android.imageresearcher.DataClass.KakaoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIInterface {
    // 카카오 이미지 검색
    @GET("https://dapi.kakao.com/v2/search/image")
    suspend fun getImage(
        @Header("Authorization") apiKey: String = "KakaoAk 9de08003677f3ae189c5a83ad0ef2343",
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): MutableList<KakaoResponse>
}