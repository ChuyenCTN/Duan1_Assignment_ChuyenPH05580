package com.chuyenctn.assignment.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.Nguoidung_DAO;
import com.chuyenctn.assignment.model.Nguoidung;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

public class Dangki_Activity extends AppCompatActivity {
    private Toolbar toolBarDangki;
    private ImageView imgLogoDk;
    private TextInputLayout tilTennguoidungDk;
    private EditText edTennguoidungDk;
    private TextInputLayout tilTaikhoanDk;
    private EditText edTaikhoanDk;
    private TextInputLayout tilMatkhauDk;
    private EditText edMatkhauDk;
    private TextInputLayout tilXnmatkhauDk;
    private EditText edXnmatkhauDk;
    private Nguoidung_DAO nguoidungDao;
    private String strUser, strPass, strHoten, strRepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        toolBarDangki = (Toolbar) findViewById(R.id.toolBar_Dangki);
        imgLogoDk = (ImageView) findViewById(R.id.img_logo_dk);
        tilTennguoidungDk = (TextInputLayout) findViewById(R.id.til_tennguoidung_dk);
        edTennguoidungDk = (EditText) findViewById(R.id.ed_tennguoidung_dk);
        tilTaikhoanDk = (TextInputLayout) findViewById(R.id.til_taikhoan_dk);
        edTaikhoanDk = (EditText) findViewById(R.id.ed_taikhoan_dk);
        tilMatkhauDk = (TextInputLayout) findViewById(R.id.til_matkhau_dk);
        edMatkhauDk = (EditText) findViewById(R.id.ed_matkhau_dk);
        tilXnmatkhauDk = (TextInputLayout) findViewById(R.id.til_xnmatkhau_dk);
        edXnmatkhauDk = (EditText) findViewById(R.id.ed_xnmatkhau_dk);
    }

    public void dangki(View view) {
        strUser = edTaikhoanDk.getText().toString();
        strPass = edMatkhauDk.getText().toString();
        strHoten = edTennguoidungDk.getText().toString();
        strRepass = edXnmatkhauDk.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty() || strRepass.isEmpty() || strHoten.isEmpty()) {
            Toast.makeText(this, "Mời bạn nhập đầy đủ dữ liệu", Toast.LENGTH_LONG).show();
        } else if (!strPass.equalsIgnoreCase(strRepass)) {
            Toast.makeText(this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        } else {
            Nguoidung user = new Nguoidung(strHoten, strUser, strPass);
            try {
                nguoidungDao = new Nguoidung_DAO(new SqliteHelper(getApplicationContext()));
                if (nguoidungDao.insertNguoiDung(user) > 0) {
                    Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                    intent.putExtra("taikhoan", strUser);
                    intent.putExtra("matkhau", strPass);
                    startActivity(intent);
                }
            } catch (Exception e) {
                Log.e("error: ", e.toString());
            }
        }
    }
    public void back_login(View view) {
        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
    }
}
