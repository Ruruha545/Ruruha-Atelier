package com.android.imageresearcher.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.android.imageresearcher.databinding.FragmentResearchBinding

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
    fun Searching(){
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

        // 검색어 작동 구문 추가
        Searching()


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
        // 뷰 자원 해제
    }

    // 프래그먼트 제거 시 호출
    override fun onDestroy() {
        super.onDestroy()
    }


}