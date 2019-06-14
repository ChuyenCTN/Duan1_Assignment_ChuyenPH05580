package com.chuyenctn.assignment.common;

public class Contants {

    public final static boolean isDEBUG = true;

    // Table loai chi
    public final static String LOAICHI_MA = "maloaichi";
    public final static String LOAICHI_TEN = "tenloaichi";
    public final static String LOAICHI_GHICHU = "ghichu";
    public final static String LOAICHI_TABLE = "LoaiChi";

    public final static String CREATE_LOAICHI_TABLE = "CREATE TABLE " + LOAICHI_TABLE + " (" +
            "" + LOAICHI_MA + " TEXT PRIMARY KEY ," +
            "" + LOAICHI_TEN + " TEXT ," +
            "" + LOAICHI_GHICHU + " TEXT " +
            ")";

    // Table loai thu
    public final static String LOAITHU_MA = "maloaithu";
    public final static String LOAITHU_TEN = "tenloaithu";
    public final static String LOAITHU_GHICHU = "ghichu";
    public final static String LOAITHU_TABLE = "LoaiThu";

    public final static String CREATE_LOAITHU_TABLE = "CREATE TABLE " + LOAITHU_TABLE + " (" +
            "" + LOAITHU_MA + " TEXT PRIMARY KEY ," +
            "" + LOAITHU_TEN + " TEXT ," +
            "" + LOAITHU_GHICHU + " TEXT " +
            ")";

    // Table khoan chi
    public final static String KHOANCHI_MA = "makhoanchi";
    public final static String KHOANCHI_TEN_KHOANCHI = "tenkhoanchi";
    public final static String KHOANCHI_TEN_LOAICHI = "tenloaichi";
    public final static String KHOANCHI_SOTIEN = "sotien";
    public final static String KHOANCHI_NGAY = "ngay";
    public final static String KHOANCHI_GHICHU = "ghichu";
    public final static String KHOANCHI_TABLE = "KhoanChi";

    public final static String CREATE_KHOANCHI_TABLE = "CREATE TABLE " + KHOANCHI_TABLE + " (" +
            "" + KHOANCHI_MA + " TEXT PRIMARY KEY ," +
            "" + KHOANCHI_TEN_KHOANCHI + " TEXT ," +
            "" + KHOANCHI_TEN_LOAICHI + " TEXT ," +
            "" + KHOANCHI_SOTIEN + " DOUBLE ," +
            "" + KHOANCHI_NGAY + " DATE ," +
            "" + KHOANCHI_GHICHU + " TEXT " +
            ")";

//    Table khoan thu

    public final static String KHOANTHU_MA = "makhoanthu";
    public final static String KHOANTHU_TEN_KHOANTHU = "tenkhoanthu";
    public final static String KHOANTHU_TEN_LOAITHU = "tenloaithu";
    public final static String KHOANTHU_SOTIEN = "sotien";
    public final static String KHOANTHU_NGAY = "ngay";
    public final static String KHOANTHU_GHICHU = "ghichu";
    public final static String KHOANTHU_TABLE = "KhoanThu";

    public final static String CREATE_KHOANTHU_TABLE = "CREATE TABLE " + KHOANTHU_TABLE + " (" +
            "" + KHOANTHU_MA + " TEXT PRIMARY KEY ," +
            "" + KHOANTHU_TEN_KHOANTHU + " TEXT ," +
            "" + KHOANTHU_TEN_LOAITHU + " TEXT ," +
            "" + KHOANTHU_SOTIEN + " DOUBLE ," +
            "" + KHOANTHU_NGAY + " DATE ," +
            "" + KHOANTHU_GHICHU + " TEXT " +
            ")";
    //Table nguoidung
    public final static String NGUOIDUNG_TABLE = "NguoiDung";
    public final static String NGUOIDUNG_HOTEN = "hoten";
    public final static String NGUOIDUNG_TAIKHOAN = "taikhoan";
    public final static String NGUOIDUNG_MATKHAU = "matkhau";

    public final static String CREATE_NGUOIDUNG_TABLE = "CREATE TABLE " + NGUOIDUNG_TABLE + " (" +
            "" + NGUOIDUNG_TAIKHOAN + " TEXT PRIMARY KEY ," +
            "" + NGUOIDUNG_HOTEN + " TEXT ," +
            "" + NGUOIDUNG_MATKHAU + " TEXT " +
            ")";
}
