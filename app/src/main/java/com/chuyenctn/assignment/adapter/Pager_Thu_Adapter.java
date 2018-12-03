package com.chuyenctn.assignment.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chuyenctn.assignment.fragment.Fragment_Khoanthu;
import com.chuyenctn.assignment.fragment.Fragment_Loaithu;

public class Pager_Thu_Adapter extends FragmentStatePagerAdapter {
    public Pager_Thu_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new Fragment_Loaithu();
                break;
            case 1:
                fragment = new Fragment_Khoanthu();
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
                title = "Loại thu";
                break;
            case 1:
                title = "Khoản thu";
                break;
        }
        return title;
    }
}
