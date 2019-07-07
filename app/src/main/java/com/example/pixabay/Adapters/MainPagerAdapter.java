package com.example.pixabay.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> listOfFragments = new ArrayList<>();
    public static List<String> fragmentTitles = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public void addFragments(Fragment fragment, String title){
        listOfFragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return listOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return listOfFragments.size();
    }

    /*
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
    */
}
