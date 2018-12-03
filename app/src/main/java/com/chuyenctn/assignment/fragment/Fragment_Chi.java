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
import com.chuyenctn.assignment.adapter.Pager_Chi_Adapter;

public class Fragment_Chi extends Fragment {
    private Pager_Chi_Adapter adapter;
    private TabLayout tablayoutChi;
    private ViewPager viewpagerChi;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chi,container,false);

        tablayoutChi = (TabLayout) view.findViewById(R.id.tablayout_chi);
        viewpagerChi = (ViewPager) view.findViewById(R.id.viewpager_chi);
        adapter=new Pager_Chi_Adapter(getChildFragmentManager());
        viewpagerChi.setAdapter(adapter);

        tablayoutChi.setupWithViewPager(viewpagerChi);
        viewpagerChi.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayoutChi));
        tablayoutChi.setTabsFromPagerAdapter(adapter);
        return view;
    }
}
