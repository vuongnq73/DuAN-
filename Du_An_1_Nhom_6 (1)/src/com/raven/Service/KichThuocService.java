/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_KichThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class KichThuocService extends InterfaceCRUD<Model_KichThuoc, String> {
   

    Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_KichThuoc entity) {
        String sql= "insert into KichThuoc(makichthuoc,tenkichthuoc,trangthai,CreateBy,UpdateBy) values(?,?,1,'NV002','NV002')";
        int check=0;
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaKichThuoc());
            ps.setObject(2,entity.getTenKichThuoc());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_KichThuoc entity) {
        String sql="Update Kichthuoc set makichthuoc=?,tenkichthuoc=? where makichthuoc=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaKichThuoc());
            ps.setObject(2, entity.getTenKichThuoc());
            ps.setObject(3, entity.getMaKichThuoc());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql="delete kichthuoc where makichthuoc=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_KichThuoc> selectALll() {
        
        String sql="select id, makichthuoc,tenkichthuoc,TrangThai,CreateAt,CreateBy from KichThuoc";
        List<Model_KichThuoc>listKT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_KichThuoc xx= new Model_KichThuoc();
                xx.setIdKichThuoc(rs.getInt(1));
                xx.setMaKichThuoc(rs.getString(2));
                xx.setTenKichThuoc(rs.getString(3));
                xx.setTrangthai(rs.getBoolean(4));
                xx.setCreateAt(rs.getDate(5));
                xx.setCreateBy(rs.getString(6));
               listKT.add(xx);
            }
            return listKT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_KichThuoc selectById(String id) {
        String sql="Select  makichthuoc,tenkichthuoc from Kichthuoc where makichthuoc=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_KichThuoc sp = new Model_KichThuoc();
                sp.setMaKichThuoc(rs.getString(1));
                sp.setTenKichThuoc(rs.getString(2));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   

    public Model_KichThuoc selectByTen(String ten) {
        String sql="select id, makichthuoc,tenkichthuoc,TrangThai,CreateAt,CreateBy from kichthuoc where tenkichthuoc=?";
        List<Model_KichThuoc>listKT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, ten);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_KichThuoc xx= new Model_KichThuoc();
                xx.setIdKichThuoc(rs.getInt(1));
                xx.setMaKichThuoc(rs.getString(2));
                xx.setTenKichThuoc(rs.getString(3));
                xx.setTrangthai(rs.getBoolean(4));
                xx.setCreateAt(rs.getDate(5));
                xx.setCreateBy(rs.getString(6));
                return xx;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
