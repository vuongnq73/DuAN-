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
public class Model_KhachHang {
    private String maKH, hoTen, soDt, email, diaChi, CCCD;
    private boolean trangThai;
//    private Date CreateAt;
//    private String createBy;
//    private Date UpDateAt;
//    private String UpdateBy;
//    private  boolean Deleted;

    public Model_KhachHang() {
    }

    public Model_KhachHang(String maKH, String hoTen, String soDt, String email, String diaChi, String CCCD, boolean trangThai) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDt = soDt;
        this.email = email;
        this.diaChi = diaChi;
        this.CCCD = CCCD;
        this.trangThai = trangThai;
    }

    

//    public Model_KhachHang(String maKH, String hoTen, String soDt, String email, String diaChi, String CCCD, boolean trangThai, Date CreateAt, String createBy, Date UpDateAt, String UpdateBy, boolean Deleted) {
//        this.maKH = maKH;
//        this.hoTen = hoTen;
//        this.soDt = soDt;
//        this.email = email;
//        this.diaChi = diaChi;
//        this.CCCD = CCCD;
//        this.trangThai = trangThai;
//        this.CreateAt = CreateAt;
//        this.createBy = createBy;
//        this.UpDateAt = UpDateAt;
//        this.UpdateBy = UpdateBy;
//        this.Deleted = Deleted;
//    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDt() {
        return soDt;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

//    public Date getCreateAt() {
//        return CreateAt;
//    }
//
//    public void setCreateAt(Date CreateAt) {
//        this.CreateAt = CreateAt;
//    }
//
//    public String getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }
//
//    public Date getUpDateAt() {
//        return UpDateAt;
//    }
//
//    public void setUpDateAt(Date UpDateAt) {
//        this.UpDateAt = UpDateAt;
//    }
//
//    public String getUpdateBy() {
//        return UpdateBy;
//    }
//
//    public void setUpdateBy(String UpdateBy) {
//        this.UpdateBy = UpdateBy;
//    }
//
//    public boolean isDeleted() {
//        return Deleted;
//    }
//
//    public void setDeleted(boolean Deleted) {
//        this.Deleted = Deleted;
//    }
    public Object[] toDataRow(){
      
        return new Object[]{this.maKH, this.hoTen, this.soDt, this.email, this.diaChi, this.CCCD, this.trangThai ? "Còn hoạt động":"Không hoạt động"};
    }
}
