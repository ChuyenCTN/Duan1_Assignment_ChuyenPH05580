package com.chuyenctn.assignment.model;

public class Loaichi {
    String maLoaiChi, tenLoaiChi, ghichu;

    public Loaichi(String maLoaiChi, String tenLoaiChi, String ghichu) {
        this.maLoaiChi = maLoaiChi;
        this.tenLoaiChi = tenLoaiChi;
        this.ghichu = ghichu;
    }

    public Loaichi() {
    }

    public String getMaLoaiChi() {
        return maLoaiChi;
    }

    public void setMaLoaiChi(String maLoaiChi) {
        this.maLoaiChi = maLoaiChi;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
