/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Model_NhanVien {

    private String MaNhanVien;
    private String MatKhau;
    private boolean ChuVu;
    private String HoTen;
    private boolean GioiTinh;
    private String CCCD;
    private String SDT;
    private String Email;
    private String DiaChi;
    private boolean TrangThai;
//    private Date CreateAt;
//    private String createBy;
//    private Date UpDateAt;
//    private String UpdateBy;
//    private  boolean Deleted;

    public Model_NhanVien() {
    }

    public Model_NhanVien(String MaNhanVien, String MatKhau, boolean ChuVu, String HoTen, boolean GioiTinh, String CCCD, String SDT, String Email, String DiaChi, boolean TrangThai) {
        this.MaNhanVien = MaNhanVien;
        this.MatKhau = MatKhau;
        this.ChuVu = ChuVu;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.CCCD = CCCD;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public boolean isChuVu() {
        return ChuVu;
    }

    public void setChuVu(boolean ChuVu) {
        this.ChuVu = ChuVu;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    public Object[] toDataRow(){
        
       return new Object[]{this.MaNhanVien, this.MatKhau, this.ChuVu ? "Nhân viên":"Quản lí", this.HoTen, this.GioiTinh ? "Nam":"Nữ",this.CCCD ,this.SDT, this.Email, this.DiaChi, this.TrangThai ? "Đang làm việc":"Nghỉ làm"};
       
    }
   
   
}