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
       private Date NgayTaoHD;
       private String nguoiTao;
       private boolean TrangThai;

     
       
    public Date getNgayTaoHD() {
        return NgayTaoHD;
    }

    public void setNgayTaoHD(Date NgayTaoHD) {
        this.NgayTaoHD = NgayTaoHD;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Model_lichSuHoaDon(Date NgayTaoHD, String nguoiTao, boolean TrangThai) {
        this.NgayTaoHD = NgayTaoHD;
        this.nguoiTao = nguoiTao;
        this.TrangThai = TrangThai;
    }

    public Model_lichSuHoaDon() {
    }


   
  
}
