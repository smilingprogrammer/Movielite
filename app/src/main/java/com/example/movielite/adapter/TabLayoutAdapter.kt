package com.example.movielite.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movielite.ui.MainFragment
import com.example.movielite.ui.TopRatedFragment

//
//class MyAdapter(c: Context, fm: FragmentManager?, totalTabs: Int) : FragmentStateAdapter(fm) {
//    var context: Context
//    var totalTabs: Int
//
//    init {
//        context = c
//        this.totalTabs = totalTabs
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> {
//                MainFragment()
//            }
//            1 -> {
//                TopRatedFragment()
//            }
//            2 -> {
//                FinalFragment()
//            }
//            else -> null
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return totalTabs
//    }
//}