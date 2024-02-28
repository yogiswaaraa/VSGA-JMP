package com.example.aplikasi_kpu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplikasi_kpu.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAddData, btnInformasi, btnLihatData, btnCekLokasi, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddData = findViewById(R.id.btn_entry);
        btnInformasi = findViewById(R.id.btn_information);
        btnLihatData = findViewById(R.id.btn_lihat_data);
        btnCekLokasi = findViewById(R.id.btn_location);
        btnLogout = findViewById(R.id.btn_logout);

        btnAddData.setOnClickListener(this);
        btnInformasi.setOnClickListener(this);
        btnLihatData.setOnClickListener(this);
        btnCekLokasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_entry){
            Intent moveIntent = new Intent(MainActivity.this, EntryFormActivity.class);
            startActivity(moveIntent);
        }
        else if(v.getId() == R.id.btn_lihat_data){
            Intent moveIntent = new Intent(MainActivity.this, ListDataActivity.class);
            startActivity(moveIntent);
        }
        else if(v.getId() == R.id.btn_location){
            Intent moveIntent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(moveIntent);
        }
    }
}