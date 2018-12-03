package com.chuyenctn.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.chuyenctn.assignment.model.Loaichi;
import com.chuyenctn.assignment.model.Loaithu;
import com.chuyenctn.assignment.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

import static com.chuyenctn.assignment.common.Contants.LOAITHU_GHICHU;
import static com.chuyenctn.assignment.common.Contants.LOAITHU_MA;
import static com.chuyenctn.assignment.common.Contants.LOAITHU_TABLE;
import static com.chuyenctn.assignment.common.Contants.LOAITHU_TEN;

public class LoaiThu_DAO {
    private SQLiteDatabase sqLiteDatabase;
    private SqliteHelper sqliteHelper;

    public LoaiThu_DAO(SqliteHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }

    public List<Loaithu> getAllLoaiThu() {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        List<Loaithu> loaithus = new ArrayList<>();
        String GET_ALL_LOAITHU = "SELECT * FROM " + LOAITHU_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_LOAITHU, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Loaithu loaithu = new Loaithu();
                loaithu.setMaLoaiThu(cursor.getString(cursor.getColumnIndex(LOAITHU_MA)));
                loaithu.setTenLoaiThu(cursor.getString(cursor.getColumnIndex(LOAITHU_TEN)));
                loaithu.setGhichu(cursor.getString(cursor.getColumnIndex(LOAITHU_GHICHU)));
                loaithus.add(loaithu);
                cursor.moveToNext();
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return loaithus;
    }

    public long insertLoaiThu(Loaithu loaithu) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOAITHU_MA, loaithu.getMaLoaiThu());
        values.put(LOAITHU_TEN, loaithu.getTenLoaiThu());
        values.put(LOAITHU_GHICHU, loaithu.getGhichu());
        long result = sqLiteDatabase.insert(LOAITHU_TABLE, null, values);
        sqLiteDatabase.close();
        return result;
    }

    public long updateLoaiThu(Loaithu loaithu) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOAITHU_MA, loaithu.getMaLoaiThu());
        values.put(LOAITHU_TEN, loaithu.getTenLoaiThu());
        values.put(LOAITHU_GHICHU, loaithu.getGhichu());
        long result = sqLiteDatabase.update(LOAITHU_TABLE, values, LOAITHU_MA + "=?", new String[]{loaithu.getMaLoaiThu()});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteLoaiThu(String maloaithu) {
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(LOAITHU_TABLE, LOAITHU_MA + "=?", new String[]{maloaithu});
        sqLiteDatabase.close();
        return result;
    }


    public List<Loaithu> getTenLoaiThu(){
        sqLiteDatabase = sqliteHelper.getWritableDatabase();
        List<Loaithu> loaithuList=new ArrayList<>();
        String GET_TEN_LOAITHU = "SELECT " + LOAITHU_TEN + " FROM " +LOAITHU_TABLE ;
        Cursor cursor=sqLiteDatabase.rawQuery(GET_TEN_LOAITHU,null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Loaithu loaithu=new Loaithu();
                String tenloaithu=cursor.getString(cursor.getColumnIndex(LOAITHU_TEN));
                loaithu.setTenLoaiThu(tenloaithu);
                loaithuList.add(loaithu);
                cursor.moveToNext();
            }
            cursor.close();
            sqliteHelper.close();
        }
        return loaithuList;
    }

    public String[] gettenloaithu() {
        String[] loaithu = null;
        sqLiteDatabase=sqliteHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(LOAITHU_TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            loaithu = new String[cursor.getCount()];
            loaithu[cursor.getPosition()] = cursor.getString(1);
            cursor.moveToNext();
        }
        cursor.close();
        return loaithu;
    }
}
