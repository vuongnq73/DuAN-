/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

/**
 *
 * @author admin
 */
public class DoanhThu_Service {
     private String ngay_dang_ky;
     private long tien;

    public String getNgay_dang_ky() {
        return ngay_dang_ky;
    }

    public void setNgay_dang_ky(String ngay_dang_ky) {
        this.ngay_dang_ky = ngay_dang_ky;
    }

    public long getTien() {
        return tien;
    }

    public void setTien(long tien) {
        this.tien = tien;
    }

    public DoanhThu_Service(String ngay_dang_ky, long tien) {
        this.ngay_dang_ky = ngay_dang_ky;
        this.tien = tien;
    }

    public DoanhThu_Service() {
    }
}
