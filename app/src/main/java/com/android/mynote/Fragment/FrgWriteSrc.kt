package com.android.mynote.Fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.android.mynote.R
import com.android.mynote.databinding.FrgReadBinding
import com.android.mynote.databinding.FrgWriteBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter

class FrgWriteSrc: Fragment() {

    // 뷰 바인딩용 객체 추가(소스 : frg_write.xml)
    lateinit var bindingWrite : FrgWriteBinding

    // 호스트 액티비티의 컨텍스트를 상속받기 위한 객체 추가
    lateinit var writeContext: Context

    // 프래그먼트 선택 TXT 파일의 Uri 정보 객체 추가
    private var writeUri: Uri? = null

    // 출력용 텍스트 뷰 객체화(지연)
    private lateinit var dispView : EditText

    // 팝업 메뉴 객체화(지연)
    private lateinit var fltActBtn: FloatingActionButton
    private lateinit var popWrite : PopupMenu

    // 에디트 뷰에 전시할 텍스트 지연 객체 추가
    private lateinit var reText: String

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
                    writeUri = it
                    readTXT(it)
                }
            }
            else{
                Toast.makeText(requireContext(), "파일 미선택 상태", Toast.LENGTH_SHORT).show()
            }
        }
    // 이상 파일 로드용 코드(SAF 활용)

    // 이하 파일 작성용 코드(SAF 활용)
    // 1) 파일 저장용 다이얼로그 출력 메서드
    private fun dialToNameFile(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("신규 파일 생성")

        // 입력용 에디트 뷰 생성--속성 정의
        val inputSpace: EditText = EditText(requireContext())
        inputSpace.hint = "신규 파일명을 입력해주세요"
        builder.setView(inputSpace)

        // 다이얼로그 버튼 뷰 생성--속성 정의
        builder.setPositiveButton("저장하기"){
                dial: DialogInterface, _: Int ->

            // 파일명 입력받는 로직
            val newName: String = inputSpace.text.toString()

            if(newName.isNotEmpty()){
                createTXT(newName)
            }
            else{
                Toast.makeText(requireContext(), "파일명을 입력하세요",
                    Toast.LENGTH_SHORT).show()
            }

            dial.dismiss()
        }

        builder.setNegativeButton("취소"){
                dial: DialogInterface, _: Int ->
            dial.cancel()
        }

        builder.show()
    }

    // 2) 신규 파일 생성용 메서드
    private fun createTXT(fileName: String){
        val saveIntent: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply{
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "$fileName.txt")
        }
        createTXTLauncher.launch(saveIntent)
    }

    // 3) 신규 파일 생성 동작용 액티비티 런쳐 객체화
    private val createTXTLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == Activity.RESULT_OK && result.data != null){
                val tgtUri: Uri? = result.data?.data
                tgtUri?.let{
                    writeUri = it
                    saveTXT(it, dispView.text.toString())
                }
            }
            else{
                Toast.makeText(requireContext(), "파일 저장실패 상태", Toast.LENGTH_SHORT).show()
            }
        }
    // 이상 파일 작성용 코드(SAF 활용)

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

            // 읽어온 텍스트 파일, 파일명 화면 전시
            dispView = bindingWrite.frgWriteEditMain
            dispView.setText(cargoText)

            bindingWrite.frgWriteTextTitle.text = getTXTName(tgtUri)

            readBuf.close()
        }

        // 파일 출력 실패용 로직
        catch (e: Exception){
            Log.e("FrgWriteSrc", "readTXT 동작 간 에러 발생")
            Toast.makeText(requireContext(), "readTXT 동작 간 에러 발생",
                Toast.LENGTH_SHORT).show()
        }
    }

    // 파일 저장용 메서드
    private fun saveTXT(tgtUri: Uri, contents: String){
        // 저장 성공용 로직
        try{
            val newStream: OutputStream? = requireContext().contentResolver.openOutputStream(tgtUri)
            val writeOut: OutputStreamWriter = OutputStreamWriter(newStream)

            writeOut.use{ it.write(contents) }

            Toast.makeText(requireContext(), "saveTXT 동작 성공, 파일 저장 완료",
                Toast.LENGTH_SHORT).show()
        }

        // 저장 실패용 로직
        catch(e: Exception){
            Log.e("FrgWriteSrc", "saveTXT 동작 간 에러 발생")
            Toast.makeText(requireContext(), "saveTXT 동작 간 에러 발생",
                Toast.LENGTH_SHORT).show()
        }
    }



    // 열람용 프래그먼트 호출 및 데이터 전달용 코드
    private fun startFrgWithTXT(){

        // 상태 전달용 번들 객체화
        val passbundle: Bundle = Bundle()
        passbundle.putParcelable("txtbox", writeUri)

        // 열람용 프래그먼트 객체화 및 번들 전달
        val newFragment: Fragment = FrgReadSrc()
        newFragment.arguments = passbundle

        // 프래그먼트 트랜잭션 작업 로직
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, newFragment)
            .addToBackStack(null)
            .commit()
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

        // 플로팅 액션 버튼 객체화 로직
        fltActBtn = bindingWrite.frgWriteBtnMenu

        // 전시용 에디트 뷰 객체화 로직
        dispView = bindingWrite.frgWriteEditMain

        // 수신한 번들 객체화
        arguments?.let{
            writeUri = it.getParcelable("txtbox")!!
        }

        // 텍스트 전시 로직
        writeUri?.let{
            Uri -> readTXT(Uri)
        }

        // 프래그먼트용 레이아웃 링크 로직
        return bindingWrite.root
    }

    // 프래그먼트 UI 최신화 시 호출(반복)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 파일 작성용 뷰 속성 고정시키는 로직
        // 뷰 속성 정보 변경(고정) 구문
        bindingWrite.frgWriteEditMain.isEnabled = true
        bindingWrite.frgWriteEditMain.isFocusable = true
        bindingWrite.frgWriteEditMain.isFocusableInTouchMode = true

        // 소프트 키보드 강제호출 구문
        bindingWrite.frgWriteEditMain.requestFocus()
        // 인풋 매니저 객체화 구문(초기화 >> 객체화)
        val keyboard: InputMethodManager = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        keyboard.showSoftInput(bindingWrite.frgWriteEditMain, InputMethodManager.SHOW_IMPLICIT)


        // 번들 수신여부에 따른 이벤트 처리 로직
        if(writeUri != null){
            // 기존파일 작성용 메서드 동작
            readTXT(writeUri!!)
        }
        else{
            // 수신한 번들이 없을 경우 미동작
        }

        // 메뉴 버튼 클릭 이벤트 처리 로직
        fltActBtn.setOnClickListener {
            // 출력용 팝업 메뉴 생성
            popWrite = PopupMenu(requireContext(), fltActBtn)
            popWrite.menuInflater.inflate(R.menu.write_menu_list, popWrite.menu)

            // 팝업 메뉴 아이템 클릭 이벤트 처리
            popWrite.setOnMenuItemClickListener { ele ->
                when (ele.itemId){
                    // 1) 파일 불러오기 선택
                    R.id.menu_loader -> {
                        // 파일 열람용 메서드 동작
                        openTXT()

                        true
                    }

                    // 2) 파일 저장하기 선택
                    R.id.menu_saver -> {
                        // a) 신규 파일 저장하기
                        if(writeUri == null){
                            // 신규파일 작성용 메서드 동작
                            dialToNameFile()
                        }
                        // b) 수정한 파일 저장하기
                        else{
                            // Uri 최신화, 파일 저장용 메서드 동작
                            writeUri?.let{ Uri ->
                                saveTXT(Uri, bindingWrite.frgWriteEditMain.text.toString())
                            }
                        }

                        true
                    }

                    // 3) 모드 전환하기 선택
                    R.id.menu_changer -> {
                        // 현재파일 작성여부 묻는 로직 추가하기!!
                        // 메서드 작성 필요!!

                        // 모드 변경용 메서드 동작
                        startFrgWithTXT()

                        true
                    }

                    // 4) 그 외(예외처리)
                    else -> false
                }
            }

            // 팝업 메뉴 표시
            popWrite.show()
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

        // 파일 작성용 이벤트 로직
        // 뷰 속성 정보 변경(고정) 구문
        bindingWrite.frgWriteEditMain.isEnabled = true
        bindingWrite.frgWriteEditMain.isFocusable = true
        bindingWrite.frgWriteEditMain.isFocusableInTouchMode = true

        // 소프트 키보드 강제호출 구문
        bindingWrite.frgWriteEditMain.requestFocus()
        // 인풋 매니저 객체화 구문(초기화 >> 객체화)
        val keyboard: InputMethodManager = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        keyboard.showSoftInput(bindingWrite.frgWriteEditMain, InputMethodManager.SHOW_IMPLICIT)
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