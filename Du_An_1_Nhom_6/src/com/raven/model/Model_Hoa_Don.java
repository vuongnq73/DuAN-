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
private   String nguoiTao;  
private  Date NgayTao;
private BigDecimal PhiShip;
private String MaVoucher;
private BigDecimal tongTien;
private String HTThanhToan;
private String TenKH;
private String SDT;
private String DiaChi;
private boolean trangThai;
  @Override
public String toString() {
    return this.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán";
}

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public BigDecimal getPhiShip() {
        return PhiShip;
    }

    public void setPhiShip(BigDecimal PhiShip) {
        this.PhiShip = PhiShip;
    }

    public String getMaVoucher() {
        return MaVoucher;
    }

    public void setMaVoucher(String MaVoucher) {
        this.MaVoucher = MaVoucher;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getHinhThucThanhToan() {
        return HTThanhToan;
    }

    public void setHinhThucThanhToan(String MaNV) {
        this.HTThanhToan = MaNV;
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

    public boolean isTrangThai() {
        
        return trangThai;
       
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Model_Hoa_Don(String MaHD, String nguoiTao, Date NgayTao, BigDecimal PhiShip, String MaVoucher, BigDecimal tongTien, String MaNV, String TenKH, String SDT, String DiaChi, boolean trangThai) {
        this.MaHD = MaHD;
        this.nguoiTao = nguoiTao;
        this.NgayTao = NgayTao;
        this.PhiShip = PhiShip;
        this.MaVoucher = MaVoucher;
        this.tongTien = tongTien;
        this.HTThanhToan = MaNV;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.trangThai = trangThai;
    }

    public Model_Hoa_Don() {
    }

}
