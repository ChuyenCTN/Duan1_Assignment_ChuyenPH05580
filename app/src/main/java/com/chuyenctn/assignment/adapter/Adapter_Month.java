package com.chuyenctn.assignment.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chuyenctn.assignment.R;

import java.util.ArrayList;

public class Adapter_Month extends ArrayAdapter<String> {
    public ArrayList mData;
    public Resources mResources;
    private LayoutInflater mInflater;

    public Adapter_Month(Context context, int textViewResourceId, ArrayList objects, Resources resourceLocal) {
        super(context, textViewResourceId, objects);
        mData = objects;
        mResources = resourceLocal;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getAdapter_Month(position,convertView,parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getAdapter_Month(position,convertView,parent);
    }

    public View getAdapter_Month(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_spinner, parent, false);
        TextView tv_item = view.findViewById(R.id.tv_item_tenloaichi_spinner);
        tv_item.setText(mData.get(position).toString());
        return view;
    }
}
