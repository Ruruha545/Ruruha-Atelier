package com.android.notenote.Fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.android.notenote.Manifest
import com.android.notenote.databinding.FrgReadBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class FrgReadSrc : Fragment() {

    // 뷰 바인딩용 객체 추가(소스 : frg_read.xml)
    lateinit var bindingRead : FrgReadBinding

    // 호스트 액티비티의 컨텍스트를 상속받기 위한 객체화
    lateinit var readContext: Context

    // 출력용 텍스트 뷰 객체화(지연)
    private lateinit var dispView : TextView

    // 이하 파일 열람용 방법 1번
    // SAF 활용, 파일 선택기 호출용 코드
    private val pickTXT: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()
        ){
            tgtUri: Uri? ->
            tgtUri?.let{
                val tgtText: String = readerTXT(it)
                dispView = bindingRead.textView
                dispView.text = tgtText
            }
        }

    // 파일 선택기 내부 동작용 코드
    private fun readerTXT(tgtUri_1: Uri) : String {
        val textBuilder_1: StringBuilder = StringBuilder()
        try{
            val inputPrc_1: InputStream? = requireContext().contentResolver.openInputStream(tgtUri_1)
            val reader_1: BufferedReader = BufferedReader(InputStreamReader(inputPrc_1))

            var textLine_1: String?
            while(reader_1.readLine().also { textLine_1 = it } != null){
                textBuilder_1.append(textLine_1).append("\n")
            }

            reader_1.close()
        }
        catch(e: Exception){
            Log.e("FrgReadSrc", "파일 선택기 1번 동작 간 발생")
            Toast.makeText(requireContext(), "텍스트 방법 1번 에러 발생",
                Toast.LENGTH_SHORT).show()
        }
        return textBuilder_1.toString()
    }
    // 이상 파일 열람용 방법 1번


    // 이하 파일 열람용 방법 2번
    // MediaStore API 통제용 변수(modeType) 객체화
    private val modeType : Int = 5

    // TXT 파일 열람용 파일 탐색기 호출 코드
    private fun fileOpener(){
        val tgtFile: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply{
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        startActivityForResult(tgtFile, modeType)
    }

    // 파일 탐색기 파일 선택 로직 코드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == modeType && resultCode == Activity.RESULT_OK){
            data?.data?.let{
                ele -> txtDisplayer(ele)
            }
        }
    }

    // TXT 파일 열람 로직 코드
    private fun txtDisplayer(tgtUri_2: Uri){
        // 파일을 읽어서 텍스트 뷰에 표시하는 로직
        try{
            val inputPrc_2: InputStream? = requireContext().contentResolver.openInputStream(tgtUri_2)
            val reader_2: BufferedReader = BufferedReader(InputStreamReader(inputPrc_2))
            val textBuilder_2: StringBuilder = StringBuilder()
            var textLine_2: String?

            while(reader_2.readLine().also{ textLine_2 = it } != null){
                textBuilder_2.append(textLine_2).append("\n")
            }
            reader_2.close()

            dispView.text = textBuilder_2.toString()
        }
        catch(e: Exception){
            Log.e("FrgReadSrc", "파일 선택기 2번 동작 간 발생")
            Toast.makeText(requireContext(), "텍스트 방법 2번 에러 발생",
                Toast.LENGTH_SHORT).show()
        }
    }
    // 이상 파일 열람용 방법 2번




    // 프래그먼트 연결 시 호출(1회)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 호스트 액티비티의 컨텍스트를 상속받는 로직
        readContext = context
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
        bindingRead = FrgReadBinding.inflate(layoutInflater)

        // 프래그먼트용 레이아웃 링크 로직
        return bindingRead.root
    }

    // 프래그먼트 UI 최신화 시 호출(반복)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 파일 열람용 버튼 동작 로직
        bindingRead.btnChgmode.setOnClickListener {
            pickTXT.launch("text")
        }
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


    // 텍스트 뷰 초기화용 로직




    // 범위 저장소 접근용 권한 요청/통제 로직
    // 권한이 없을 경우, 운영체제에 권한을 요청하는 로직
    if(ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.READ_EXTERNAL_STORAGE
    ) != PackageManager.PERMISSION_GRANTED
    ){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            modeType
        )
    }
    // 권한이 있을 경우, 프래그먼트에 통보하는 로직
    else{



    }





}