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
public class Model_lichSuHoaDon {
       private String MaHD;
    private Date Ngaytao;
    private String Nguoitao;
    private String TenKH;
    private BigDecimal PhiShip;
    private String HinhThuc;
    private BigDecimal tongtien;
    private String MaVoucher;
    private Date NgaySua;
    private String NguoiSua;

    public Model_lichSuHoaDon() {
    }

    public Model_lichSuHoaDon(String MaHD, Date Ngaytao, String Nguoitao, String TenKH, BigDecimal PhiShip, String HinhThuc, BigDecimal tongtien, String MaVoucher, Date NgaySua, String NguoiSua) {
        this.MaHD = MaHD;
        this.Ngaytao = Ngaytao;
        this.Nguoitao = Nguoitao;
        this.TenKH = TenKH;
        this.PhiShip = PhiShip;
        this.HinhThuc = HinhThuc;
        this.tongtien = tongtien;
        this.MaVoucher = MaVoucher;
        this.NgaySua = NgaySua;
        this.NguoiSua = NguoiSua;
    }

    public Model_lichSuHoaDon(String createBy, Date createAt, String ghiChu, String maHoaDon, BigDecimal tongTien, BigDecimal phiShip, String tenKhachHang, String diaChi, String tenNhanVien, Date updateAt, String tenHinhThuc, String trangThai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgaytao() {
        return Ngaytao;
    }

    public void setNgaytao(Date Ngaytao) {
        this.Ngaytao = Ngaytao;
    }

    public String getNguoitao() {
        return Nguoitao;
    }

    public void setNguoitao(String Nguoitao) {
        this.Nguoitao = Nguoitao;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public BigDecimal getPhiShip() {
        return PhiShip;
    }

    public void setPhiShip(BigDecimal PhiShip) {
        this.PhiShip = PhiShip;
    }

    public String getHinhThuc() {
        return HinhThuc;
    }

    public void setHinhThuc(String HinhThuc) {
        this.HinhThuc = HinhThuc;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }

    public String getMaVoucher() {
        return MaVoucher;
    }

    public void setMaVoucher(String MaVoucher) {
        this.MaVoucher = MaVoucher;
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

  
}
