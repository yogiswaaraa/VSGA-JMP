package com.example.aplikasi_kpu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.aplikasi_kpu.R;
import com.example.aplikasi_kpu.db.DbHelper;
import com.example.aplikasi_kpu.model.Voter;

public class DetailVoterActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private TextView tvNIK, tvName, tvAddress, tvSex;

    private Voter voter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_voter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper =  new DbHelper(this);
        tvNIK = findViewById(R.id.tv_detail_nik);
        tvName = findViewById(R.id.tv_detail_name);
        tvAddress = findViewById(R.id.tv_detail_address);
        tvSex = findViewById(R.id.tv_detail_sex);

        Intent intent = getIntent();
        voter = (Voter) intent.getSerializableExtra("voter");

        assert  voter != null;
        tvNIK.setText(voter.getNik());
        tvName.setText(voter.getName());
        tvAddress.setText(voter.getAddress());
        tvSex.setText(voter.getSex());
    }
}