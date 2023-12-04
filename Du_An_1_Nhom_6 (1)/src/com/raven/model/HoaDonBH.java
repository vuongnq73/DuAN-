/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.math.BigDecimal;

/**
 *
 * @author MSI
 */
public class HoaDonBH {
    private String maHD;
    private int idHoaDon;
    private int loaiKH;
    private BigDecimal tongTien;
    private String ghiChu;
    private String updateBy;
    private int idNhanVien;
    private int idKhachHang;
    private int idHTTT;

    public HoaDonBH() {
    }

    public HoaDonBH(String maHD,int loaiKH, BigDecimal tongTien, String ghiChu, String updateBy, int idNhanVien, int idHTTT) {
        this.maHD = maHD;
        this.idHoaDon = idHoaDon;
        this.loaiKH = loaiKH;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
        this.updateBy = updateBy;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idHTTT = idHTTT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

   
    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getLoaiKH() {
        return loaiKH;
    }

    public void setLoaiKH(int loaiKH) {
        this.loaiKH = loaiKH;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdHTTT() {
        return idHTTT;
    }

    public void setIdHTTT(int idHTTT) {
        this.idHTTT = idHTTT;
    }
    
            }
