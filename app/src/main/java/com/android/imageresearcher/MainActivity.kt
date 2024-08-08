package com.android.imageresearcher

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.imageresearcher.Fragment.FragmentMain
import com.android.imageresearcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        
    }

    // 바인딩용 구문 추가(activity_main.xml)
    lateinit var bindingActMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 프래그먼트 매니저, 프래그먼트 트랜색션 생성
        val actFragManager: FragmentManager = supportFragmentManager
        val actFragTransaction: FragmentTransaction = actFragManager.beginTransaction()

        // 프래그먼트 인스턴스 생성
        val mainFragment: Fragment = FragmentMain()

        // 프래그먼트 트랜색션에 생성한 인스턴스(FragmentMain)를 할당
        actFragTransaction.add(bindingActMain.fragmentContainerView.id, mainFragment)

        // 트랜색션 커밋
        actFragTransaction.commit()


        enableEdgeToEdge()
        setContentView(bindingActMain.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}