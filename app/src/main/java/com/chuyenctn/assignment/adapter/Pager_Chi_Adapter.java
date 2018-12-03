package com.chuyenctn.assignment.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chuyenctn.assignment.fragment.Fragment_Khoanchi;
import com.chuyenctn.assignment.fragment.Fragment_Loaichi;

public class Pager_Chi_Adapter extends FragmentStatePagerAdapter {
    public Pager_Chi_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new Fragment_Loaichi();
                break;
            case 1:
                fragment = new Fragment_Khoanchi();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Loại chi";
                break;
            case 1:
                title = "Khoản chi";
                break;
        }
        return title;
    }
}
