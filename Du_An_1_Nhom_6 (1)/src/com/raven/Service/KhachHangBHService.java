/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.KhachHangBH;
import com.raven.model.Model_KhachHang;
import com.raven.model.Model_MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author MSI
 */
public class KhachHangBHService {
    Connection con=DBconnect.getConnection();
     public void insert(KhachHangBH entity) {
        String sql = "insert into KhachHang(MaKhachHang, HoTen, SoDT, Email, DiaChi, TrangThai, CreateBy,UpdateBy) values(?,?,?,?,?,1,?,?)";
        
        int check = 0;
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getMaKH());
            ps.setObject(2, entity.getHoTen());
            ps.setObject(3, entity.getSoDT());
            ps.setObject(4, entity.getEmail());
            ps.setObject(5, entity.getDiaChi());
            ps.setObject(6, entity.getCreateBy());
            ps.setObject(7, entity.getUpdateBy());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
     public void update(KhachHangBH entity) {
        String sql="update KhachHang set Hoten=?,SoDT=?,Email=?,DiaChi=?,UpdateAt=getdate(),UpdateBy=? where MakhachHang=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,entity.getHoTen());
            ps.setObject(2, entity.getSoDT());
            ps.setObject(3, entity.getEmail());
            ps.setObject(4, entity.getDiaChi());
            ps.setObject(5, entity.getUpdateBy());
            ps.setObject(6, entity.getMaKH());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public List<KhachHangBH> selectALll() {
         String sql="select id,MaKhachHang,hoten,SoDT,Email,DiaChi  from KhachHang";
        List<KhachHangBH>listMS= new ArrayList<>();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                KhachHangBH ms= new KhachHangBH();
                ms.setIdKH(rs.getInt(1));
                ms.setMaKH(rs.getString(2));
                ms.setHoTen(rs.getString(3));
                ms.setSoDT(rs.getString(4));
                ms.setEmail(rs.getString(5));
                ms.setDiaChi(rs.getString(6));
               listMS.add(ms);
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public KhachHangBH selectByTen(String id) {
        String sql="select id,MaKhachHang,hoten,SoDT,Email,DiaChi  from KhachHang where hoten=?";
        List<KhachHangBH>listMS= new ArrayList<>();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                KhachHangBH ms= new KhachHangBH();
                ms.setIdKH(rs.getInt(1));
                ms.setMaKH(rs.getString(2));
                ms.setHoTen(rs.getString(3));
                ms.setSoDT(rs.getString(4));
                ms.setEmail(rs.getString(5));
                ms.setDiaChi(rs.getString(6));
                return ms;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public KhachHangBH selectById(String id) {
        String sql="select MaKhachHang,hoten from KhachHang where maKhachHang=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                KhachHangBH sp = new KhachHangBH();
                sp.setMaKH(rs.getString(1));
                sp.setHoTen(rs.getString(2));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
