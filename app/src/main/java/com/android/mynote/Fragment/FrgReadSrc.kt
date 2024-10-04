package com.android.mynote.Fragment

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.android.mynote.R
import com.android.mynote.databinding.FrgReadBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class FrgReadSrc : Fragment() {

    // 뷰 바인딩용 객체 추가(소스 : frg_read.xml)
    private lateinit var bindingRead : FrgReadBinding

    // 호스트 액티비티의 컨텍스트를 상속받기 위한 객체 추가
    private lateinit var readContext: Context

    // 프래그먼트 선택 TXT 파일의 Uri 정보 객체 추가
    private var readUri: Uri? = null

    // 출력용 텍스트 뷰 객체화(지연)
    private lateinit var dispView : TextView

    // 팝업 메뉴 객체화(지연)
    private lateinit var fltActBtn: FloatingActionButton
    private lateinit var popRead : PopupMenu

    // 이하 파일 로드용 코드(SAF 활용)
    // 1) 파일 선택용 메서드
    private fun openTXT(){
        val intentRead: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply{
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
        }
        loadTXTLauncher.launch(intentRead)
    }

    // 2) 파일 선택 동작용 액티비티 런쳐 객체화
    private val loadTXTLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if(result.resultCode == Activity.RESULT_OK && result.data != null){
                val tgtUri: Uri? = result.data?.data
                tgtUri?.let{
                    readUri = it
                    readTXT(it)
                }
            }
            else{
                Toast.makeText(requireContext(), "파일 미선택 상태", Toast.LENGTH_SHORT).show()
            }
        }
    // 이상 파일 로드용 코드(SAF 활용)

    // 파일명 추출 메서드
    private fun getTXTName(tgtUri: Uri): String?{
        // 반환값(파일명), 추적자 정의
        var fileName: String? = null
        val pointer: Cursor? = requireContext().contentResolver
            .query(tgtUri, null, null, null, null)

        // 추적자 동작
        pointer?.use{
            if(it.moveToFirst()){
                val nameId: Int = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if(nameId >= 0){
                    fileName = it.getString(nameId)
                }
            }
        }

        return fileName
    }

    // 파일 출력용 메서드
    private fun readTXT(tgtUri: Uri){
        // 파일 출력 성공용 로직
        try{
            val newStream: InputStream? = requireContext().contentResolver.openInputStream(tgtUri)
            val readBuf: BufferedReader = BufferedReader(InputStreamReader(newStream))
            val cargoText: String = readBuf.use{ it.readText() }

            // 상태 표시용 이미지, 텍스트 비활성화, 메인 텍스트 활성화
            bindingRead.frgReadImgNone.visibility = View.GONE
            bindingRead.frgReadTextNone.visibility = View.GONE

            dispView.visibility = View.VISIBLE

            // 읽어온 텍스트 파일, 파일명 화면 전시
            dispView.text = cargoText
            bindingRead.frgReadTextTitle.text = getTXTName(tgtUri)

            readBuf.close()
        }

        // 파일 출력 실패용 로직
        catch (e: Exception){
            Log.e("readTXT 메서드", "예외처리 상황 발생")
            Toast.makeText(requireContext(), "readTXT 동작 간 예외처리 발생",
                Toast.LENGTH_SHORT).show()
        }
    }

    // 작성용 프래그먼트 호출 및 데이터 전달용 코드
    private fun startFrgWithTXT(){

        // 상태 전달용 번들 객체화
        val passbundle: Bundle = Bundle()
        passbundle.putParcelable("txtbox", readUri)

        // 작성용 프래그먼트 객체화 및 번들 전달
        val newFragment: Fragment = FrgWriteSrc()
        newFragment.arguments = passbundle

        // 프래그먼트 트랜잭션 작업 로직
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, newFragment)
            .commit()
    }


    // 프래그먼트 연결 시 호출(1회)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 호스트 액티비티의 컨텍스트를 상속받는 로직
        readContext = context

        Log.e("FrgReadSrc", "onAttach 부분(FrgReadSrc 1번)")
    }

    // 프래그먼트 생성 시 호출(1회)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("FrgReadSrc", "onCreate 부분(FrgReadSrc 2번)")
    }

    // 프래그먼트 UI 생성 시 호출(1회)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰 바인딩 객체화 로직
        bindingRead = FrgReadBinding.inflate(layoutInflater)

        // 플로팅 액션 버튼 객체화 로직
        fltActBtn = bindingRead.frgReadBtnMenu

        // 전시용 텍스트 뷰 객체화 로직
        dispView = bindingRead.frgReadTextMain

        // 수신한 번들 객체화
        arguments?.let{
            readUri = it.getParcelable("txtbox")!!
        }

        Log.e("FrgReadSrc", "onCreateView 부분(FrgReadSrc 3번)")

        // 프래그먼트용 레이아웃 링크 로직
        return bindingRead.root

    }

    // 프래그먼트 UI 최신화 시 호출(반복)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 텍스트 뷰 속성 변경용 로직
        bindingRead.frgReadTextMain.movementMethod = ScrollingMovementMethod()

        // 번들 수신여부에 따른 이벤트 처리 로직
        if(readUri != null){
            // 상태 표시용 이미지, 텍스트 비활성화
            bindingRead.frgReadImgNone.visibility = View.GONE
            bindingRead.frgReadTextNone.visibility = View.GONE

            dispView.visibility = View.VISIBLE

            // 파일 전시용 메서드 동작
            readTXT(readUri!!)
        }
        else{
            // 상태 표시용 이미지, 텍스트 활성화, 메인 텍스트 비활성화
            bindingRead.frgReadImgNone.visibility = View.VISIBLE
            bindingRead.frgReadTextNone.visibility = View.VISIBLE

            dispView.visibility = View.GONE
        }

        // 메뉴 버튼 클릭 이벤트 처리 로직
        fltActBtn.setOnClickListener {
            // 출력용 팝업 메뉴 생성
            popRead = PopupMenu(requireContext(), fltActBtn)
            popRead.menuInflater.inflate(R.menu.read_menu_list, popRead.menu)

            // 팝업 메뉴 아이템 클릭 이벤트 처리
            popRead.setOnMenuItemClickListener { ele ->
                when (ele.itemId){
                    // 1) 파일 불러오기 선택
                    R.id.menu_loader -> {
                        // 파일 열람용 메서드 동작
                        openTXT()

                        true
                    }

                    // 2) 모드 전환하기 선택
                    R.id.menu_changer -> {
                        // 모드 변경용 메서드 동작
                        startFrgWithTXT()

                        true
                    }

                    // 3) 그 외(예외처리)
                    else -> false
                }
            }

            // 팝업 메뉴 표시
            popRead.show()
        }

        Log.e("FrgReadSrc", "onViewCreated 부분(FrgReadSrc 4번)")
    }

    // 호스트 액티비티의 onCreate() 동작 시 호출(1회)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e("FrgReadSrc", "onActivityCreated 부분(FrgReadSrc 5번)")
    }

    // 프래그먼트 시각화 시 호출(1회)
    override fun onStart() {
        super.onStart()

        Log.e("FrgReadSrc", "onStart 부분(FrgReadSrc 6번)")
    }

    // 프래그먼트 상태 업데이트 시 호출(반복)
    override fun onResume() {
        super.onResume()

        Log.e("FrgReadSrc", "onResume 부분(FrgReadSrc 7번)")
    }

    // 프래그먼트 상태 일시중단 시 호출(반복)
    override fun onPause() {
        super.onPause()

        Log.e("FrgReadSrc", "onPause 부분(FrgReadSrc 8번)")
    }

    // 프래그먼트 중단 시 호출(반복)
    override fun onStop() {
        super.onStop()

        Log.e("FrgReadSrc", "onStop 부분(FrgReadSrc 9번)")
    }

    // 프래그먼트 뷰 제거 시 호출(1회)
    override fun onDestroyView() {
        super.onDestroyView()

        Log.e("FrgReadSrc", "onDestroyView 부분(FrgReadSrc 10번)")
    }

    // 프래그먼트 제거 시 호출(1회)
    override fun onDestroy() {
        super.onDestroy()

        Log.e("FrgReadSrc", "onDestroy 부분(FrgReadSrc 11번)")
    }

    // 프래그먼트 연결해제 시 호출(1회)
    override fun onDetach() {
        super.onDetach()

        Log.e("FrgReadSrc", "onDetach 부분(FrgReadSrc 12번)")
    }
}