package com.android.imageresearcher.Object

import com.android.imageresearcher.InterfaceService.APIInterface
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    // 베이스 URL 객체 생성
    const val BASE_URL:String = "https://dapi.kakao.com/"

    // 인증키 객체 생성
    const val Auth_Key:String = "KakaoAk 9de08003677f3ae189c5a83ad0ef2343"

    // Retrofit 객체 생성
    // baseURL() : 모든 API 요청의 기본 URL 설정
    // addConverterFactory() : 데이터(← 서버) 변환
    val MyRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val MyRequest:APIInterface = MyRetrofit.create(APIInterface::class.java)


//    // 요청을 보낼 클라이언트 객체 생성
//    val MyClient: OkHttpClient = OkHttpClient()
//
//    // 클라이언트가 보내는 요청 객체 생성
//    val MyMsg: Request = Request.Builder()
//        .url("https://dapi.kakao.com/v2/search/image?query=${보낼 키워드}")
//        .addHeader("Authorization", "KakaoAk ${REST_API_KEY}")
//        .build()


//    val KakaoClient by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://dapi.kakao.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

//    val KakaoAPI: APIInterface by lazy{
//        retrofit2.create(APIInterface::class.java)
//
//    }

}