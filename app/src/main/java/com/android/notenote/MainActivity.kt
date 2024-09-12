package com.android.notenote

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceControl.Transaction
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.notenote.Fragment.FrgReadSrc
import com.android.notenote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 뷰 바인딩용 코드
    private lateinit var bindingMain: ActivityMainBinding

    // 프래그먼트 로딩용 코드
    private fun loadFrag(frgTgt : Fragment) : Unit {
        val transFrag : FragmentTransaction = supportFragmentManager.beginTransaction()
        transFrag.replace(R.id.container_fragment, frgTgt)
        transFrag.commit()
    }

    // 액티비티 최초 생성 시 호출(1회)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩 객체화 로직
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // 프래그먼트 로딩 로직(읽기모드 로딩)
        if(savedInstanceState == null){
            loadFrag(FrgReadSrc())
        }
    }

    // 액티비티 시작 시 호출(조건부 n회)
    override fun onStart() {
        super.onStart()
    }


    // 액티비티 상호작용 시 호출(조건부 n회)
    override fun onResume() {
        super.onResume()
    }


    // 액티비티 일시정지 시 호출(조건부 n회)
    override fun onPause() {
        super.onPause()
    }


    // 액티비티 중단 시 호출(조건부 n회)
    override fun onStop() {
        super.onStop()
    }


    // 액티비티 종료/제거 시 호출(1회)
    override fun onDestroy() {
        super.onDestroy()
    }


    // 액티비티 중단(onStop) 후 재시작 시 호출(조건부 n회)
    override fun onRestart() {
        super.onRestart()
    }
}