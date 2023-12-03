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
public class Model_ThongKe {
    private String MaSP;
    private String TenSP;
    private String Hang;
    private String MauSac;
    private String Size;
    private int SoLuong;
    private BigDecimal donGia;

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String Hang) {
        this.Hang = Hang;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
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

    public Model_ThongKe() {
    }

    public Model_ThongKe(String MaSP, String TenSP, String Hang, String MauSac, String Size, int SoLuong, BigDecimal donGia) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Hang = Hang;
        this.MauSac = MauSac;
        this.Size = Size;
        this.SoLuong = SoLuong;
        this.donGia = donGia;
    }
}
