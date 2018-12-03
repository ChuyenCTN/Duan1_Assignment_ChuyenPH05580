package com.chuyenctn.assignment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.Nguoidung_DAO;

public class Login_Activity extends AppCompatActivity {
    private Toolbar toolBar;
    private ImageView imgLogo;
    private TextInputLayout tilTaikhoan;
    private EditText edTaikhoan;
    private TextInputLayout tilMatkhau;
    private EditText edMatkhau;
    private CheckBox chkNhomatkhau;
    private String strUser, strPass;
    private Boolean check = false;

    private Nguoidung_DAO nguoidungDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        imgLogo = (ImageView) findViewById(R.id.img_logo);
        tilTaikhoan = (TextInputLayout) findViewById(R.id.til_taikhoan);
        edTaikhoan = (EditText) findViewById(R.id.ed_taikhoan);
        tilMatkhau = (TextInputLayout) findViewById(R.id.til_matkhau);
        edMatkhau = (EditText) findViewById(R.id.ed_matkhau);
        chkNhomatkhau = (CheckBox) findViewById(R.id.chk_nhomatkhau);
        setSupportActionBar(toolBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionmenu_taikhoanmoi:
                startActivity(new Intent(getApplicationContext(), Dangki_Activity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void thoat(View view) {
        Snackbar.make(view, "Bạn chắc chắn muốn thoát chứ?", 3000).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        }).show();
    }

    public void dangnhap(View view) {
        startActivity(new Intent(getApplicationContext(),Thongke_Activity.class));
        strUser = edTaikhoan.getText().toString();
        strPass = edMatkhau.getText().toString();
        if (strUser.isEmpty()) {
            tilTaikhoan.setError("Tài khoản trống");
            edTaikhoan.setHint("");
            tilTaikhoan.setHint("");
            check = false;
        } else {
            check = true;
            edTaikhoan.setHint("Tài khoản");
            tilTaikhoan.setError("");
        }
        if (strPass.isEmpty()) {
            check = false;
            tilMatkhau.setError("Mật khẩu trống");
            edMatkhau.setHint("");
            tilMatkhau.setHint("");
        } else {
            check = true;
            tilMatkhau.setError("");
            edMatkhau.setHint("Mật khẩu");
        }
        if (check) {
//            if (nguoidungDao.checkLogin(strUser, strPass) > 0) {
//                rememberUser(strUser,strPass,chkNhomatkhau.isChecked());
//                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

//            }
//            else {
//                Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
//            }
        }
        else {}
    }

    public void rememberUser(String username, String password, boolean status) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }
}
