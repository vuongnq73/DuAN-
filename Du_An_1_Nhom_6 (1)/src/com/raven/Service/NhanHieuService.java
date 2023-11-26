/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_NhanHieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class NhanHieuService extends InterfaceCRUD<Model_NhanHieu, String> {
   

    Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_NhanHieu entity) {
        String sql= "insert into NhanHieu(manhanhieu,tennhanhieu,trangthai,CreateBy,UpdateBy) values(?,?,1,'NV002','NV002')";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaNhanHieu());
            ps.setObject(2,entity.getTenNhanHieu());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_NhanHieu entity) {
        String sql="Update NhanHieu set manhanhieu=?,tennhanhieu=? where manhanhieu=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaNhanHieu());
            ps.setObject(2, entity.getTenNhanHieu());
            ps.setObject(3, entity.getMaNhanHieu());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql="delete Nhanhieu where manhanhieu=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_NhanHieu> selectALll() {
        
        String sql="select id, Manhanhieu,tennhanhieu,TrangThai,CreateAt,CreateBy from NhanHieu";
        List<Model_NhanHieu>listXX= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_NhanHieu xx= new Model_NhanHieu();
                xx.setIdNhanHieu(rs.getInt(1));
                xx.setMaNhanHieu(rs.getString(2));
                xx.setTenNhanHieu(rs.getString(3));
                xx.setTrangthai(rs.getBoolean(4));
                xx.setCreateAt(rs.getDate(5));
                xx.setCreateBy(rs.getString(6));
               listXX.add(xx);
            }
            return listXX;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_NhanHieu selectById(String id) {
        String sql="Select  manhanhieu,tennhanhieu from Nhanhieu where manhanhieu=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_NhanHieu sp = new Model_NhanHieu();
                sp.setMaNhanHieu(rs.getString(1));
                sp.setTenNhanHieu(rs.getString(2));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   

    @Override
    public Model_NhanHieu selectByTen(String ten) {
        String sql="select id, manhanhieu,tennhanhieu,TrangThai,CreateAt,CreateBy from NhanHieu where tennhanhieu=?";
        List<Model_NhanHieu>listNH= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, ten);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_NhanHieu xx= new Model_NhanHieu();
                xx.setIdNhanHieu(rs.getInt(1));
                xx.setMaNhanHieu(rs.getString(2));
                xx.setTenNhanHieu(rs.getString(3));
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
