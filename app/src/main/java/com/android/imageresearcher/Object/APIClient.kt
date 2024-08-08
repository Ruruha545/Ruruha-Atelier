package com.android.imageresearcher.Object

import com.android.imageresearcher.InterfaceService.APIInterface
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    // 요청을 보낼 클라이언트 객체 생성
    val MyClient: OkHttpClient = OkHttpClient()

    // 클라이언트가 보내는 요청 객체 생성
    val MyMsg: Request = Request.Builder()
        .url("https://dapi.kakao.com/v2/search/image?query=${보낼 키워드}")
        .addHeader("Authorization", "KakaoAk ${REST_API_KEY}")
        .build()


//    val KakaoClient by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://dapi.kakao.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    val KakaoAPI: APIInterface by lazy{
        retrofit2.create(APIInterface::class.java)

    }






}