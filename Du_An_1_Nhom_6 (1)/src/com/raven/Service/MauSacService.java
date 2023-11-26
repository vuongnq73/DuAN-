/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_MauSac;
import com.raven.model.Model_SanPham;
import com.raven.model.Model_SanPhamChiTiet;
import com.raven.model.Model_XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class MauSacService extends InterfaceCRUD<Model_MauSac, String>{
        Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_MauSac entity) {
        String sql= "insert into mauSac(mamausac,tenmausac,trangthai,CreateBy,UpdateBy) values(?,?,1,'NV002','NV002')";
        int check=0;
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaMauSac());
            ps.setObject(2,entity.getTenMauSac());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_MauSac entity) {
        String sql="Update mausac set mamausac=?,tenmausac=? where mamausac=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaMauSac());
            ps.setObject(2, entity.getTenMauSac());
            ps.setObject(3, entity.getMaMauSac());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql="delete mausac where mamausac=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_MauSac> selectALll() {
         String sql="select id, Mamausac,tenmausac,TrangThai,CreateAt,CreateBy from mausac";
        List<Model_MauSac>listMS= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_MauSac ms= new Model_MauSac();
                ms.setIdMauSac(rs.getInt(1));
                ms.setMaMauSac(rs.getString(2));
                ms.setTenMauSac(rs.getString(3));
                ms.setTrangthai(rs.getBoolean(4));
                ms.setCreateAt(rs.getDate(5));
                ms.setCreateBy(rs.getString(6));
               listMS.add(ms);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_MauSac selectById(String id) {
        String sql="Select  mamausac,tenmausac from mausac where mamausac=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_MauSac sp = new Model_MauSac();
                sp.setMaMauSac(rs.getString(1));
                sp.setTenMauSac(rs.getString(2));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_MauSac selectByTen(String id) {
        String sql="select id, Mamausac,tenmausac,TrangThai,CreateAt,CreateBy from mausac where tenmausac=?";
        List<Model_MauSac>listMS= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_MauSac ms= new Model_MauSac();
                ms.setIdMauSac(rs.getInt(1));
                ms.setMaMauSac(rs.getString(2));
                ms.setTenMauSac(rs.getString(3));
                ms.setTrangthai(rs.getBoolean(4));
                ms.setCreateAt(rs.getDate(5));
                ms.setCreateBy(rs.getString(6));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    }
   

    
   
    

