package com.android.imageresearcher.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.imageresearcher.databinding.FragmentMyboxBinding
import com.android.imageresearcher.databinding.FragmentResearchBinding

class FragmentMybox: Fragment() {

    // 탭 레이아웃 처리용 참고 객체 생성
    companion object {
        const val TabName = "RightTab"
    }

    // 뷰 바인딩용 객체 생성
    private lateinit var MyBoxBind : FragmentMyboxBinding

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
        MyBoxBind = FragmentMyboxBinding.inflate(inflater, container, false)


        return MyBoxBind.root
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