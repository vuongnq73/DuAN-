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
    private String maKH, hoTen, soDt, email, diaChi;
    private boolean trangThai;


    public Model_KhachHang() {
    }

    public Model_KhachHang(String maKH, String hoTen, String soDt, String email, String diaChi, boolean trangThai) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDt = soDt;
        this.email = email;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   
    public Object[] toDataRow(){
      
        return new Object[]{this.maKH, this.hoTen, this.soDt, this.email, this.diaChi, this.trangThai ? "Còn hoạt động":"Không hoạt động"};
    }
}
