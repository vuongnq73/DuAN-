/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_ChatLieu;
import com.raven.model.Model_DangAo;
import com.raven.model.Model_SanPham;
import com.raven.model.Model_SanPhamChiTiet;
import com.raven.model.Model_XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author MSI
 */
public class ChatLieuService extends InterfaceCRUD<Model_ChatLieu, String>{
    Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_ChatLieu entity) {
        String sql= "insert into ChatLieu(MaChatLieu,TenChatLieu,trangthai,CreateBy,UpdateBy) values(?,?,1,'NV002','NV002')";
        int check=0;
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaChatLieu());
            ps.setObject(2,entity.getTenChatLieu());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_ChatLieu entity) {
         String sql="Update chatlieu set maChatLieu=?,TenChatLieu=? where machatlieu=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,entity.getMaChatLieu());
            ps.setObject(2, entity.getTenChatLieu());
            ps.setObject(3, entity.getMaChatLieu());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
         String sql="delete chatlieu where machatlieu=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    @Override
    public List<Model_ChatLieu> selectALll() {
         String sql="select id, machatlieu,Tenchatlieu,TrangThai,CreateAt,CreateBy from ChatLieu";
        List<Model_ChatLieu>listCl= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_ChatLieu cl= new Model_ChatLieu();
                cl.setIdCl(rs.getInt(1));
                cl.setMaChatLieu(rs.getString(2));
                cl.setTenChatLieu(rs.getString(3));
                cl.setTrangthai(rs.getBoolean(4));
                cl.setCreateAt(rs.getDate(5));
                cl.setCreateBy(rs.getString(6));
               listCl.add(cl);
            }
            return listCl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_ChatLieu selectById(String id) {
        String sql="Select  maChatLieu,TenChatLieu from ChatLieu where MaChatLieu=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_ChatLieu sp = new Model_ChatLieu();
                sp.setMaChatLieu(rs.getString(1));
                sp.setTenChatLieu(rs.getString(2));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Model_ChatLieu selectByTen(String id) {
        String sql="select id, machatlieu,tenchatlieu,TrangThai,CreateAt,CreateBy from chatlieu where tenchatlieu=?";
        List<Model_ChatLieu>listCl= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_ChatLieu cl= new Model_ChatLieu();
                cl.setIdCl(rs.getInt(1));
                cl.setMaChatLieu(rs.getString(2));
                cl.setTenChatLieu(rs.getString(3));
                cl.setTrangthai(rs.getBoolean(4));
                cl.setCreateAt(rs.getDate(5));
                cl.setCreateBy(rs.getString(6));
                                return cl;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
