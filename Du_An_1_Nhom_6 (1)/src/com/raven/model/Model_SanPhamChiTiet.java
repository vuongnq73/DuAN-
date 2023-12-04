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
public class Model_SanPhamChiTiet {
    private int idCTSP;
    private int idsanpham;
    private String maSPCT;
    private String tenSPCT;
    private int soLuong;
    private int idXuatXu;
    private int idMauSac;
    private int idNhanHieu;
    private int idKichThuoc;
    private int idCoAo;
    private int idDangAo;
    private int idChatLieu;
    private BigDecimal giaBan;
    private boolean trangthai;
    public Model_SanPhamChiTiet() {
    }

    public Model_SanPhamChiTiet(int idsanpham, String maSPCT, String tenSPCT, int soLuong, int idXuatXu, int idMauSac, int idNhanHieu, int idKichThuoc, int idCoAo, int idDangAo, int idChatLieu, BigDecimal giaBan) {
        this.idsanpham = idsanpham;
        this.maSPCT = maSPCT;
        this.tenSPCT = tenSPCT;
        this.soLuong = soLuong;
        this.idXuatXu = idXuatXu;
        this.idMauSac = idMauSac;
        this.idNhanHieu = idNhanHieu;
        this.idKichThuoc = idKichThuoc;
        this.idCoAo = idCoAo;
        this.idDangAo = idDangAo;
        this.idChatLieu = idChatLieu;
        this.giaBan = giaBan;
        this.trangthai = trangthai;
    }

    public int getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(int idCTSP) {
        this.idCTSP = idCTSP;
    }

    

    

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSPCT() {
        return tenSPCT;
    }

    public void setTenSPCT(String tenSPCT) {
        this.tenSPCT = tenSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdXuatXu() {
        return idXuatXu;
    }

    public void setIdXuatXu(int idXuatXu) {
        this.idXuatXu = idXuatXu;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public int getIdNhanHieu() {
        return idNhanHieu;
    }

    public void setIdNhanHieu(int idNhanHieu) {
        this.idNhanHieu = idNhanHieu;
    }

    public int getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(int idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public int getIdCoAo() {
        return idCoAo;
    }

    public void setIdCoAo(int idCoAo) {
        this.idCoAo = idCoAo;
    }

    public int getIdDangAo() {
        return idDangAo;
    }

    public void setIdDangAo(int idDangAo) {
        this.idDangAo = idDangAo;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

   
    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    
}
