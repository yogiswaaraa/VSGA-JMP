package com.example.aplikasi_kpu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasi_kpu.R;
import com.example.aplikasi_kpu.db.DbHelper;
import com.example.aplikasi_kpu.model.Voter;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private DbHelper dbHelper;
    private TextView edtNIK, edtName, edtAddress, edtSex;

    private Button btnUpdate;
    private Voter voter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DbHelper(this);

        edtNIK = findViewById(R.id.edt_update_nik);
        edtName = findViewById(R.id.edt_update_name);
        edtAddress = findViewById(R.id.edt_update_address);
        edtSex = findViewById(R.id.edt_update_sex);
        btnUpdate = findViewById(R.id.btn_update);

        Intent intent = getIntent();
        voter = (Voter) intent.getSerializableExtra("voter");

        assert  voter != null;
        edtNIK.setText(voter.getNik());
        edtName.setText(voter.getName());
        edtAddress.setText(voter.getAddress());
        edtSex.setText(voter.getSex());

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (edtNIK.getText().toString().isEmpty()) {
            Toast.makeText(this, "NIK tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (edtAddress.getText().toString().isEmpty()) {
            Toast.makeText(this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (edtSex.getText().toString().isEmpty()) {
            Toast.makeText(this, "Jenis kelamin tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.updateUser(edtNIK.getText().toString(), edtName.getText().toString(), edtAddress.getText().toString(), edtSex.getText().toString(),voter.getId());
            edtNIK.setText("");
            edtName.setText("");
            edtAddress.setText("");
            edtSex.setText("");
            Intent updateIntent = new Intent(this, ListDataActivity.class);
            startActivity(updateIntent);
            Toast.makeText(this, "Data Berhasil Terupdate", Toast.LENGTH_SHORT).show();
        }
    }
}