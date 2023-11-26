/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author lenovo
 */
public class table1_KhachHang {

    private String maKH;
    private String tenKhachHang;
    private String sdt;
    private String tenSp;
    private Integer soLuong;
    private Double donGia;
    private Double tongtTien;
    private Boolean trangThai;

    public table1_KhachHang() {
    }

    public table1_KhachHang(String maKH, String tenKhachHang, String sdt, String tenSp, Integer soLuong, Double donGia, Double tongtTien, Boolean trangThai) {
        this.maKH = maKH;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.tenSp = tenSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongtTien = tongtTien;
        this.trangThai = trangThai;
    }

    
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getTongtTien() {
        return tongtTien;
    }

    public void setTongtTien(Double tongtTien) {
        this.tongtTien = tongtTien;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    
}
