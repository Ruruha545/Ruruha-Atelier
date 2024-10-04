package com.android.mynote.FrgInterface

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter

interface FrgWriteInterface {

//    // 지정 파일 연결용 코드(구버전)
//    private fun writerTXT(nowUriW: Uri) : String{
//
//        // 작성용 스트링빌더 객체화
//        val writeStringBuilder: StringBuilder = StringBuilder()
//
//        // 파일 출력용 로직
//        try{
//            val inpStrWrite: InputStream? = requireContext().contentResolver
//                .openInputStream(nowUriW)
//            val buffReaderWrite: BufferedReader = BufferedReader(InputStreamReader(inpStrWrite))
//            var textLineWrite: String?
//            while( buffReaderWrite.readLine().also{ textLineWrite = it } != null ){
//                writeStringBuilder.append(textLineWrite).append("\n")
//            }
//            buffReaderWrite.close()
//        }
//
//        // 예외처리 로직
//        catch(e: Exception){
//            Log.e("FrgWriteSrc", "writerTXT 동작 간 에러 발생")
//            Toast.makeText(requireContext(), "writerTXT 코드 에러 발생",
//                Toast.LENGTH_SHORT).show()
//        }
//
//        return writeStringBuilder.toString()
//    }
//
//    // 작성 파일 저장용 코드
//    private fun saverTXT(nowUriW: Uri, cargo: String){
//        // 접근할 컨텐츠 객체화
//        val saveContResol: ContentResolver = requireContext().contentResolver
//
//        // 파일 저장 성공 로직
//        try{
//            // Uri 출력용 스트림 객체화
//            val outStr: OutputStream? = saveContResol.openOutputStream(nowUriW, "w")
//
//            // 데이터 덮어쓰기 성공 로직
//            outStr?.let{
//                val bufWriter: BufferedWriter = BufferedWriter(OutputStreamWriter(it))
//                bufWriter.write(cargo)
//                bufWriter.flush()
//                bufWriter.close()
//
//                // 성공 확인용 메세지 출력
//                Toast.makeText(requireContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show()
//            }
//            // 데이터 덮어쓰기 실패 로직
//                ?: run{
//                    Toast.makeText(requireContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show()
//                }
//
//            outStr?.close()
//        }
//        // 파일 저장 실패 로직
//        catch (e: Exception){
//            Log.e("FrgWriteSrc", "saverTXT 동작 간 에러 발생")
//            Toast.makeText(requireContext(), "saverTXT 코드 에러 발생",
//                Toast.LENGTH_SHORT).show()
//        }
//    }

    //    // 이하 파일 작성용 코드(SAF 활용), 구버전
//    // 1) 파일 저장용 다이얼로그 출력 메서드
//    private fun dialToNameFile() {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
//        builder.setTitle("신규 파일 생성")
//
//        // 입력용 에디트 뷰 생성--속성 정의
//        val inputSpace: EditText = EditText(requireContext())
//        inputSpace.hint = "신규 파일명을 입력해주세요"
//        builder.setView(inputSpace)
//
//        // 다이얼로그 버튼 뷰 생성--속성 정의
//        builder.setPositiveButton("저장하기") { dial: DialogInterface, _: Int ->
//
//            // 파일명 입력받는 로직
//            val newName: String = inputSpace.text.toString()
//
//            if (newName.isNotEmpty()) {
//                createTXT(newName)
//            } else {
//                Toast.makeText(
//                    requireContext(), "파일명을 입력하세요",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//            dial.dismiss()
//        }
//
//        builder.setNegativeButton("취소") { dial: DialogInterface, _: Int ->
//            dial.cancel()
//        }
//
//        builder.show()
//    }
//
//    // 2) 신규 파일 생성용 메서드
//    private fun createTXT(fileName: String) {
//        val saveIntent: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//            addCategory(Intent.CATEGORY_OPENABLE)
//            type = "text/plain"
//            putExtra(Intent.EXTRA_TITLE, "$fileName.txt")
//        }
//        createTXTLauncher.launch(saveIntent)
//    }
//
//    // 3) 신규 파일 생성 동작용 액티비티 런쳐 객체화
//    private val createTXTLauncher: ActivityResultLauncher<Intent> =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
//                val tgtUri: Uri? = result.data?.data
//                tgtUri?.let {
//                    writeUri = it
//                    saveTXT(it, dispView.text.toString())
//                }
//            } else {
//                Toast.makeText(requireContext(), "파일 저장실패 상태", Toast.LENGTH_SHORT).show()
//            }
//        }
//    // 이상 파일 작성용 코드(SAF 활용)

}