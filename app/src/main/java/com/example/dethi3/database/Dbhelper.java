package com.example.dethi3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class Dbhelper {
    public Dbhelper(@Nullable Context context) {
        super(context, "QLSP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qSP = "create table SANPHAM (id integer primary key autoincrement, tenSP text, " +
                "giaSP integer, soLuong integer, image text)";
        db.execSQL(qSP);

        String dataSP = "insert into SANPHAM values " +
                "(1,'Bánh quy bơ LU Pháp',45000,10,'baseline_person_24')," +
                "(2,'Snack mực lăn muối ớt',8000,52,'baseline_cake_24')," +
                "(3,'Bánh gạo One One',30000,11,'baseline_airport_shuttle_24')," +
                "(4,'Trà sữa đường đen',25000,3,'fpt_polytechnic')," +
                "(5,'Nước uống sữa trái cây',15000,10,'baseline_security_24')";
        db.execSQL(dataSP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists SANPHAM");
            db.execSQL("drop table if exists NGUOIDUNG");
            onCreate(db);
        }
    }
}
