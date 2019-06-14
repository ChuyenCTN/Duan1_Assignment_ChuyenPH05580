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
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.Nguoidung_DAO;
import com.chuyenctn.assignment.fragment.Fragment_Chi;
import com.chuyenctn.assignment.fragment.Fragment_Thongke;
import com.chuyenctn.assignment.fragment.Fragment_Thu;
import com.chuyenctn.assignment.model.Nguoidung;
import com.chuyenctn.assignment.sqlite.SqliteHelper;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

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
    private List<Nguoidung> nguoidungList = new ArrayList<>();
    private String username = "";
    private ProfilePictureView friendProfilePicture;
    private String Name_Fb = "";
    private String Email_Fb = "";
    private String Img_Fb = "";
    private String type_Login = "";

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
        View headerView = navigationView.getHeaderView(0);
        tvUserPerson = (TextView) headerView.findViewById(R.id.tv_user_person);
        tvNamePerson = (TextView) headerView.findViewById(R.id.tv_name_person);
        friendProfilePicture = headerView.findViewById(R.id.friendProfilePicture);
        Intent intent = getIntent();
        type_Login = intent.getStringExtra("type_login");
        username = intent.getStringExtra("username");
        Name_Fb = intent.getStringExtra("name_fb");
        Email_Fb = intent.getStringExtra("email_fb");
        Img_Fb = intent.getStringExtra("img_fb");
        if (type_Login.equalsIgnoreCase("login1")) {
            nguoidungDao = new Nguoidung_DAO(new SqliteHelper(Thongke_Activity.this));
            tvUserPerson.setText(username);
            tvNamePerson.setText("Xin chào: " + nguoidungDao.getHoten(username).getHoten());
        } else {
            tvNamePerson.setText("Xin chào: " + Name_Fb);
            tvUserPerson.setText(Email_Fb);
            friendProfilePicture.setProfileId(Img_Fb);
        }
        navigationView.setNavigationItemSelectedListener(this);
        Display_Fragment(R.id.nav_thongke);
        LoginManager.getInstance().logOut();
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
                startActivity(new Intent(getApplicationContext(), Gioithieu_Activity.class));
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
                LoginManager.getInstance().logOut();
                startActivity(new Intent(getApplicationContext(), Login_Activity.class));
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
