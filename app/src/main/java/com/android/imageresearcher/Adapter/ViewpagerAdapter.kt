package com.android.imageresearcher.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.imageresearcher.Fragment.FragmentMybox
import com.android.imageresearcher.Fragment.FragmentResearch

class ViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){

    val pageNumber: List<Fragment> = listOf(FragmentResearch(), FragmentMybox())
    val pageTag: List<String> = listOf(FragmentResearch.TabName, FragmentMybox.TabName)

    override fun createFragment(position: Int): Fragment {
        return pageNumber[position]
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return pageNumber.size
        TODO("Not yet implemented")
    }
}