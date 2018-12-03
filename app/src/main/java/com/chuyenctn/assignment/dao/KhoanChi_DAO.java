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

import static com.chuyenctn.assignment.common.Contants.KHOANCHI_GHICHU;
import static com.chuyenctn.assignment.common.Contants.KHOANCHI_MA;
import static com.chuyenctn.assignment.common.Contants.KHOANCHI_NGAY;
import static com.chuyenctn.assignment.common.Contants.KHOANCHI_SOTIEN;
import static com.chuyenctn.assignment.common.Contants.KHOANCHI_TABLE;
import static com.chuyenctn.assignment.common.Contants.KHOANCHI_TEN_KHOANCHI;
import static com.chuyenctn.assignment.common.Contants.KHOANCHI_TEN_LOAICHI;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_TABLE;

public class KhoanChi_DAO {
    private SQLiteDatabase sqLiteDatabase;
    private SqliteHelper sqliteHelper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public KhoanChi_DAO(SqliteHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }

    public List<Khoanchi> getAllKhoanChi() throws ParseException {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        List<Khoanchi> khoanchis = new ArrayList<>();
        String GET_ALL_KHOANCHI = "SELECT * FROM " + KHOANCHI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_KHOANCHI, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Khoanchi khoanchi = new Khoanchi();
                khoanchi.setMaKhoanChi(cursor.getString(cursor.getColumnIndex(KHOANCHI_MA)));
                khoanchi.setTenKhoanChi(cursor.getString(cursor.getColumnIndex(KHOANCHI_TEN_KHOANCHI)));
                khoanchi.setTenLoaiChi(cursor.getString(cursor.getColumnIndex(KHOANCHI_TEN_LOAICHI)));
                khoanchi.setGhichu(cursor.getString(cursor.getColumnIndex(KHOANCHI_GHICHU)));
                khoanchi.setSotien(String.valueOf(cursor.getDouble(cursor.getColumnIndex(KHOANCHI_SOTIEN))));
                khoanchi.setNgay(sdf.format(sdf.parse(cursor.getString(cursor.getColumnIndex(KHOANCHI_NGAY)))));
                khoanchis.add(khoanchi);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return khoanchis;
    }

    public long insertKhoanChi(Khoanchi khoanchi) throws ParseException {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KHOANCHI_MA, khoanchi.getMaKhoanChi());
        values.put(KHOANCHI_TEN_KHOANCHI, khoanchi.getTenKhoanChi());
        values.put(KHOANCHI_TEN_LOAICHI, khoanchi.getTenLoaiChi());
        values.put(KHOANCHI_GHICHU, khoanchi.getGhichu());
        values.put(KHOANCHI_NGAY, sdf.format(sdf.parse(khoanchi.getNgay())));
        values.put(KHOANCHI_SOTIEN, Double.parseDouble(khoanchi.getSotien()));
        long result = sqLiteDatabase.insert(KHOANCHI_TABLE, null, values);
        sqLiteDatabase.close();
        return result;
    }

    public long updateKhoanChi(String makhoanchi, String tenkhoanchi, String tenloaichi, double sotien, Date ngay, String ghichu) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KHOANCHI_MA, makhoanchi);
        values.put(KHOANCHI_TEN_KHOANCHI, tenkhoanchi);
        values.put(KHOANCHI_TEN_LOAICHI, tenloaichi);
        values.put(KHOANCHI_GHICHU, ghichu);
        values.put(KHOANCHI_NGAY, sdf.format(ngay));
        values.put(KHOANCHI_SOTIEN, sotien);
        long result = sqLiteDatabase.update(KHOANCHI_TABLE, values, KHOANCHI_MA + "=?", new String[]{makhoanchi});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteKhoanChi(String makhoanchi) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(KHOANCHI_TABLE, KHOANCHI_MA + "=?", new String[]{makhoanchi});
        sqLiteDatabase.close();
        return result;
    }


    public String getsotienKhoanChi() {
        String sotien = "";
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        String SQL = "SELECT SUM(sotien) from KhoanChi";
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

    public Khoanchi getKhoanChimax() throws ParseException {
        Khoanchi khoanchi = null;
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        String SQL = "SELECT * from KhoanChi ORDER BY sotien DESC LIMIT 1 ";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        Log.d("aaa", (cursor.getCount()) + "");
        cursor.moveToFirst();
        if (cursor != null & cursor.getCount() > 0) {
            while (cursor.isAfterLast() == false) {
                Log.d("aaaaaaaaa", cursor.getColumnCount() + "");
                khoanchi = new Khoanchi();
                khoanchi.setTenKhoanChi(cursor.getString(cursor.getColumnIndex(KHOANCHI_TEN_KHOANCHI)));
                khoanchi.setMaKhoanChi(cursor.getString(cursor.getColumnIndex(KHOANCHI_MA)));
                khoanchi.setTenLoaiChi(cursor.getString(cursor.getColumnIndex(KHOANCHI_TEN_LOAICHI)));
                khoanchi.setSotien(String.valueOf(cursor.getString(cursor.getColumnIndex(KHOANCHI_SOTIEN))) + "");
                Log.d("max", cursor.getString(cursor.getColumnIndex(KHOANCHI_SOTIEN)));
                khoanchi.setNgay(sdf.format(sdf.parse(cursor.getString(cursor.getColumnIndex(KHOANCHI_NGAY)))));
                khoanchi.setGhichu(cursor.getString(cursor.getColumnIndex(KHOANCHI_GHICHU)));
                break;
            }
        }
        cursor.close();
        return khoanchi;
    }

}
