/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Model_Hoa_Don {
private String MaHD;
private   String MaNV; 
private String TenKH;
private String SDT;
private String DiaChi;
private BigDecimal tongTien;
private boolean LoaiHoaDon;

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isLoaiHoaDon() {
        return LoaiHoaDon;
    }

    public void setLoaiHoaDon(boolean LoaiHoaDon) {
        this.LoaiHoaDon = LoaiHoaDon;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getHTThanhToan() {
        return HTThanhToan;
    }

    public void setHTThanhToan(String HTThanhToan) {
        this.HTThanhToan = HTThanhToan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Model_Hoa_Don(String MaHD, String MaNV, String TenKH, String SDT, String DiaChi, BigDecimal tongTien, boolean LoaiHoaDon, Date NgayThanhToan, String HTThanhToan, boolean trangThai) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.tongTien = tongTien;
        this.LoaiHoaDon = LoaiHoaDon;
        this.NgayThanhToan = NgayThanhToan;
        this.HTThanhToan = HTThanhToan;
        this.trangThai = trangThai;
    }

    public Model_Hoa_Don() {
    }
private  Date NgayThanhToan;
private String HTThanhToan;
private boolean trangThai;
  @Override
public String toString() {
    return this.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán";
}


   

}
