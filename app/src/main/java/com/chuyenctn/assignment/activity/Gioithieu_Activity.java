package com.chuyenctn.assignment.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.chuyenctn.assignment.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Gioithieu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioithieu);

    }

    public void back_thongke(View view) {
        startActivity(new Intent(getApplicationContext(),Thongke_Activity.class));
    }
}
