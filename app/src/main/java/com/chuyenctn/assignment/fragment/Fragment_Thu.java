package com.chuyenctn.assignment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.adapter.Pager_Thu_Adapter;

public class Fragment_Thu extends Fragment {
    private Pager_Thu_Adapter adapter;
    private TabLayout tablayoutThu;
    private ViewPager viewpagerThu;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        tablayoutThu = (TabLayout) view.findViewById(R.id.tablayout_thu);
        viewpagerThu = (ViewPager) view.findViewById(R.id.viewpager_thu);
        adapter = new Pager_Thu_Adapter(getChildFragmentManager());
        viewpagerThu.setAdapter(adapter);
        tablayoutThu.setupWithViewPager(viewpagerThu);
        viewpagerThu.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayoutThu));
        tablayoutThu.setTabsFromPagerAdapter(adapter);
        return view;
    }
}
