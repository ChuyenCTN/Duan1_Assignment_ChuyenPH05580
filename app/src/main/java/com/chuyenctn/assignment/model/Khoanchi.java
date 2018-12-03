package com.chuyenctn.assignment.model;

import java.util.Date;

public class Khoanchi {
    String maKhoanChi, tenKhoanChi,tenLoaiChi,ngay, ghichu, sotien;

    public Khoanchi(String maKhoanChi, String tenKhoanChi, String tenLoaiChi, String ngay, String ghichu, String sotien) {
        this.maKhoanChi = maKhoanChi;
        this.tenKhoanChi = tenKhoanChi;
        this.tenLoaiChi = tenLoaiChi;
        this.ngay = ngay;
        this.ghichu = ghichu;
        this.sotien = sotien;
    }

    public Khoanchi() {
    }

    public String getMaKhoanChi() {
        return maKhoanChi;
    }

    public void setMaKhoanChi(String maKhoanChi) {
        this.maKhoanChi = maKhoanChi;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
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

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }
}
