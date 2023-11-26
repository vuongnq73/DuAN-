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
    private String TenNhanVien;
    private boolean trangThai;

    public Date getNgaytao() {
        return Ngaytao;
    }

    public void setNgaytao(Date Ngaytao) {
        this.Ngaytao = Ngaytao;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Model_LichSuHoaDonChiTiet(Date Ngaytao, String TenNhanVien, boolean trangThai) {
        this.Ngaytao = Ngaytao;
        this.TenNhanVien = TenNhanVien;
        this.trangThai = trangThai;
    }

    public Model_LichSuHoaDonChiTiet() {
    }

}
