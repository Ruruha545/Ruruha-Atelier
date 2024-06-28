package com.android.hw_pjh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 회원가입 페이지 레이아웃 관련 로직
        // 1) 레이아웃 전시 로직
        setContentView(R.layout.cre_acc_page)

        // 2) 버튼 뷰와 연결된 객체 생성
        val bt_roste: Button = findViewById<Button>(R.id.bt_roster)
        val bt_back2: Button = findViewById<Button>(R.id.bt_backer)

        // 3) 에디트 뷰와 연결된 객체 생성
        var spc_ID: EditText = findViewById<EditText>(R.id.spc_cre_id)
        var spc_Pw: EditText = findViewById<EditText>(R.id.spc_cre_pw)
        var spc_Name: EditText = findViewById<EditText>(R.id.spc_cre_name)
        var spc_School: EditText = findViewById<EditText>(R.id.spc_cre_school)
        var spc_Hobby: EditText = findViewById<EditText>(R.id.spc_cre_hobby)
        var spc_One_Word: EditText = findViewById<EditText>(R.id.spc_cre_one_word)

        // 4) 토스트 메시지 관련 객체
        val emtIdToast: Toast = Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT)
        val emtPwToast: Toast = Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT)
        val emtNameToast: Toast = Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT)
        val emtSchoolToast: Toast = Toast.makeText(this, "소속을 입력해주세요", Toast.LENGTH_SHORT)
        val emtHobbyToast: Toast = Toast.makeText(this, "취미를 입력해주세요", Toast.LENGTH_SHORT)
        val emtOneWordToast: Toast = Toast.makeText(this, "한마디를 입력해주세요", Toast.LENGTH_SHORT)


        // 자기소개 전달 시 필요한 객체 생성
        // 1) 프로세스 관련 객체
        var Prc_03:prcAcc = prcAcc(); var goNext:Boolean = false

        // 2-1) 넘겨받은 회원정보 테이블(로그인) 관련 객체
        var table1:MutableList<accInfo> = mutableListOf()
        var LogInTab:accTab1 = accTab1(table1)
        LogInTab = intent.getSerializableExtra("Tab_LogIn_2") as? accTab1 ?: LogInTab

        // 2-2) 넘겨받은 회원정보 테이블(자기소개) 관련 객체
        var table2:MutableList<accData> = mutableListOf()
        var PageTab:accTab2 = accTab2(table2)
        PageTab = intent.getSerializableExtra("Tab_Page_2") as? accTab2 ?: PageTab

        // 3) 기입한 회원정보(로그인, 자기소개) 관련 객체
        var nowInfo:accInfo; var nowInfoTab:accTab1
        var nowData:accData; var nowDataTab:accTab2

        // 4) 인증된 회원정보(ID) 관련 객체
        var nowID:String?

        // 0-1) 회원가입 버튼 클릭 시 로직
        bt_roste.setOnClickListener {
            // 1-1) 회원정보(로그인) 객체화
            nowInfo = accInfo(spc_ID.text.toString(),spc_Pw.text.toString())

            // 1-2) 회원정보 테이블(로그인) 갱신
            nowInfoTab = Prc_03.AddInfo(LogInTab,nowInfo)


            // 2) 회원정보(자기소개) 객체화
            nowData = accData(spc_ID.text.toString(), spc_Name.text.toString(), spc_School.text.toString(),
                spc_Hobby.text.toString(), spc_One_Word.text.toString(), 0)

            // 2-1) 회원정보 테이블(자기소개) 갱신
            nowDataTab = Prc_03.AddData(PageTab,nowData)

            // 3) 인증된 회원정보(ID) 객체 생성
            nowID = nowInfo.v_id


            // 자기소개 액티비티 호출 및 데이터 전송(회원정보 테이블 2종, 인증된 ID값), 현 액티비티 종료
            val intHome = Intent(this, HomeActivity::class.java)
            intHome.putExtra("Tab_LogIn_1", nowInfoTab)
            intHome.putExtra("Tab_Page_1", nowDataTab)
            intHome.putExtra("Key_ID", nowID)
            startActivity(intHome); finish()
        }

        // 0-2) 뒤로가기 버튼 클릭 시 로직
        bt_back2.setOnClickListener {
            val intSignIn = Intent(this, SignInActivity::class.java)
            startActivity(intSignIn); finish()
        }
    }
}