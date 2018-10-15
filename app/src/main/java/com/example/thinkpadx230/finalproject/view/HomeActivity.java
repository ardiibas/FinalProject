package com.example.thinkpadx230.finalproject.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.thinkpadx230.finalproject.R;
import com.example.thinkpadx230.finalproject.adapter.ViewPagerAdapater;
import com.example.thinkpadx230.finalproject.fragment.NowPlaying;
import com.example.thinkpadx230.finalproject.fragment.UpComing;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.main_tab);
        ViewPager viewPager = findViewById(R.id.main_view_pager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setupTab();

        if (savedInstanceState == null) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem());
        }

    }

    private void setupTab() {
        TextView tabSatu = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabSatu.setText(getString(R.string.now_playing));
        Objects.requireNonNull(tabLayout.getTabAt(0)).setCustomView(tabSatu);

        TextView tabDua = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabDua.setText(getString(R.string.upcoming));
        Objects.requireNonNull(tabLayout.getTabAt(1)).setCustomView(tabDua);

        TextView tabTiga = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTiga.setText(getString(R.string.favorit));
        Objects.requireNonNull(tabLayout.getTabAt(2)).setCustomView(tabTiga);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapater adapter = new ViewPagerAdapater(getSupportFragmentManager());
        adapter.addFragment(new NowPlaying(), getString(R.string.now_playing));
        adapter.addFragment(new UpComing(), getString(R.string.upcoming));
        adapter.addFragment(new UpComing(), getString(R.string.favorit));
        viewPager.setAdapter(adapter);
    }
}
