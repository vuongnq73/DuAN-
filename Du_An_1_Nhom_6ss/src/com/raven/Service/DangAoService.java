/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_DangAo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class DangAoService extends InterfaceCRUD<Model_DangAo, String> {
   

    Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_DangAo entity) {
        String sql= "insert into DangAo(MaDangAO,TenDangAo,trangthai,CreateBy,UpdateBy) values(?,?,1,'NV002','NV002')";
        int check=0;
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaDangAo());
            ps.setObject(2,entity.getTenDangAo());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_DangAo entity) {
        String sql="Update chatlieu set madangao=?,tendangao=? where madangao=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaDangAo());
            ps.setObject(2, entity.getTenDangAo());
            ps.setObject(3, entity.getMaDangAo());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql="delete dangao where madangao=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_DangAo> selectALll() {
        
        String sql="select id, madangao,tendangao,TrangThai,CreateAt,CreateBy from DangAo";
        List<Model_DangAo>listDA= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_DangAo xx= new Model_DangAo();
                xx.setIdDangAo(rs.getInt(1));
                xx.setMaDangAo(rs.getString(2));
                xx.setTenDangAo(rs.getString(3));
                xx.setTrangthai(rs.getBoolean(4));
                xx.setCreateAt(rs.getDate(5));
                xx.setCreateBy(rs.getString(6));
               listDA.add(xx);
            }
            return listDA;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_DangAo selectById(String id) {
        String sql="Select  maDangao,tendangao from DangAo where MaDangAo=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_DangAo sp = new Model_DangAo();
                sp.setMaDangAo(rs.getString(1));
                sp.setTenDangAo(rs.getString(2));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   

    public Model_DangAo selectByTen(String ten) {
        String sql="select id, madangao,tendangao,TrangThai,CreateAt,CreateBy from dangao where tendangao=?";
        List<Model_DangAo>listDA= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, ten);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_DangAo xx= new Model_DangAo();
                xx.setIdDangAo(rs.getInt(1));
                xx.setMaDangAo(rs.getString(2));
                xx.setTenDangAo(rs.getString(3));
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
