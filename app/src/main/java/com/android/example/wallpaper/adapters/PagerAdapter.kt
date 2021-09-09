package com.android.example.wallpaper.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.example.wallpaper.PagerFragment
import com.android.example.wallpaper.models.pixabay.Photo

class PagerAdapter(fragmentActivity: FragmentActivity, private val list: List<String>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        return PagerFragment.newInstance(list[position])
    }
}