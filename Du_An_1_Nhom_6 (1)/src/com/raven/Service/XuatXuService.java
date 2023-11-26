/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_XuatXu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class XuatXuService extends InterfaceCRUD<Model_XuatXu, String> {
   

    Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_XuatXu entity) {
        String sql= "insert into XuatXu(MaXuatXu,Noixuatxu,trangthai,CreateBy,UpdateBy) values(?,?,1,'NV002','NV002')";
        int check=0;
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaXuatXu());
            ps.setObject(2,entity.getNoiXuatXu());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_XuatXu entity) {
        String sql="Update XuatXu set maXuatxu=?,NoiXuatXu=? where maxuatxu=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaXuatXu());
            ps.setObject(2, entity.getNoiXuatXu());
            ps.setObject(3, entity.getMaXuatXu());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql="delete xuatxu where maxuatxu=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_XuatXu> selectALll() {
        
        String sql="select id, MaXuatXu,NoiXuatXu,TrangThai,CreateAt,CreateBy from xuatxu";
        List<Model_XuatXu>listXX= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_XuatXu xx= new Model_XuatXu();
                xx.setIdXX(rs.getInt(1));
                xx.setMaXuatXu(rs.getString(2));
                xx.setNoiXuatXu(rs.getString(3));
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
    public Model_XuatXu selectById(String id) {
        String sql="Select  maxuatxu,noixuatxu from xuatxu where maxuatxu=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_XuatXu sp = new Model_XuatXu();
                sp.setMaXuatXu(rs.getString(1));
                sp.setNoiXuatXu(rs.getString(2));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   

    @Override
    public Model_XuatXu selectByTen(String ten) {
        String sql="select id, MaXuatXu,NoiXuatXu,TrangThai,CreateAt,CreateBy from xuatxu where noixuatxu=?";
        List<Model_XuatXu>listXX= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, ten);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_XuatXu xx= new Model_XuatXu();
                xx.setIdXX(rs.getInt(1));
                xx.setMaXuatXu(rs.getString(2));
                xx.setNoiXuatXu(rs.getString(3));
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
