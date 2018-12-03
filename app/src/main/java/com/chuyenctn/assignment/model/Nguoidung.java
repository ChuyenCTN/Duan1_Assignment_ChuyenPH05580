package com.chuyenctn.assignment.model;

public class Nguoidung {
    String username, password, hoten;

    public Nguoidung(String username, String password, String hoten) {
        this.username = username;
        this.password = password;
        this.hoten = hoten;
    }

    public Nguoidung() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
