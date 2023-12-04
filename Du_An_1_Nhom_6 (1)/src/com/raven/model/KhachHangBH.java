/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author MSI
 */
public class KhachHangBH {
    private int idKH;
    private String maKH;
    private String hoTen;
    private String soDT;
    private String email;
    private String diaChi;
    private String updateBy;
    private String createBy;
    public KhachHangBH() {
    }

    
    public KhachHangBH(String maKH, String hoTen, String soDT, String email, String diaChi, String updateBy, String createBy) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
        this.updateBy = updateBy;
        this.createBy = createBy;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
}
