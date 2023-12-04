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
public class HoaDonChiTiet {
    private int idSP;
    private int idHD;
    private int soluong;
    private BigDecimal thanhtien;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int idSP, int idHD, int soluong, BigDecimal thanhtien) {
        this.idSP = idSP;
        this.idHD = idHD;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(BigDecimal thanhtien) {
        this.thanhtien = thanhtien;
    }
    
}
