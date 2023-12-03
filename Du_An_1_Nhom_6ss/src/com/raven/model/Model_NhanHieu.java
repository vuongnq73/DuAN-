/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Model_NhanHieu {
    private int idNhanHieu;
    private String maNhanHieu;
    private String tenNhanHieu;
    private boolean trangthai;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;

    public Model_NhanHieu() {
    }

    public Model_NhanHieu( String maNhanHieu, String tenNhanHieu) {
        this.idNhanHieu = idNhanHieu;
        this.maNhanHieu = maNhanHieu;
        this.tenNhanHieu = tenNhanHieu;
        this.trangthai = trangthai;
        this.createAt = createAt;
        this.createBy = createBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
    }

    public int getIdNhanHieu() {
        return idNhanHieu;
    }

    public void setIdNhanHieu(int idNhanHieu) {
        this.idNhanHieu = idNhanHieu;
    }

    

    public String getMaNhanHieu() {
        return maNhanHieu;
    }

    public void setMaNhanHieu(String maNhanHieu) {
        this.maNhanHieu = maNhanHieu;
    }

    public String getTenNhanHieu() {
        return tenNhanHieu;
    }

    public void setTenNhanHieu(String tenNhanHieu) {
        this.tenNhanHieu = tenNhanHieu;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
}
