package com.example.gymapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {

    private CharSequence[] tabTitles = {"String","Graph"};
    private String result;

    public ViewFragmentPagerAdapter(FragmentManager fm, String result_text){
        super(fm);
        result = result_text;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                ViewStringFragment fragment = new ViewStringFragment();
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                fragment.setArguments(bundle);
                return fragment;
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
