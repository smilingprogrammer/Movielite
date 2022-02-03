package com.example.movielite.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.movielite.ui.MainFragment;
import com.example.movielite.ui.TopRatedFragment;
import com.google.android.material.tabs.TabLayout;

public class NonActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new MainFragment(), "Home");
        vpAdapter.addFragment(new TopRatedFragment(), "Top Rated");
        vpAdapter.addFragment(new FinalFragment(), "Wednesday");
        viewPager.setAdapter(vpAdapter);
    }
}
