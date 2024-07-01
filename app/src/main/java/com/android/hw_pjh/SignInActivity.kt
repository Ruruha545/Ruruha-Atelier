package com.android.hw_pjh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 로그인 페이지 레이아웃 관련 로직
        // 1) 레이아웃 전시 로직
        setContentView(R.layout.sign_page)

        // 2) 에디트 뷰와 연결된 객체 생성
        var spc_ID:EditText = findViewById<EditText>(R.id.inputID)
        var spc_Pw:EditText = findViewById<EditText>(R.id.inputPW)

        // 3) 버튼 뷰와 연결된 객체 생성
        val bt_sign:Button = findViewById<Button>(R.id.bt_sign)
        val bt_cre_acc:Button = findViewById<Button>(R.id.bt_cre_acc)


        // 로그인 절차 시 필요한 객체 생성
        // 1) 프로세스 관련 객체
        var Prc_01:prcAcc = prcAcc(); var goNext:Boolean = false

        // 2-1) 회원정보 테이블(로그인) 관련 객체
        var table1:MutableList<accInfo> = mutableListOf()
        var LogInTab:accTab1 = accTab1(table1)
        LogInTab = intent.getSerializableExtra("Tab_LogIn_0") as? accTab1 ?: LogInTab

        // 2-2) 회원정보 테이블(자기소개) 관련 객체
        var table2:MutableList<accData> = mutableListOf()
        var PageTab:accTab2 = accTab2(table2)
        PageTab = intent.getSerializableExtra("Tab_Page_0") as? accTab2 ?: PageTab

        // 3) 토스트 메시지 관련 객체
        val emtidToast: Toast = Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT)
        val emtpwToast: Toast = Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT)
        val emtallToast: Toast = Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT)
        val wrongChkToast: Toast = Toast.makeText(this, "아이디 또는 비밀번호가 틀립니다", Toast.LENGTH_SHORT)


        // 버튼 클릭 시 처리되는 로직
        // 0-1) 로그인 버튼 클릭 시 로직
        bt_sign.setOnClickListener{
            // 1) 로그인 페이지 입력정보 관련 객체 정의
            var inpAcc:accInfo;
            var inputID:String? = spc_ID.text.toString(); var inputPW:String? = spc_Pw.text.toString()

            // 2) 입력받은 로그인 정보 검증
            if(inputID == null){
                emtidToast.show()
            }
            else if(inputPW == null){
                emtpwToast.show()
            }
            else if(inputID == null && inputPW == null){
                emtallToast.show()
            }
            else{
                // 3) 입력받은 로그인 정보로 인증용 객체 생성
                inpAcc = accInfo(inputID,inputPW)

                // 4) 회원정보 테이블 內 인증용 객체 정보 일치여부 대조
                goNext = Prc_01.ChkAcc(inpAcc,LogInTab)

                // 4-1) 회원정보 일치 시
                if(goNext == true){
                    // 5) 자기소개 액티비티 호출 및 데이터 전송(회원정보 테이블 2종, 인증된 ID값), 현 액티비티 종료
                    val intHome = Intent(this, HomeActivity::class.java)
                    intHome.putExtra("Tab_LogIn_1", LogInTab)
                    intHome.putExtra("Tab_Page_1", PageTab)
                    intHome.putExtra("Key_ID", inputID)
                    startActivity(intHome); finish()
                }
                // 4-2) 회원정보 미일치 시
                else{
                    wrongChkToast.show()
                }
            }
        }

        // 0-2) 회원가입 버튼 클릭 시 로직
        bt_cre_acc.setOnClickListener {
            // 1) 회원가입 액티비티 호출 및 데이터 전송(회원정보 테이블 2종), 현 액티비티 종료
            val intSignUp = Intent(this, SignUpActivity::class.java)
            intSignUp.putExtra("Tab_LogIn_2", LogInTab)
            intSignUp.putExtra("Tab_Page_2", PageTab)
            startActivity(intSignUp); finish()
        }
    }
}