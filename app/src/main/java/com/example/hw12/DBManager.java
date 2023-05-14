package com.example.hw12;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager {

    private SQLiteOpenHelper sqLiteHelper;


    public DBManager(SQLiteOpenHelper sqLiteHelper) {
        this.sqLiteHelper = sqLiteHelper;
    }

    public boolean saveDoctorToDatabase(Doctor doctor) {
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean i = doctor.isAttictated();
        int a;
        if (i) {
            a = 1;
        } else {
            a = 0;
        }
        cv.put("first_name", doctor.getfName());
        cv.put("last_name", doctor.getlName());
        cv.put("speciality", doctor.getSpeciality());
        cv.put("is_attistated", a);
        long rowId = db.insert("TABLE_DOCTORS", null, cv);
        cv.clear();
        db.close();
        return rowId != -1;
    }

    public ArrayList<Doctor> loadAllDoctorsFromDatabase() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_DOCTORS",
                null,null,null,
                null,null,null);
        if (dbCursor.moveToFirst()) {
            do{
                String fName = dbCursor.getString(dbCursor.getColumnIndexOrThrow("first_name"));
                String lName = dbCursor.getString(dbCursor.getColumnIndexOrThrow("last_name"));
                String spec = dbCursor.getString(dbCursor.getColumnIndexOrThrow("speciality"));
                int attistat = dbCursor.getInt(dbCursor.getColumnIndexOrThrow("is_attistated"));
                boolean isAtt;
                if (attistat == 1) {
                    isAtt = true;
                } else {
                    isAtt = false;
                }
                doctors.add(new Doctor(fName,lName,spec,isAtt));
            } while (dbCursor.moveToNext());
        }
        dbCursor.close();
        db.close();
        return doctors;
    }
}
