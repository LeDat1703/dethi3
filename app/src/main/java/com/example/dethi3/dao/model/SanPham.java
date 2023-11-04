package com.example.dethi3.dao.model;

public class SanPham {
    int id;
    String tenSP;
    int giaSP;
    int soLuong;
    String image;

    public SanPham(int id, String tenSP, int giaSP, int soLuong, String image) {
        this.id = id;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
        this.image = image;
    }

    public SanPham(String tenSP, int giaSP, int soLuong, String image) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
