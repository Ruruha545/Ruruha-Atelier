package com.android.mynote.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.mynote.R
import com.android.mynote.databinding.PageWriteBinding

class PageWrite: Fragment() {

    // 뷰 바인딩용 객체 추가(소스 : page_read.xml)
    lateinit var bindingPageWrite : PageWriteBinding

    // 출력용 뷰 그룹 객체화(지연)
    private lateinit var menuView : ViewGroup

    // 프래그먼트 로딩용 코드
    private fun loadFrag() : Unit {

        // 출력용 프래그먼트 객체화
        val newFragment: Fragment = FrgWriteSrc()

        // 출력용 프래그먼트 트랜잭션 작업 로직
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, newFragment)
            .commit()
    }


    // 프래그먼트 연결 시 호출(1회)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.e("PageWrite 부분", "onAttach 영역")
    }

    // 프래그먼트 생성 시 호출(1회)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("PageWrite 부분", "onCreate 영역")
    }

    // 프래그먼트 UI 생성 시 호출(1회)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰 바인딩 객체화 로직
        bindingPageWrite = PageWriteBinding.inflate(layoutInflater)

        // 뷰 그룹 객체화 로직
        menuView = bindingPageWrite.pgWriteBox

        Log.e("PageWrite 부분", "onCreateView 영역")

        return bindingPageWrite.root
    }

    // 프래그먼트 UI 최신화 시 호출(반복)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 메뉴 버튼 클릭 이벤트 처리 로직
        menuView.setOnClickListener {
            // 열람 프래그먼트 출력 로직
            loadFrag()
        }

        Log.e("PageWrite 부분", "onViewCreated 영역")
    }

    // 호스트 액티비티의 onCreate() 동작 시 호출(1회)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e("PageWrite 부분", "onActivityCreated 영역")
    }

    // 프래그먼트 시각화 시 호출(1회)
    override fun onStart() {
        super.onStart()

        Log.e("PageWrite 부분", "onStart 영역")
    }

    // 프래그먼트 상태 업데이트 시 호출(반복)
    override fun onResume() {
        super.onResume()

        Log.e("PageWrite 부분", "onResume 영역")
    }

    // 프래그먼트 상태 일시중단 시 호출(반복)
    override fun onPause() {
        super.onPause()

        Log.e("PageWrite 부분", "onPause 영역")
    }

    // 프래그먼트 중단 시 호출(반복)
    override fun onStop() {
        super.onStop()

        Log.e("PageWrite 부분", "onStop 영역")
    }

    // 프래그먼트 뷰 제거 시 호출(1회)
    override fun onDestroyView() {
        super.onDestroyView()

        Log.e("PageWrite 부분", "onDestroyView 영역")
    }

    // 프래그먼트 제거 시 호출(1회)
    override fun onDestroy() {
        super.onDestroy()

        Log.e("PageWrite 부분", "onDestroy 영역")
    }

    // 프래그먼트 연결해제 시 호출(1회)
    override fun onDetach() {
        super.onDetach()

        Log.e("PageWrite 부분", "onDetach 영역")
    }
}