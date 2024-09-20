package com.android.mynote.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.mynote.databinding.FrgReadBinding
import com.android.mynote.databinding.FrgWriteBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class FrgWriteSrc: Fragment() {

    // 뷰 바인딩용 객체 추가(소스 : frg_write.xml)
    lateinit var bindingWrite : FrgWriteBinding

    // 호스트 액티비티의 컨텍스트를 상속받기 위한 객체 추가
    lateinit var writeContext: Context

    // 프래그먼트 선택 TXT 파일의 Uri 정보 객체 추가
    lateinit var writeUri: Uri

    // 에디트 뷰에 전시할 텍스트 지연 객체 추가
    private lateinit var reText: String

    // 지정 파일 연결용 코드
    private fun writerTXT(nowUriW: Uri) : String{

        // 작성용 스트링빌더 객체화
        val writeStringBuilder: StringBuilder = StringBuilder()

        // 파일 출력용 로직
        try{
            val inpStrWrite: InputStream? = requireContext().contentResolver
                .openInputStream(nowUriW)
            val buffReaderWrite: BufferedReader = BufferedReader(InputStreamReader(inpStrWrite))
            var textLineWrite: String?
            while( buffReaderWrite.readLine().also{ textLineWrite = it } != null ){
                writeStringBuilder.append(textLineWrite).append("\n")
            }
            buffReaderWrite.close()
        }

        // 예외처리 로직
        catch(e: Exception){
            Log.e("FrgWriteSrc", "지정 파일 연결용 코드 동작 간 에러 발생")
            Toast.makeText(requireContext(), "지정 파일 연결용 코드 에러 발생",
                Toast.LENGTH_SHORT).show()
        }

        return writeStringBuilder.toString()
    }


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

        // 수신한 번들 객체화
        arguments?.let{
            writeUri = it.getParcelable("txtbox")!!
        }

        // 텍스트 전시 로직
        reText = writerTXT(writeUri)
        bindingWrite.editTextText.setText(reText)



//        // 넘겨받은 번들 객체화 및 에디트 뷰 연결
//        reText = arguments?.getString("txtbox") ?: "빈 문자"



        // 프래그먼트용 레이아웃 링크 로직
        return bindingWrite.root
    }

    // 프래그먼트 UI 최신화 시 호출(반복)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingWrite.editTextText.isEnabled = true
        bindingWrite.editTextText.isFocusable = true
        bindingWrite.editTextText.isFocusableInTouchMode = true


//        // 넘겨받은 TXT 파일의 내용 에디트 뷰에 전시하는 로직
//        writeUri?.let{
//            reText = writerTXT(it)
//            bindingWrite.editTextText.setText(reText)
//        } ?:
//        run{
//            Toast.makeText(requireContext(), "파일수신 실패", Toast.LENGTH_SHORT).show()
//            Log.e("FrgWriteSrc", "파일 수신에 실패했습니다")
//        }
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