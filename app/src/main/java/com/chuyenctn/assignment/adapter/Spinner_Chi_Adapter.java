package com.chuyenctn.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chuyenctn.assignment.R;

import java.util.ArrayList;
import java.util.List;

public class Spinner_Chi_Adapter extends BaseAdapter {

    Context context;
    int layout;
    List arrayList;

    public Spinner_Chi_Adapter(Context context, int layout, List arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        TextView tvItemTenloaichiSpinner;
        tvItemTenloaichiSpinner = (TextView) view.findViewById(R.id.tv_item_tenloaichi_spinner);
        return view;
    }
}
