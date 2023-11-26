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
    private String SDT;
    private String Email;
    private String DiaChi;
    private boolean TrangThai;
    private Date CreateAt;
    private String createBy;
    private Date UpDateAt;
    private String UpdateBy;
    private  boolean Deleted;

    public Model_NhanVien() {
    }

    public Model_NhanVien(String MaNhanVien, String MatKhau, boolean ChuVu, String HoTen, boolean GioiTinh, String SDT, String Email, String DiaChi, boolean TrangThai, Date CreateAt, String createBy, Date UpDateAt, String UpdateBy, boolean Deleted) {
        this.MaNhanVien = MaNhanVien;
        this.MatKhau = MatKhau;
        this.ChuVu = ChuVu;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
        this.CreateAt = CreateAt;
        this.createBy = createBy;
        this.UpDateAt = UpDateAt;
        this.UpdateBy = UpdateBy;
        this.Deleted = Deleted;
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

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date CreateAt) {
        this.CreateAt = CreateAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpDateAt() {
        return UpDateAt;
    }

    public void setUpDateAt(Date UpDateAt) {
        this.UpDateAt = UpDateAt;
    }

    public String getUpdateBy() {
        return UpdateBy;
    }

    public void setUpdateBy(String UpdateBy) {
        this.UpdateBy = UpdateBy;
    }

    public boolean isDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean Deleted) {
        this.Deleted = Deleted;
    }
   

   
}