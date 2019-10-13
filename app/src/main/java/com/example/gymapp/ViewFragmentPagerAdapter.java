package com.example.gymapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {

    private CharSequence[] tabTitles = {"String","Graph"};

    public ViewFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new ViewStringFragment();
            case 1:
                return new ViewGraphFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

}
