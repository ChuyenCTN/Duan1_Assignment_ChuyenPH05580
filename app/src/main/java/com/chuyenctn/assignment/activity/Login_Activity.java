package com.chuyenctn.assignment.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chuyenctn.assignment.R;
import com.chuyenctn.assignment.dao.Nguoidung_DAO;
import com.chuyenctn.assignment.sqlite.SqliteHelper;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
    private String Name = "";
    private String Email = "";
    private String idProfilePicture = "";
    private String type_Login = "";
    private LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        imgLogo = (ImageView) findViewById(R.id.img_logo);
        tilTaikhoan = (TextInputLayout) findViewById(R.id.til_taikhoan);
        edTaikhoan = (EditText) findViewById(R.id.ed_taikhoan);
        tilMatkhau = (TextInputLayout) findViewById(R.id.til_matkhau);
        edMatkhau = (EditText) findViewById(R.id.ed_matkhau);
        chkNhomatkhau = (CheckBox) findViewById(R.id.chk_nhomatkhau);
        loginButton = findViewById(R.id.login_button);
        setSupportActionBar(toolBar);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        Intent intent = getIntent();
        edTaikhoan.setText(intent.getStringExtra("taikhoan"));
        edMatkhau.setText(intent.getStringExtra("matkhau"));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                boolean iswWifi = networkInfo.isConnected();
                networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                boolean is3G = networkInfo.isConnected();
                if (iswWifi || is3G) {
                    setLogin();
                } else {
                    showToast();
                }
            }
        });


// Get keyhash
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    getPackageName(),
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
//                messageDigest.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
//            }
//        }
//        catch (PackageManager.NameNotFoundException e) {
//
//        }
//        catch (NoSuchAlgorithmException e) {
//
//        }


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
            case R.id.optionmenu_thoat:
                thoat(getCurrentFocus());
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
        strPass = edMatkhau.getText().toString();
        strUser = edTaikhoan.getText().toString();
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
            nguoidungDao = new Nguoidung_DAO(new SqliteHelper(getApplicationContext()));
            if (nguoidungDao.checkDangnhap(strUser, strPass)) {
                rememberUser(strUser, strPass, chkNhomatkhau.isChecked());
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                type_Login = "login1";
                Intent intent = new Intent(getApplicationContext(), Thongke_Activity.class);
                intent.putExtra("username", strUser);
                intent.putExtra("type_login", type_Login);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            }
            type_Login = "login1";
            Intent intent = new Intent(getApplicationContext(), Thongke_Activity.class);
            intent.putExtra("username", strUser);
            intent.putExtra("type_login", type_Login);
            startActivity(intent);

        } else {
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setLogin() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                result();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });

    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON", response.getJSONObject().toString());
                try {
                    Email = object.getString("email");
                    Name = object.getString("name");
                    idProfilePicture = Profile.getCurrentProfile().getId();
                    type_Login = "login2";
                    Intent intent = new Intent(getApplicationContext(), Thongke_Activity.class);
                    intent.putExtra("name_fb", Name);
                    intent.putExtra("email_fb", Email);
                    intent.putExtra("img_fb", idProfilePicture);
                    intent.putExtra("type_login", type_Login);
                    startActivity(intent);
                    Toast.makeText(Login_Activity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.error_toast, (ViewGroup) findViewById(R.id.layout_Toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 500);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
