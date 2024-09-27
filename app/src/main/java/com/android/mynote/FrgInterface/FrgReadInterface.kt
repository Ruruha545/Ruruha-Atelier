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
import java.io.InputStream
import java.io.InputStreamReader

interface FrgReadInterface {

//    // 이하 파일 열람용 방법 1번
//    private val pickTXT: ActivityResultLauncher<String> =
//        registerForActivityResult(
//            ActivityResultContracts.GetContent()
//        ){
//                tgtUri: Uri? ->
//            tgtUri?.let{
//                val tgtText: String = readerTXT(it)
//                dispView = bindingRead.textView
//                dispView.text = tgtText
//
//                // 선택된 TXT파일 Uri정보 객체화
//                readUri = tgtUri
//
//                // 열람하는 파일에 대한 각종 권한 부여 로직
////                requireContext().contentResolver.takePersistableUriPermission(
////                    readUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
////                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
////                )
//            }
//        }
//    // 파일 선택기 내부 동작용 코드
//    private fun readerTXT(nowUriR: Uri) : String {
//
//        // 열람용 스트링빌더 객체화
//        val readStringBuilder: StringBuilder = StringBuilder()
//
//        // 파일 출력용 로직
//        try{
//            val inpStrRead: InputStream? = requireContext().contentResolver
//                .openInputStream(nowUriR)
//            val buffReaderRead: BufferedReader = BufferedReader(InputStreamReader(inpStrRead))
//
//            var textLineRead: String?
//            while(buffReaderRead.readLine().also { textLineRead = it } != null){
//                readStringBuilder.append(textLineRead).append("\n")
//            }
//
//            buffReaderRead.close()
//        }
//
//        // 예외처리 로직
//        catch(e: Exception){
//            Log.e("FrgReadSrc", "파일 선택기 1번 동작 간 발생")
//            Toast.makeText(requireContext(), "텍스트 방법 1번 에러 발생",
//                Toast.LENGTH_SHORT).show()
//        }
//        return readStringBuilder.toString()
//    }
//    // 이상 파일 열람용 방법 1번
//
//    // 이하 파일 열람용 방법 2번
//    // MediaStore API 통제용 변수(modeType) 객체화
//    private val modeType : Int = 5
//
//    // TXT 파일 열람용 파일 탐색기 호출 코드
//    private fun fileOpener(){
//        val tgtFile: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply{
//            addCategory(Intent.CATEGORY_OPENABLE)
//            type = "*/*"
//        }
//        startActivityForResult(tgtFile, modeType)
//    }
//
//    // 파일 탐색기 파일 선택 로직 코드
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == modeType && resultCode == Activity.RESULT_OK){
//            data?.data?.let{
//                    ele -> txtDisplayer(ele)
//            }
//        }
//    }
//
//    // TXT 파일 열람 로직 코드
//    private fun txtDisplayer(tgtUri_2: Uri){
//        // 접근할 컨텐츠 객체화
//        val contResol: ContentResolver = requireContext().contentResolver
//
//        // 파일을 읽어서 텍스트 뷰에 표시하는 로직
//        try{
//            val inputPrc_2: InputStream? = contResol.openInputStream(tgtUri_2)
//            val reader_2: BufferedReader = BufferedReader(InputStreamReader(inputPrc_2))
//            val textBuilder_2: StringBuilder = StringBuilder()
//            var textLine_2: String?
//
//            while(reader_2.readLine().also{ textLine_2 = it } != null){
//                textBuilder_2.append(textLine_2).append("\n")
//            }
//            dispView.text = textBuilder_2.toString()
//
//            reader_2.close()
//            inputPrc_2?.close()
//        }
//        catch(e: Exception){
//            Log.e("FrgReadSrc", "파일 선택기 2번 동작 간 발생")
//            Toast.makeText(requireContext(), "텍스트 방법 2번 에러 발생",
//                Toast.LENGTH_SHORT).show()
//        }
//    }
//    // 이상 파일 열람용 방법 2번
}