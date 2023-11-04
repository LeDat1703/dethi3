package com.example.dethi3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dethi3.database.Dbhelper;
import com.example.dethi3.dao.model.SanPham;

import java.util.ArrayList;

public class SanPhamDAO {
    Dbhelper dbHelper;
    ArrayList<SanPham> list;

    public SanPhamDAO(Context context) {
        dbHelper = new Dbhelper(context);
    }

    public ArrayList<SanPham> getListSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();

        SQLiteDatabase database = dbHelper.getRead
        database.beginTransaction();

        try {
            Cursor cursor = database.rawQuery("select * from SANPHAM", new String[]{}, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new SanPham(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4)));
                } while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean deleteSanPham(int id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", id + "");

        long check = database.delete("SANPHAM", "id = ?", new String[]{id + ""});

        return check != -1;
    }

    public boolean updateSanPham(SanPham sanPham) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        ContentValues values = new ContentValues();
        values.put("tenSP", sanPham.getTenSP());
        values.put("giaSP", sanPham.getGiaSP() + "");
        values.put("soLuong", sanPham.getSoLuong() + "");
        values.put("image",sanPham.getImage());
        database.setTransactionSuccessful();

        long check = database.update("SANPHAM", values, "id = ?", new String[]{sanPham.getId() + ""});
        database.endTransaction();
        return check != -1;
    }

//    public boolean addSanPham(SanPham sanPham) {
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
//        database.beginTransaction();
//
//        ContentValues values = new ContentValues();
//        values.put("tenSP", sanPham.getTenSP());
//        values.put("giaSP", sanPham.getGiaSP() + "");
//        database.setTransactionSuccessful();
//
//        long check = database.insert("SANPHAM", null, values);
//        database.endTransaction();
//        return check != -1;
//    }

    public boolean checkUser(String tenDangNhap, String matKhau){
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from NGUOIDUNG where tenDangNhap = ? and matKhau = ?",new String[]{tenDangNhap, matKhau});
        if(cursor.getCount() == 1){
            return true;
        }else{
            return false;
        }
    }

}
