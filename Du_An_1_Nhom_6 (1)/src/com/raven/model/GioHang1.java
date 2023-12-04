/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.math.BigDecimal;

/**
 *
 * @author ACER
 */
public class GioHang1 {
    private int idCTSP;
    private String maSP;
    private String tenSP;
    private Integer soLuong;
    private BigDecimal dongia;
    private BigDecimal thanhTien;
    private String maHoaDon;

    public GioHang1() {
    }

    public GioHang1(int idCTSP, String tenSP,BigDecimal dongia, Integer soLuong, BigDecimal thanhTien,String maHoaDon) {
        this.idCTSP=idCTSP;
    this.maSP=maSP;
        this.tenSP = tenSP;
                this.dongia=dongia;

        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.maHoaDon=maHoaDon;
    }

    public int getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(int idCTSP) {
        this.idCTSP = idCTSP;
    }

    public BigDecimal getDongia() {
        return dongia;
    }

    public void setDongia(BigDecimal dongia) {
        this.dongia = dongia;
    }

   
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    
    
}
