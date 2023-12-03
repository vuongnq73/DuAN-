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
public class Model_TraHang {
    private String maSPCT;
    private String TenSPCT;
    private String NhanHieu;
    private String KichThuoc;
    private BigDecimal donGia;
    private Date NgayTT;

    public Model_TraHang( String maSPCT, String TenSPCT, String NhanHieu, String KichThuoc, BigDecimal donGia, Date NgayTT) {
        this.maSPCT = maSPCT;
        this.TenSPCT = TenSPCT;
        this.NhanHieu = NhanHieu;
        this.KichThuoc = KichThuoc;
        this.donGia = donGia;
        this.NgayTT = NgayTT;
    }

  

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSPCT() {
        return TenSPCT;
    }

    public void setTenSPCT(String TenSPCT) {
        this.TenSPCT = TenSPCT;
    }

    public String getNhanHieu() {
        return NhanHieu;
    }

    public void setNhanHieu(String NhanHieu) {
        this.NhanHieu = NhanHieu;
    }

    public String getKichThuoc() {
        return KichThuoc;
    }

    public void setKichThuoc(String KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Date getNgayTT() {
        return NgayTT;
    }

    public void setNgayTT(Date NgayTT) {
        this.NgayTT = NgayTT;
    }

    
    public Model_TraHang() {
    }

}
