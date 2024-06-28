package com.android.hw_pjh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 자기소개 페이지 레이아웃 관련 로직
        // 1) 레이아웃 전시 로직
        setContentView(R.layout.my_acc_page)

        // 2) 개인 텍스트 뷰와 연결된 객체 생성
        var spc_Name: TextView = findViewById<TextView>(R.id.spc_Name)
        var spc_School: TextView = findViewById<TextView>(R.id.spc_School)
        var spc_Hobby: TextView = findViewById<TextView>(R.id.spc_Hobby)
        var spc_OneWord: TextView = findViewById<TextView>(R.id.spc_OneWord)
        var spc_Picture: ImageView = findViewById<ImageView>(R.id.profile)

        // 3) 버튼 뷰와 연결된 객체 생성
        val bt_back1: Button = findViewById<Button>(R.id.bt_Back)

        // 4) 토스트 메시지 관련 객체
        val emtNameToast: Toast = Toast.makeText(this, "로그인 정보가 없습니다.", Toast.LENGTH_SHORT)
        val getBackToast: Toast = Toast.makeText(this, "로그인 화면으로 돌아갑니다.", Toast.LENGTH_SHORT)

        // 자기소개 전시 시 필요한 객체 생성
        // 1) 프로세스 관련 객체
        var Prc_02:prcAcc = prcAcc(); var goNext:Boolean = false

        // 2-1) 넘겨받은 회원정보 테이블(로그인) 관련 객체
        var table1:MutableList<accInfo> = mutableListOf()
        var LogInTab:accTab1 = accTab1(table1)
        LogInTab = intent.getSerializableExtra("Tab_LogIn_1") as? accTab1 ?: LogInTab

        // 2-2) 넘겨받은 회원정보 테이블(자기소개) 관련 객체
        var table2:MutableList<accData> = mutableListOf()
        var PageTab:accTab2 = accTab2(table2)
        PageTab = intent.getSerializableExtra("Tab_Page_1") as? accTab2 ?: PageTab

        // 3) 회원정보(인증완료) 관련 객체
        var nowAcc:accData; val nowID:String? = intent.getStringExtra("Key_ID")

        // 자기소개 전시 로직
        // 인증정보 정확 시, 자기소개 화면 전시
        if(nowID != null){
            // 1) 일치하는 계정정보 구체화
            nowAcc = Prc_02.DispAcc(nowID, PageTab)

            // 2) 계정정보 화면 전시
            spc_Name.text = nowAcc.name; spc_School.text = nowAcc.school
            spc_Hobby.text = nowAcc.hobby; spc_OneWord.text = nowAcc.oneWord
            spc_Picture.setImageResource(nowAcc.pic)
        }
        // 인증정보 부정확 시, 로그인 화면으로 이동, 현 액티비티 종료
        else{
            emtNameToast.show(); getBackToast.show()
            val intSignIn = Intent(this, SignInActivity::class.java)
            intSignIn.putExtra("Tab_LogIn_1", LogInTab)
            intSignIn.putExtra("Tab_Page_1", PageTab)
            startActivity(intSignIn); finish()
        }

        // 나가기 버튼 클릭 시 처리 로직
        bt_back1.setOnClickListener {
            val intSignIn = Intent(this, SignInActivity::class.java)
            intSignIn.putExtra("Tab_LogIn_0", LogInTab)
            intSignIn.putExtra("Tab_Page_0", PageTab)
            startActivity(intSignIn); finish()
        }
    }
}