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
public class Model_LichSuHoaDonChiTiet {

    private Date Ngaytao;
    private String HinhThucTT;
    private boolean trangThai;
    private String MaHD;
    private BigDecimal tongtien;
    private BigDecimal PhiShip;
    private String TenKH;
    private String DiaChi;
    private String NhanVien;
    private Date NgaySua;
    private String NguoiSua;
    private String GhiChu;

    public Date getNgaytao() {
        return Ngaytao;
    }

    public void setNgaytao(Date Ngaytao) {
        this.Ngaytao = Ngaytao;
    }

    public String getHinhThucTT() {
        return HinhThucTT;
    }

    public void setHinhThucTT(String HinhThucTT) {
        this.HinhThucTT = HinhThucTT;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }

    public BigDecimal getPhiShip() {
        return PhiShip;
    }

    public void setPhiShip(BigDecimal PhiShip) {
        this.PhiShip = PhiShip;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(String NhanVien) {
        this.NhanVien = NhanVien;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    public String getNguoiSua() {
        return NguoiSua;
    }

    public void setNguoiSua(String NguoiSua) {
        this.NguoiSua = NguoiSua;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Model_LichSuHoaDonChiTiet(Date Ngaytao, String HinhThucTT, boolean trangThai, String MaHD, BigDecimal tongtien, BigDecimal PhiShip, String TenKH, String DiaChi, String NhanVien, Date NgaySua, String NguoiSua, String GhiChu) {
        this.Ngaytao = Ngaytao;
        this.HinhThucTT = HinhThucTT;
        this.trangThai = trangThai;
        this.MaHD = MaHD;
        this.tongtien = tongtien;
        this.PhiShip = PhiShip;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.NhanVien = NhanVien;
        this.NgaySua = NgaySua;
        this.NguoiSua = NguoiSua;
        this.GhiChu = GhiChu;
    }

    public Model_LichSuHoaDonChiTiet() {
    }
}
