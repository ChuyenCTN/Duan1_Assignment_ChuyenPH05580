package com.chuyenctn.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.chuyenctn.assignment.model.Khoanchi;
import com.chuyenctn.assignment.model.Loaichi;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

import static com.chuyenctn.assignment.common.Contants.LOAICHI_GHICHU;
import static com.chuyenctn.assignment.common.Contants.LOAICHI_MA;
import static com.chuyenctn.assignment.common.Contants.LOAICHI_TABLE;
import static com.chuyenctn.assignment.common.Contants.LOAICHI_TEN;
import static com.chuyenctn.assignment.common.Contants.LOAITHU_TABLE;

public class LoaiChi_DAO {
    private SQLiteDatabase sqLiteDatabase;
    private SqliteHelper sqliteHelper;

    public LoaiChi_DAO(SqliteHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }


    public List<Loaichi> getAllLoaiChi() {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        List<Loaichi> loaichis = new ArrayList<>();
        String GET_ALL_LOAICHI = "SELECT * FROM " + LOAICHI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_LOAICHI, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maloaichi = cursor.getString(cursor.getColumnIndex(LOAICHI_MA));
                String tenloaichi = cursor.getString(cursor.getColumnIndex(LOAICHI_TEN));
                String ghichu = cursor.getString(cursor.getColumnIndex(LOAICHI_GHICHU));
                Loaichi loaichi = new Loaichi();
                loaichi.setMaLoaiChi(maloaichi);
                loaichi.setTenLoaiChi(tenloaichi);
                loaichi.setGhichu(ghichu);
                loaichis.add(loaichi);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return loaichis;
    }

    public long insertLoaiChi(Loaichi loaichi) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOAICHI_MA, loaichi.getMaLoaiChi());
        values.put(LOAICHI_TEN, loaichi.getTenLoaiChi());
        values.put(LOAICHI_GHICHU, loaichi.getGhichu());
        long result = sqLiteDatabase.insert(LOAICHI_TABLE, null, values);
        sqLiteDatabase.close();
        return result;
    }

    public long updateLoaiChi(Loaichi loaichi) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOAICHI_MA, loaichi.getMaLoaiChi());
        values.put(LOAICHI_TEN, loaichi.getTenLoaiChi());
        values.put(LOAICHI_GHICHU, loaichi.getGhichu());
        long result = sqLiteDatabase.update(LOAICHI_TABLE, values, LOAICHI_MA + "=?", new String[]{loaichi.getMaLoaiChi()});
        return result;
    }

    public long deleteLoaiChi(String maloaichi) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(LOAICHI_TABLE, LOAICHI_MA + "=?", new String[]{maloaichi});
        sqLiteDatabase.close();
        return result;
    }

    public List<Loaichi> getTenLoaiChi() {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        List<Loaichi> loaichiList = new ArrayList<>();
        String GET_TEN_LOAICHI = "SELECT " + LOAICHI_TEN + " FROM " + LOAICHI_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_TEN_LOAICHI, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Loaichi loaichi = new Loaichi();
                String tenloaichi = cursor.getString(cursor.getColumnIndex(LOAICHI_TEN));
                loaichi.setTenLoaiChi(tenloaichi);
                loaichiList.add(loaichi);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return loaichiList;
    }

    public String[] gettenloaichi() {
        String[] loaichi = null;
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(LOAICHI_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            loaichi = new String[cursor.getCount()];
            loaichi[cursor.getPosition()] = cursor.getString(cursor.getColumnIndex(LOAICHI_TEN));
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return loaichi;
    }

}
