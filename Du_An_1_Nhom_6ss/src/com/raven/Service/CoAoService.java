/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_CoAo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class CoAoService extends InterfaceCRUD<Model_CoAo, String> {
   

    Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_CoAo entity) {
        String sql= "insert into CoAO(maCoAo,TenCoAO,trangthai,CreateBy,UpdateBy) values(?,?,1,'NV002','NV002')";
        int check=0;
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaCoAo());
            ps.setObject(2,entity.getTenCoAo());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_CoAo entity) {
        String sql="Update CoAO set maCoAO=?,TenCoAo=? where maCoAo=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaCoAo());
            ps.setObject(2, entity.getTenCoAo());
            ps.setObject(3, entity.getMaCoAo());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    @Override
    public void delete(String id) {
        String sql="delete CoAo where maCoAO=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    @Override
    public List<Model_CoAo> selectALll() {
        
        String sql="select id, macoao,tencoao,TrangThai,CreateAt,CreateBy from CoAo";
        List<Model_CoAo>listCA= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_CoAo xx= new Model_CoAo();
                xx.setIdCoAo(rs.getInt(1));
                xx.setMaCoAo(rs.getString(2));
                xx.setTenCoAo(rs.getString(3));
                xx.setTrangthai(rs.getBoolean(4));
                xx.setCreateAt(rs.getDate(5));
                xx.setCreateBy(rs.getString(6));
               listCA.add(xx);
            }
            return listCA;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_CoAo selectById(String id) {
        String sql="Select  maCoAo,tenCoAo from CoAo where MaCoAo=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_CoAo sp = new Model_CoAo();
                sp.setMaCoAo(rs.getString(1));
                sp.setTenCoAo(rs.getString(2));
        
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }

   

    public Model_CoAo selectByTen(String ten) {
        String sql="select id, macoao,tencoao,TrangThai,CreateAt,CreateBy from CoAO where tencoao=?";
        List<Model_CoAo>listDA= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, ten);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_CoAo xx= new Model_CoAo();
                xx.setIdCoAo(rs.getInt(1));
                xx.setMaCoAo(rs.getString(2));
                xx.setTenCoAo(rs.getString(3));
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
