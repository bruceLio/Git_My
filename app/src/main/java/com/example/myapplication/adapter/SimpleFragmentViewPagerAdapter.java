package com.example.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.fragment.SimpleFragment;

import java.util.List;

public class SimpleFragmentViewPagerAdapter extends FragmentPagerAdapter {

    private final List<String> titles;
    private Fragment[] fragments;

    public SimpleFragmentViewPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
        fragments = new Fragment[titles.size()];
    }

    @Override
    public Fragment getItem(int i) {
        if (fragments[i] == null) {
            fragments[i] = SimpleFragment.getInstance(titles.get(i));
        }
        return fragments[i];
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
