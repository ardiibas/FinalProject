package com.example.thinkpadx230.finalproject.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapater extends FragmentPagerAdapter {

    private final List<String> titleTab = new ArrayList<>();
    private final List<Fragment> fragmentTab = new ArrayList<>();

    public ViewPagerAdapater(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String s) {
        fragmentTab.add(fragment);
        titleTab.add(s);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleTab.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentTab.get(i);
    }

    @Override
    public int getCount() {
        return fragmentTab.size();
    }
}
