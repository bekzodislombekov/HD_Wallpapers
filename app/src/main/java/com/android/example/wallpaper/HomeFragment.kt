package com.android.example.wallpaper

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.example.wallpaper.adapters.PagerAdapter
import com.android.example.wallpaper.databinding.FragmentHomeBinding
import com.android.example.wallpaper.databinding.TabCircleBinding
import com.android.example.wallpaper.models.pixabay.Photo
import com.android.example.wallpaper.retrofit.ApiClient
import com.android.example.wallpaper.retrofit.ApiService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

const val API_KEY = "21501447-06c7cd416cd56b937c5ec286e"

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var list: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.elevation = 0F
        loadData()
        pagerAdapter = PagerAdapter(requireActivity(), list)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = list[position]
            }).attach()

        setTabs()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val circle = customView?.findViewById<LinearLayout>(R.id.circle)
                val tv = customView?.findViewById<TextView>(R.id.tab_tv)
                tv?.setTextColor(Color.parseColor("#BABABA"))
                circle?.visibility = View.INVISIBLE
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val circle = customView?.findViewById<LinearLayout>(R.id.circle)
                val tv = customView?.findViewById<TextView>(R.id.tab_tv)
                tv?.setTextColor(Color.WHITE)
                circle?.visibility = View.VISIBLE
            }
        })

        return binding.root
    }

    private fun setTabs() {
        val count = binding.tabLayout.tabCount
        for (i in 0 until count) {
            val bindingCircle = TabCircleBinding.inflate(layoutInflater)
            val circle = bindingCircle.circle
            bindingCircle.tabTv.text = list[i]
            if (i == 0) {
                circle.visibility = View.VISIBLE
                bindingCircle.tabTv.setTextColor(Color.WHITE)
            } else {
                bindingCircle.tabTv.setTextColor(Color.parseColor("#BABABA"))
                circle.visibility = View.INVISIBLE
            }
            binding.tabLayout.getTabAt(i)?.customView = bindingCircle.root
        }
    }

    private fun loadData() {
        list = ArrayList()
        list.add("Animal")
        list.add("Technology")
        list.add("Nature")
        list.add("Sport")
        list.add("Car")
        list.add("Flower")
    }
}