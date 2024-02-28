package com.example.aplikasi_kpu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasi_kpu.R;

public class LoginAtivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername;
    private EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ativity);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(username.equals("admin") && password.equals("admin")){
            Intent moveLogin = new Intent(LoginAtivity.this, MainActivity.class);
            startActivity(moveLogin);
        }else {
            Toast.makeText(LoginAtivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
        }
    }
}