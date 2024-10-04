package com.android.mynote

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.android.mynote.Adapter.MenuSelecter
import com.android.mynote.Fragment.FrgReadSrc
import com.android.mynote.Fragment.FrgWriteSrc
import com.android.mynote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 뷰 바인딩용 코드
    private lateinit var bindingMain: ActivityMainBinding

    // 프래그먼트 로딩용 코드
    private fun loadFrag(frgTgt : Fragment) : Unit {
        val transFrag : FragmentTransaction = supportFragmentManager.beginTransaction()
        transFrag.replace(R.id.container_fragment, frgTgt)
        transFrag.commit()
    }

    // 뷰페이저 및 어댑터 정의용 코드
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: MenuSelecter

    // 액티비티 최초 생성 시 호출(1회)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩 객체화 로직
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // 뷰페이저 및 어댑터 객체화 로직
        viewPager = bindingMain.containerMode
        pagerAdapter = MenuSelecter(this)
        viewPager.adapter = pagerAdapter

        // 뷰페이저 반응용 로직
        pagerAdapter.setClickListener(object: MenuSelecter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                // 프래그먼트 로드 시, 뷰페이저 숨기는 로직
                viewPager.visibility = ViewPager2.GONE

                // 프래그먼트 로드
                val newFrag: Fragment = when (position){
                    0 -> FrgReadSrc()
                    else -> FrgWriteSrc()
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, newFrag)
                    .addToBackStack(null)
                    .commit()
            }
        })

        // 프래그먼트 종료 후 복귀 시 리스너 동작 로직
        supportFragmentManager.addOnBackStackChangedListener {
            // 백스택 제거 시 동작
            if (supportFragmentManager.backStackEntryCount == 0) {
                viewPager.visibility = ViewPager2.VISIBLE
            }
        }

        Log.e("MainActivity", "onCreate 영역(MainActivity 1번)")
    }

    // 액티비티 시작 시 호출(조건부 n회)
    override fun onStart() {
        super.onStart()

        Log.e("MainActivity", "onStart 영역(MainActivity 2번)")
    }


    // 액티비티 상호작용 시 호출(조건부 n회)
    override fun onResume() {
        super.onResume()

        Log.e("MainActivity", "onResume 영역(MainActivity 3번)")
    }


    // 액티비티 일시정지 시 호출(조건부 n회)
    override fun onPause() {
        super.onPause()

        Log.e("MainActivity", "onPause 영역(MainActivity 4번)")
    }


    // 액티비티 중단 시 호출(조건부 n회)
    override fun onStop() {
        super.onStop()

        Log.e("MainActivity", "onStop 영역(MainActivity 5번)")
    }


    // 액티비티 종료/제거 시 호출(1회)
    override fun onDestroy() {
        super.onDestroy()

        Log.e("MainActivity", "onDestroy 영역(MainActivity 6번)")
    }


    // 액티비티 중단(onStop) 후 재시작 시 호출(조건부 n회)
    override fun onRestart() {
        super.onRestart()

        Log.e("MainActivity", "onRestart 영역(MainActivity 7번)")
    }
}