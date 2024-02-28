package com.example.aplikasi_kpu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasi_kpu.R;
import com.example.aplikasi_kpu.db.DbHelper;

import org.w3c.dom.Text;

public class EntryFormActivity extends AppCompatActivity implements View.OnClickListener {
    DbHelper dbHelper;
    EditText edtNIK, edtName, edtAddress, edtSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new DbHelper(this);

        edtNIK = findViewById(R.id.edt_nik);
        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_address);
        edtSex = findViewById(R.id.edt_sex);

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
            dbHelper.addUser(edtNIK.getText().toString(), edtName.getText().toString(), edtAddress.getText().toString(), edtSex.getText().toString());
            edtNIK.setText("");
            edtName.setText("");
            edtAddress.setText("");
            edtSex.setText("");
            Toast.makeText(this, "Data Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
        }
    }
}