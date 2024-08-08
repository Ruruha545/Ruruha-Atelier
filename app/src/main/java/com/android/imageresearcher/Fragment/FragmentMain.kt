package com.android.imageresearcher.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.imageresearcher.Adapter.ViewpagerAdapter
import com.android.imageresearcher.databinding.ActivityMainBinding
import com.android.imageresearcher.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class FragmentMain: Fragment() {

    // 뷰 바인딩용 객체 추가(fragment_main.xml)
    lateinit var fragMainBinding: FragmentMainBinding

    val mainViewpager by lazy {
        ViewpagerAdapter(this)
    }

    lateinit var TopFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰 바인딩 초기화 작업
        fragMainBinding = FragmentMainBinding.inflate(layoutInflater)
        return fragMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TopFragmentManager = parentFragmentManager

        fragMainBinding.fragmentMainViewPager2.adapter = mainViewpager

        TabLayoutMediator(fragMainBinding.fragmentMainTabContainer, fragMainBinding.fragmentMainViewPager2)
        { tab, position ->
            tab.text = when (position) {
                0 -> "이미지 검색"
                1 -> "내 보관함"
                else -> ""
            }
        }.attach()

    }
}