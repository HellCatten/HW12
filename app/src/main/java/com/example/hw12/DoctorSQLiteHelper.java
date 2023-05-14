package com.example.hw12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DoctorSQLiteHelper extends SQLiteOpenHelper {



    public DoctorSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
        public void onCreate(SQLiteDatabase bd) {
            bd.execSQL("create table " + "TABLE_DOCTORS" + " ("
                    + "_id integer primary key autoincrement,"
                    + "first_name text,"
                    + "last_name text,"
                    + "speciality text,"
                    + "is_attistated integer" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }


}
