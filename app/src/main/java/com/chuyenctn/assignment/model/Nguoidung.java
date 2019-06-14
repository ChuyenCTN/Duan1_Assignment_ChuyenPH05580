package com.chuyenctn.assignment.model;

public class Nguoidung {
    String hoten,taikhoan,matkhau;

    public Nguoidung(String hoten, String taikhoan, String matkhau) {
        this.hoten = hoten;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    public Nguoidung() {
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
