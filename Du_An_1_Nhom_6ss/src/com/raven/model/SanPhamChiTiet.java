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
public class SanPhamChiTiet {
     private String tensanpham;
    private String maSPCT;
    private String tenSPCT;
    private int soLuong;
    private String noiXuatXu;
    private String tenMauSac;
    private String tenNhanHieu;
    private String tenKichThuoc;
    private String tenCoAo;
    private String tenDangAo;
    private String tenChatLieu;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private boolean trangthai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(String tensanpham, String maSPCT, String tenSPCT, int soLuong, String noiXuatXu, String tenMauSac, String tenNhanHieu, String tenKichThuoc, String tenCoAo, String tenDangAo, String tenChatLieu, BigDecimal giaNhap, BigDecimal giaBan, boolean trangthai) {
        this.tensanpham = tensanpham;
        this.maSPCT = maSPCT;
        this.tenSPCT = tenSPCT;
        this.soLuong = soLuong;
        this.noiXuatXu = noiXuatXu;
        this.tenMauSac = tenMauSac;
        this.tenNhanHieu = tenNhanHieu;
        this.tenKichThuoc = tenKichThuoc;
        this.tenCoAo = tenCoAo;
        this.tenDangAo = tenDangAo;
        this.tenChatLieu = tenChatLieu;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trangthai = trangthai;
    }

    

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSPCT() {
        return tenSPCT;
    }

    public void setTenSPCT(String tenSPCT) {
        this.tenSPCT = tenSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNoiXuatXu() {
        return noiXuatXu;
    }

    public void setNoiXuatXu(String noiXuatXu) {
        this.noiXuatXu = noiXuatXu;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenNhanHieu() {
        return tenNhanHieu;
    }

    public void setTenNhanHieu(String tenNhanHieu) {
        this.tenNhanHieu = tenNhanHieu;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public String getTenCoAo() {
        return tenCoAo;
    }

    public void setTenCoAo(String tenCoAo) {
        this.tenCoAo = tenCoAo;
    }

    public String getTenDangAo() {
        return tenDangAo;
    }

    public void setTenDangAo(String tenDangAo) {
        this.tenDangAo = tenDangAo;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    
}
