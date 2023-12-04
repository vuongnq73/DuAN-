/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author ACER
 */
public class modelGioHang {
    private Integer idCTSP;
    private String maSanPham;
    private String tenSanPham;
    private Integer soLuong;
    private Double donGia;
    private Double tongTien;

    public modelGioHang() {
    }

    public modelGioHang(Integer idCTSP, String maSanPham, String tenSanPham, Integer soLuong, Double donGia, Double tongTien) {
        this.idCTSP = idCTSP;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public Integer getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(Integer idCTSP) {
        this.idCTSP = idCTSP;
    }

    

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
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

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
