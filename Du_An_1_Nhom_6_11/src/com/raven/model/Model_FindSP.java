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
public class Model_FindSP {
  private String MaHD;
    private String MaSp;
    private String NameSP;
    private BigDecimal donGia;
    private int SoLuong;
    private BigDecimal tongTien;
    private Date ngayTT;

    public Model_FindSP() {
    }

    public Model_FindSP(String MaHD, String MaSp, String NameSP, BigDecimal donGia, int SoLuong, BigDecimal tongTien, Date ngayTT) {
        this.MaHD = MaHD;
        this.MaSp = MaSp;
        this.NameSP = NameSP;
        this.donGia = donGia;
        this.SoLuong = SoLuong;
        this.tongTien = tongTien;
        this.ngayTT = ngayTT;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaSp() {
        return MaSp;
    }

    public void setMaSp(String MaSp) {
        this.MaSp = MaSp;
    }

    public String getNameSP() {
        return NameSP;
    }

    public void setNameSP(String NameSP) {
        this.NameSP = NameSP;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

   
}
