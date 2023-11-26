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
public class Model_DangAo {
    private int idDangAo;
    private String maDangAo;
    private String tenDangAo;
    private boolean trangthai;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;

    public Model_DangAo() {
    }

    public Model_DangAo(String maDangAo, String tenDangAo) {
        this.maDangAo = maDangAo;
        this.tenDangAo = tenDangAo;
    }

    public int getIdDangAo() {
        return idDangAo;
    }

    public void setIdDangAo(int idDangAo) {
        this.idDangAo = idDangAo;
    }

    public String getMaDangAo() {
        return maDangAo;
    }

    public void setMaDangAo(String maDangAo) {
        this.maDangAo = maDangAo;
    }

    public String getTenDangAo() {
        return tenDangAo;
    }

    public void setTenDangAo(String tenDangAo) {
        this.tenDangAo = tenDangAo;
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
