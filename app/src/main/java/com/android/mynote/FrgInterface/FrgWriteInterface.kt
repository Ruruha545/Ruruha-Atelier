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
}