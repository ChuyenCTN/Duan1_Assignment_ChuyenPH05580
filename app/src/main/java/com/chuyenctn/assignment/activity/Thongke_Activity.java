package com.chuyenctn.assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.Nguoidung_DAO;
import com.chuyenctn.assignment.fragment.Fragment_Chi;
import com.chuyenctn.assignment.fragment.Fragment_Thongke;
import com.chuyenctn.assignment.fragment.Fragment_Thu;
import com.chuyenctn.assignment.model.Nguoidung;

import java.util.ArrayList;
import java.util.List;

public class Thongke_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView tv_title_toolBar;
    private View view;
    private Toolbar toolbar;
    private ImageView imgAvatar;
    private TextView tvUserPerson;
    private TextView tvNamePerson;
    private Nguoidung_DAO nguoidungDao;
    private List<Nguoidung> nguoidungList=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title_toolBar = findViewById(R.id.tv_title_toolBar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        imgAvatar = (ImageView) headerView.findViewById(R.id.img_Avatar);
        tvUserPerson = (TextView) headerView.findViewById(R.id.tv_user_person);
        tvNamePerson = (TextView) headerView.findViewById(R.id.tv_name_person);


        navigationView.setNavigationItemSelectedListener(this);
        Display_Fragment(R.id.nav_thongke);
    }

    private void Display_Fragment(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_thongke:
                fragment = new Fragment_Thongke();
                break;
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_layout, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_gioithieu:
               startActivity(new Intent(getApplicationContext(),Gioithieu_Activity.class));
                break;
            case R.id.nav_thongke:
                fragment = new Fragment_Thongke();
                tv_title_toolBar.setText("Thống kê");
                break;
            case R.id.nav_khoanthu:
                fragment = new Fragment_Thu();
                tv_title_toolBar.setText("Khoản thu");
                break;
            case R.id.nav_khoanchi:
                fragment = new Fragment_Chi();
                tv_title_toolBar.setText("Khoản chi");
                break;
            case R.id.nav_thoat:

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startActivity(startMain);
                        finish();


break;
        }


        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_layout, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
