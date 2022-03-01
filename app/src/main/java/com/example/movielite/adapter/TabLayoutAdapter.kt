package com.example.movielite.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movielite.ui.MainFragment
import com.example.movielite.ui.TopRatedFragment


internal class MyAdapter(c: Context, fm: FragmentManager?, totalTabs: Int) :
    FragmentPagerAdapter(fm) {
    var context: Context
    var totalTabs: Int
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MainFragment()
            }
            1 -> {
                TopRatedFragment()
            }
            2 -> {
                FinalFragment()
            }
            else -> null
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

    init {
        context = c
        this.totalTabs = totalTabs
    }
}