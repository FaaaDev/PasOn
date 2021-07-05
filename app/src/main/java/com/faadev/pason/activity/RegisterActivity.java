package com.faadev.pason.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.faadev.pason.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView gologin;
    private EditText username, user, password, repassword;
    private Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View main = findViewById(R.id.container);
            int flags = main.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                this.getWindow().setNavigationBarColor(Color.argb(255, 185, 15, 232));
            }
            main.setSystemUiVisibility(flags);
            this.getWindow().setStatusBarColor(Color.WHITE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        _init();
        _prep();

    }

    private void _init(){
        gologin = findViewById(R.id.gologin);
        username = findViewById(R.id.username);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.re_password);
        daftar = findViewById(R.id.daftar);
    }

    private void _prep(){
        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _validasi();
            }
        });
    }

    private void _validasi(){
        if (TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(user.getText()) ||
                TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(repassword.getText())){
            if (TextUtils.isEmpty(username.getText())){
                username.setError("Nama harus diisi");
                username.requestFocus();
            }

            if (TextUtils.isEmpty(user.getText())){
                user.setError("Nomor HP harus diisi");
                user.requestFocus();
            }

            if (TextUtils.isEmpty(password.getText())){
                password.setError("Password harus diisi");
                password.requestFocus();
            }

            if (TextUtils.isEmpty(repassword.getText())){
                repassword.setError("Ketik ulang password");
                repassword.requestFocus();
            }
        } else if (!password.getText().toString().equals(repassword.getText().toString())){
            repassword.setText("");
            repassword.setError("Password tidak sama");
            repassword.requestFocus();
        } else {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}