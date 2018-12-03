package com.chuyenctn.assignment.model;

import java.util.Date;

public class Khoanthu {
    String maKhoanThu, tenKhoanThu,tenLoaiThu,ngay, ghichu;
    double sotien;

    public Khoanthu(String maKhoanThu, String tenKhoanThu, String tenLoaiThu, String ngay, String ghichu, double sotien) {
        this.maKhoanThu = maKhoanThu;
        this.tenKhoanThu = tenKhoanThu;
        this.tenLoaiThu = tenLoaiThu;
        this.ngay = ngay;
        this.ghichu = ghichu;
        this.sotien = sotien;
    }

    public Khoanthu() {
    }

    public String getMaKhoanThu() {
        return maKhoanThu;
    }

    public void setMaKhoanThu(String maKhoanThu) {
        this.maKhoanThu = maKhoanThu;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public String getTenLoaiThu() {
        return tenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        this.tenLoaiThu = tenLoaiThu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }
}
