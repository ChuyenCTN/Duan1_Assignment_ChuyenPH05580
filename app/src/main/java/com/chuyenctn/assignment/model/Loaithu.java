package com.chuyenctn.assignment.model;

public class Loaithu {
    String maLoaiThu,tenLoaiThu,ghichu;

    public Loaithu(String maLoaiThu, String tenLoaiThu, String ghichu) {
        this.maLoaiThu = maLoaiThu;
        this.tenLoaiThu = tenLoaiThu;
        this.ghichu = ghichu;
    }

    public Loaithu() {
    }

    public String getMaLoaiThu() {
        return maLoaiThu;
    }

    public void setMaLoaiThu(String maLoaiThu) {
        this.maLoaiThu = maLoaiThu;
    }

    public String getTenLoaiThu() {
        return tenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        this.tenLoaiThu = tenLoaiThu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
