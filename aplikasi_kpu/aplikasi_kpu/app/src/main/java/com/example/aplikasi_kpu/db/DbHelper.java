package com.example.aplikasi_kpu.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.aplikasi_kpu.model.Voter;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static String DATABASENAME = "db_kpu";
    public static final int DATABASE_Version = 1;
    public static final String TABLES_STD = "kpu";
    public static final String KEY_ID = "id";
    public static final String KEY_NIK = "nik";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_SEX = "sex";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, DATABASE_Version);
    }

    private static final String CREATE_TABLE_VOTER = "CREATE TABLE " + TABLES_STD + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NIK + " TEXT, "
            + KEY_NAME + " TEXT, "
            + KEY_ADDRESS + " TEXT, "
            + KEY_SEX + " TEXT );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VOTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLES_STD + "'");
    }

    public void addUser(String nik, String name, String address, String sex) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIK, nik);
        values.put(KEY_NAME, name);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_SEX, sex);

        db.insert(TABLES_STD, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<Voter> getAllUser() {
        ArrayList<Voter> userModel = new ArrayList<Voter>();
        String selectQuery = "SELECT * FROM " + TABLES_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Voter vote = new Voter();
                vote.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                vote.setNik(c.getString(c.getColumnIndex(KEY_NIK)));
                vote.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                vote.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                vote.setSex(c.getString(c.getColumnIndex(KEY_SEX)));
                userModel.add(vote);
            } while (c.moveToNext());
        }
        return userModel;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLES_STD, KEY_ID + "= ?", new String[]{
                String.valueOf(id)
        });
    }

    public int updateUser(String nik, String name, String address, String sex, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIK, nik);
        values.put(KEY_NAME, name);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_SEX, sex);

        return db.update(TABLES_STD, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
