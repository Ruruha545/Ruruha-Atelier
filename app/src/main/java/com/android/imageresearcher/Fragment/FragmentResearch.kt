package com.android.imageresearcher.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.imageresearcher.Adapter.ImageResearchAdapter
import com.android.imageresearcher.DataClass.KakaoResponse
import com.android.imageresearcher.InterfaceService.APIInterface
import com.android.imageresearcher.Object.APIClient
import com.android.imageresearcher.databinding.FragmentResearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentResearch: Fragment() {

    // 탭 레이아웃 처리용 참고 객체 생성
    companion object {
        const val TabName = "LeftTab"
    }

    // 뷰 바인딩용 객체 생성
    private lateinit var ResearchBind : FragmentResearchBinding

    // 검색바, 검색 키워드 객체 생성
    private val MyInputBar: SearchView = ResearchBind.researchContainerSearchview
    private var MyKeyword: String = ""

    // 검색바 작동용 로직
    private fun searching(){
        // 검색바 텍스트 리스너 설정
        MyInputBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            // 검색어 인식, 처리 로직(검색버튼 클릭 시)
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    MyKeyword = query
                }
                return false
            }

            // 이건 무슨 기능인지 모르겠음
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    // 응답 에러 시 처리하는 로직
    private fun failing(cont:Context, ermsg: String){
        Toast.makeText(cont, ermsg,Toast.LENGTH_SHORT).show()
    }

    // 검색한 이미지 불러오는 로직
    private suspend fun imaging(Target: String){
        // 레트로핏 인터페이스 객체 생성
        val MyRetroAPI:APIInterface = APIClient.MyRetrofit.create(APIInterface::class.java)

        // 레트로핏 요청 비동기 처리 로직
        MyRetroAPI.RequestImage(
            apiKey = APIClient.Auth_Key,
            query = Target,
            sort = "accuracy",
            page = 1,
            size = 40
        )


        MyRetroAPI.enqueue(object : Callback<MutableList<KakaoResponse>> {
            // 응답이 정상일 때 처리 로직
            override fun onResponse(call: Call<MutableList<KakaoResponse>>, response: Response<MutableList<KakaoResponse>>) {
                if (response.isSuccessful) {
                    Log.d("이미지 검색창", "서버_정상, 응답_성공")
                } else {
                    Log.d("이미지 검색창", "서버_정상, 응답_실패")
                }
            }

            // 응답이 비정상일 때 처리 로직
            override fun onFailure(call: Call<MutableList<KakaoResponse>>, t: Throwable) {
                Log.d("이미지 검색창", "서버_비정상")
            }
        })
    }


    // 액티비티 연결 시 초기화(1회)
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // 프래그먼트 생성 시 초기화(1회)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // 레이아웃 및 UI 컴포넌트 생성 시 호출
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰 초기화 구문 추가
        ResearchBind = FragmentResearchBinding.inflate(inflater, container, false)

        // 검색어 작동 구문 추가
        searching()

        return ResearchBind.root
    }

    // 레이아웃 및 UI 컴포넌트 초기화 시 호출
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // 중단 시 호출
    override fun onPause() {
        super.onPause()
    }

    // 중단 -> 사용 시 호출
    override fun onResume() {
        super.onResume()

    }

    // 백그라운드 처리 시 호출
    override fun onStop() {
        super.onStop()

    }

    // 레이아웃 및 UI 컴포넌트 제거 시 호출
    override fun onDestroyView() {
        super.onDestroyView()
    }

    // 프래그먼트 제거 시 호출
    override fun onDestroy() {
        super.onDestroy()
    }


}