package com.android.hw_pjh

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.loading)

        // 초기화 페이지 관련 로직
        // 1) 레이아웃 전시 로직
        setContentView(R.layout.sign_page)

        // 2) 초기화 프로세스 관련 객체 생성
        var Prc_00:prcAcc = prcAcc(); var goNext:Boolean = false

        // 3-1) 회원정보 테이블(로그인) 객체 초기화
        var orgInfo:accInfo; var orgInfoTabRef:MutableList<accInfo> = mutableListOf()
        var orgInfoTab:accTab1
        // 3-2) 회원정보 테이블(로그인) 객체화
        orgInfoTab = Prc_00.genAccSign()

        // 4-1) 회원정보 테이블(자기소개) 객체 초기화
        var orgData:accData; var orgDataTabRef:MutableList<accData> = mutableListOf()
        var orgDataTab:accTab2
        //4-2) 회원정보 테이블(자기소개) 객체화
        orgDataTab = Prc_00.genAccData()

        // 본 액티비티 호출 및 회원정보 테이블(로그인, 자기소개) 인텐트 전송
        // 1) 인텐트 전송용 회원정보 테이블(로그인, 자기소개) 객체화 여부 점검?
        //    문제없는데 필요한가?

        // 2) 회원정보 테이블(로그인, 자기소개) 전송
        val intStart = Intent(this, SignInActivity::class.java)
        intStart.putExtra("Tab_LogIn_0", orgInfoTab)
        intStart.putExtra("Tab_Page_0", orgDataTab)
        startActivity(intStart); finish()
    }
}