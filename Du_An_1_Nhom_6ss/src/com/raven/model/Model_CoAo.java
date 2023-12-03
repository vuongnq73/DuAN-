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
public class Model_CoAo {
    private int idCoAo;
    private String maCoAo;
    private String tenCoAo;
    private boolean trangthai;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;

    public Model_CoAo() {
    }

    public Model_CoAo( String maCoAo, String tenCoAo) {
        this.maCoAo = maCoAo;
        this.tenCoAo = tenCoAo;
    }

    public int getIdCoAo() {
        return idCoAo;
    }

    public void setIdCoAo(int idCoAo) {
        this.idCoAo = idCoAo;
    }

    public String getMaCoAo() {
        return maCoAo;
    }

    public void setMaCoAo(String maCoAo) {
        this.maCoAo = maCoAo;
    }

    public String getTenCoAo() {
        return tenCoAo;
    }

    public void setTenCoAo(String tenCoAo) {
        this.tenCoAo = tenCoAo;
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
