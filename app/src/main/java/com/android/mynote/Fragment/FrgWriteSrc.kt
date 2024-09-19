package com.android.mynote.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.mynote.databinding.FrgReadBinding
import com.android.mynote.databinding.FrgWriteBinding

class FrgWriteSrc: Fragment() {

    // 뷰 바인딩용 객체 추가(소스 : frg_write.xml)
    lateinit var bindingWrite : FrgWriteBinding

    // 호스트 액티비티의 컨텍스트를 상속받기 위한 객체화
    lateinit var writeContext: Context

    // 에디트 뷰에 전시할 텍스트 지연 객체화
    private lateinit var reText: String


    // 프래그먼트 연결 시 호출(1회)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 호스트 액티비티의 컨텍스트를 상속받는 로직
        writeContext = context
    }

    // 프래그먼트 생성 시 호출(1회)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // "Bundle"은 "키-값" 쌍으로 데이터를 저장하는 객체
        // 프래그먼트의 상태를 저장하고, 복원하는데 사용됨
        //
    }

    // 프래그먼트 UI 생성 시 호출(1회)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰 바인딩 객체화 로직
        bindingWrite = FrgWriteBinding.inflate(layoutInflater)

        // 넘겨받은 번들 객체화 및 에디트 뷰 연결
        reText = arguments?.getString("txtbox") ?: "빈 문자"



        // 프래그먼트용 레이아웃 링크 로직
        return bindingWrite.root
    }

    // 프래그먼트 UI 최신화 시 호출(반복)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 넘겨받은 TXT 파일의 내용 에디트 뷰에 전시하는 로직
        bindingWrite.editTextText.setText(reText)
    }

    // 호스트 액티비티의 onCreate() 동작 시 호출(1회)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    // 프래그먼트 시각화 시 호출(1회)
    override fun onStart() {
        super.onStart()
    }

    // 프래그먼트 상태 업데이트 시 호출(반복)
    override fun onResume() {
        super.onResume()
    }

    // 프래그먼트 상태 일시중단 시 호출(반복)
    override fun onPause() {
        super.onPause()
    }

    // 프래그먼트 중단 시 호출(반복)
    override fun onStop() {
        super.onStop()
    }

    // 프래그먼트 뷰 제거 시 호출(1회)
    override fun onDestroyView() {
        super.onDestroyView()
    }

    // 프래그먼트 제거 시 호출(1회)
    override fun onDestroy() {
        super.onDestroy()
    }

    // 프래그먼트 연결해제 시 호출(1회)
    override fun onDetach() {
        super.onDetach()
    }

}