package com.chuyenctn.assignment.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.chuyenctn.assignment.common.Contants.CREATE_KHOANCHI_TABLE;
import static com.chuyenctn.assignment.common.Contants.CREATE_KHOANTHU_TABLE;
import static com.chuyenctn.assignment.common.Contants.CREATE_LOAICHI_TABLE;
import static com.chuyenctn.assignment.common.Contants.CREATE_LOAITHU_TABLE;
import static com.chuyenctn.assignment.common.Contants.CREATE_NGUOIDUNG_TABLE;
import static com.chuyenctn.assignment.common.Contants.KHOANCHI_TABLE;
import static com.chuyenctn.assignment.common.Contants.KHOANTHU_TABLE;
import static com.chuyenctn.assignment.common.Contants.LOAICHI_TABLE;
import static com.chuyenctn.assignment.common.Contants.LOAITHU_TABLE;
import static com.chuyenctn.assignment.common.Contants.NGUOIDUNG_TABLE;
import static com.chuyenctn.assignment.common.Contants.isDEBUG;

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(Context context) {
        super(context, "DB.ThuChi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_LOAICHI_TABLE);
        if (isDEBUG) Log.e("LOAICHI_TABLE", CREATE_LOAICHI_TABLE);
        sqLiteDatabase.execSQL(CREATE_LOAITHU_TABLE);
        if (isDEBUG) Log.e("LOAITHU_TABLE", CREATE_LOAITHU_TABLE);
        sqLiteDatabase.execSQL(CREATE_KHOANCHI_TABLE);
        if (isDEBUG) Log.e("KHOANCHI_TABLE", CREATE_KHOANCHI_TABLE);
        sqLiteDatabase.execSQL(CREATE_KHOANTHU_TABLE);
        if (isDEBUG) Log.e("KHOANTHU_TABLE", CREATE_KHOANTHU_TABLE);
        sqLiteDatabase.execSQL(CREATE_NGUOIDUNG_TABLE);
        if (isDEBUG) Log.e(NGUOIDUNG_TABLE, CREATE_NGUOIDUNG_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LOAICHI_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LOAITHU_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + KHOANCHI_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + KHOANTHU_TABLE);
    }
}
