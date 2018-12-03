package com.chuyenctn.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.chuyenctn.assignment.model.Khoanchi;
import com.chuyenctn.assignment.model.Khoanthu;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.chuyenctn.assignment.common.Contants.KHOANTHU_GHICHU;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_MA;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_NGAY;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_SOTIEN;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_TABLE;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_TEN_KHOANTHU;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_TEN_LOAITHU;

public class KhoanThu_DAO {
    private SQLiteDatabase sqLiteDatabase;
    private SqliteHelper sqliteHelper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public KhoanThu_DAO(SqliteHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }

    public List<Khoanthu> getAllKhoanThu() throws ParseException {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        List<Khoanthu> khoanthus = new ArrayList<>();
        String GET_ALL_KHOANTHU = "SELECT * FROM " + KHOANTHU_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_KHOANTHU, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Khoanthu khoanthu = new Khoanthu();
                khoanthu.setMaKhoanThu(cursor.getString(cursor.getColumnIndex(KHOANTHU_MA)));
                khoanthu.setTenKhoanThu(cursor.getString(cursor.getColumnIndex(KHOANTHU_TEN_KHOANTHU)));
                khoanthu.setTenLoaiThu(cursor.getString(cursor.getColumnIndex(KHOANTHU_TEN_LOAITHU)));
                khoanthu.setGhichu(cursor.getString(cursor.getColumnIndex(KHOANTHU_GHICHU)));
                khoanthu.setSotien(cursor.getDouble(cursor.getColumnIndex(KHOANTHU_SOTIEN)));
                khoanthu.setNgay(sdf.format(sdf.parse(cursor.getString(cursor.getColumnIndex(KHOANTHU_NGAY)))));
                khoanthus.add(khoanthu);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return khoanthus;
    }

    public long insertKhoanThu(Khoanthu khoanthu) throws ParseException {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KHOANTHU_MA, khoanthu.getMaKhoanThu());
        values.put(KHOANTHU_TEN_KHOANTHU, khoanthu.getTenKhoanThu());
        values.put(KHOANTHU_TEN_LOAITHU, khoanthu.getTenLoaiThu());
        values.put(KHOANTHU_GHICHU, khoanthu.getGhichu());
        values.put(KHOANTHU_NGAY, sdf.format(sdf.parse(khoanthu.getNgay())));
        values.put(KHOANTHU_SOTIEN, khoanthu.getSotien());
        long result = sqLiteDatabase.insert(KHOANTHU_TABLE, null, values);
        sqLiteDatabase.close();
        return result;
    }

    public long updateKhoanThu(String makhoanthu, String tenkhoanthu, String tenloaithu, double sotien, Date ngay, String ghichu) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KHOANTHU_MA, makhoanthu);
        values.put(KHOANTHU_TEN_KHOANTHU, tenkhoanthu);
        values.put(KHOANTHU_TEN_LOAITHU, tenloaithu);
        values.put(KHOANTHU_GHICHU, ghichu);
        values.put(KHOANTHU_NGAY, sdf.format(ngay));
        values.put(KHOANTHU_SOTIEN, sotien);
        long result = sqLiteDatabase.update(KHOANTHU_TABLE, values, KHOANTHU_MA + "=?", new String[]{makhoanthu});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteKhoanThu(String makhoanchi) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(KHOANTHU_TABLE, KHOANTHU_MA + "=?", new String[]{makhoanchi});
        sqLiteDatabase.close();
        return result;
    }


    public String getSoTienKhoanThu() {
        String sotien = "";
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        String SQL = "SELECT SUM(sotien) from KhoanThu";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                sotien = cursor.getString(0) + "";
                Log.d("tien", sotien);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return sotien;

    }

    public Khoanthu getKhoanThumax() throws ParseException {
        Khoanthu khoanthu = null;
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        String SQL = "SELECT * from KhoanThu ORDER BY sotien DESC LIMIT 1 ";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        Log.d("aaa", (cursor.getCount()) + "");
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Log.d("aaaaaaaaa", cursor.getColumnCount() + "");
            khoanthu = new Khoanthu();
            khoanthu.setMaKhoanThu(cursor.getString(cursor.getColumnIndex(KHOANTHU_MA)));
            khoanthu.setTenKhoanThu(cursor.getString(cursor.getColumnIndex(KHOANTHU_TEN_KHOANTHU)));
            khoanthu.setTenLoaiThu(cursor.getString(cursor.getColumnIndex(KHOANTHU_TEN_LOAITHU)));
            khoanthu.setSotien(Double.parseDouble(cursor.getString(cursor.getColumnIndex(KHOANTHU_SOTIEN))));
            khoanthu.setNgay(sdf.format(sdf.parse(cursor.getString(cursor.getColumnIndex(KHOANTHU_NGAY)))));
            khoanthu.setGhichu(cursor.getString(cursor.getColumnIndex(KHOANTHU_GHICHU)));
            break;
        }
        cursor.close();
        return khoanthu;
    }
}
