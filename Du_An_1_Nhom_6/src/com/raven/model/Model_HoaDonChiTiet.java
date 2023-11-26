/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.math.BigDecimal;

/**
 *
 * @author admin
 */
public class Model_HoaDonChiTiet {
    private String MaCTSP;
    private String TenSP;
    private String NhanHieu;
    private String Mau;
    private String Size;
    private int SoLuong;
    private BigDecimal donGia;
    private BigDecimal tongTien;

    public String getMaCTSP() {
        return MaCTSP;
    }

    public void setMaCTSP(String MaCTSP) {
        this.MaCTSP = MaCTSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getNhanHieu() {
        return NhanHieu;
    }

    public void setNhanHieu(String NhanHieu) {
        this.NhanHieu = NhanHieu;
    }

    public String getMau() {
        return Mau;
    }

    public void setMau(String Mau) {
        this.Mau = Mau;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Model_HoaDonChiTiet(String MaCTSP, String TenSP, String NhanHieu, String Mau, String Size, int SoLuong, BigDecimal donGia, BigDecimal tongTien) {
        this.MaCTSP = MaCTSP;
        this.TenSP = TenSP;
        this.NhanHieu = NhanHieu;
        this.Mau = Mau;
        this.Size = Size;
        this.SoLuong = SoLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public Model_HoaDonChiTiet() {
    }

}
