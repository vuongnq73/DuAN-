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
public class model_TexFilL {
    private  String MaHD;
    private  Date NgayTao;
    private String HTThanhToan;
    private String MaSP;
    private BigDecimal tongTien;
    private  BigDecimal PhiShip;
    private String tenKH;
    private String DiaChi;
    private String TenNV;
    private Date NgayTT;
    private String GhiChu;

    public model_TexFilL() {
    }

    public model_TexFilL(String MaHD, Date NgayTao, String HTThanhToan, String MaSP, BigDecimal tongTien, BigDecimal PhiShip, String tenKH, String DiaChi, String TenNV, Date NgayTT, String GhiChu) {
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.HTThanhToan = HTThanhToan;
        this.MaSP = MaSP;
        this.tongTien = tongTien;
        this.PhiShip = PhiShip;
        this.tenKH = tenKH;
        this.DiaChi = DiaChi;
        this.TenNV = TenNV;
        this.NgayTT = NgayTT;
        this.GhiChu = GhiChu;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getHTThanhToan() {
        return HTThanhToan;
    }

    public void setHTThanhToan(String HTThanhToan) {
        this.HTThanhToan = HTThanhToan;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getPhiShip() {
        return PhiShip;
    }

    public void setPhiShip(BigDecimal PhiShip) {
        this.PhiShip = PhiShip;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public Date getNgayTT() {
        return NgayTT;
    }

    public void setNgayTT(Date NgayTT) {
        this.NgayTT = NgayTT;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

   
}
