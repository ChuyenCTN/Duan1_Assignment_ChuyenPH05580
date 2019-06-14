package com.chuyenctn.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chuyenctn.assignment.model.Nguoidung;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

import static com.chuyenctn.assignment.common.Contants.KHOANTHU_TABLE;
import static com.chuyenctn.assignment.common.Contants.LOAICHI_TABLE;
import static com.chuyenctn.assignment.common.Contants.NGUOIDUNG_HOTEN;
import static com.chuyenctn.assignment.common.Contants.NGUOIDUNG_MATKHAU;
import static com.chuyenctn.assignment.common.Contants.NGUOIDUNG_TABLE;
import static com.chuyenctn.assignment.common.Contants.NGUOIDUNG_TAIKHOAN;

public class Nguoidung_DAO {
    private SQLiteDatabase sqLiteDatabase;
    private SqliteHelper sqliteHelper;

    public Nguoidung_DAO(SqliteHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }

    public int insertNguoiDung(Nguoidung nguoidung) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NGUOIDUNG_HOTEN, nguoidung.getHoten());
        values.put(NGUOIDUNG_TAIKHOAN, nguoidung.getTaikhoan());
        values.put(NGUOIDUNG_MATKHAU, nguoidung.getMatkhau());
        int result = (int) sqLiteDatabase.insert(NGUOIDUNG_TABLE, null, values);
        sqLiteDatabase.close();
        return result;
    }


    public Nguoidung getHoten(String user) {
        Nguoidung nguoidung = null;
        String name = "";
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        String selection = "taikhoan=?";
        String[] selectionArgs = {user};
        Cursor cursor = sqLiteDatabase.query(NGUOIDUNG_TABLE, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            nguoidung = new Nguoidung();
            nguoidung.setHoten(cursor.getString(cursor.getColumnIndex(NGUOIDUNG_HOTEN)));
            break;
        }
        return nguoidung;
    }

    public boolean checkDangnhap(String user, String pass) {
        boolean check = false;
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        List<Nguoidung> nguoidungList = new ArrayList<>();
        String GET_ALL_NGUOIDUNG = "SELECT * FROM " + NGUOIDUNG_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_NGUOIDUNG, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String hoten = cursor.getString(cursor.getColumnIndex(NGUOIDUNG_HOTEN));
                String taikhoan = cursor.getString(cursor.getColumnIndex(NGUOIDUNG_TAIKHOAN));
                String matkhau = cursor.getString(cursor.getColumnIndex(NGUOIDUNG_MATKHAU));
                Nguoidung nguoidung = new Nguoidung();
                nguoidung.setHoten(hoten);
                nguoidung.setTaikhoan(taikhoan);
                nguoidung.setMatkhau(matkhau);
                nguoidungList.add(nguoidung);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        for (int i = 0; i < nguoidungList.size(); i++) {
            if ((user.equalsIgnoreCase(nguoidungList.get(i).getTaikhoan())) & (pass.equalsIgnoreCase(nguoidungList.get(i).getMatkhau()))) {
                check = true;
            }
        }
        return check;
    }
}
