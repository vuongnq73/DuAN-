/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_KhachHang;
import com.raven.model.table1_KhachHang;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class KhachHangService extends InterfaceCRUD<Model_KhachHang, String>{
    String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;   
    List<Model_KhachHang> listKH = new ArrayList<>();
    
    
    @Override
    public void insert(Model_KhachHang entity) {
        sql = "insert into KhachHang(MaKhachHang, HoTen, SoDT, Email, DiaChi, TrangThai, CreateBy,UpdateBy) values(?,?,?,?,?,?,'NV002','NV002')";
        int check = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getMaKH());
            ps.setObject(2, entity.getHoTen());
            ps.setObject(3, entity.getSoDt());
            ps.setObject(4, entity.getEmail());
            ps.setObject(5, entity.getDiaChi());
            ps.setObject(6, entity.isTrangThai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void update(Model_KhachHang entity) {
        sql = "Update KhachHang set  HoTen =?, SoDT=?, DiaChi=?, CCCD=?, TrangThai=?, CreateBy='NV002',UpdateBy='NV002' where MaKhachHang = ? ";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getHoTen());
            ps.setObject(2, entity.getSoDt());
            ps.setObject(3, entity.getEmail());
            ps.setObject(4, entity.getDiaChi());
            ps.setObject(5, entity.isTrangThai());
            ps.setObject(6, entity.getMaKH());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        sql = "delete KhachHang where MaKhachHang=?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

    @Override
    public List<Model_KhachHang> selectALll() {
        List<Model_KhachHang> listKH = new ArrayList<>();
        sql = "select MaKhachHang, HoTen, SoDT, Email, DiaChi, TrangThai  from KhachHang";
        try {
            con=DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_KhachHang kh = new Model_KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_KhachHang selectById(String id) {
        
         sql = "select MaKhachHang, HoTen, SoDT, Email, DiaChi, TrangThai  from KhachHang where MaKhachHang=?";
        try {
             con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
             rs = ps.executeQuery();
            while(rs.next()){
                Model_KhachHang kh = new Model_KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Model_KhachHang> Find(String MaKhachHang, String HoTen, String SoDT, String Email, String DiaChi, boolean TrangThai) {
        List<Model_KhachHang> listKH = new ArrayList<>();
        sql = "SELECT  MaKhachHang, HoTen, SoDT, Email, DiaChi, TrangThai FROM [dbo].[KhachHang] where MaKhachHang like ? or HoTen like ? or SoDT like ? or Email like ? or DiaChi like ? or CCCD like ? or TrangThai like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%"+MaKhachHang+"%");
            ps.setObject(2, "%"+HoTen+"%");
            ps.setObject(3, "%"+SoDT+"%");
            ps.setObject(4, "%"+Email+"%");
            ps.setObject(5, "%"+DiaChi+"%");
            ps.setObject(6, "%"+TrangThai+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_KhachHang kh = new Model_KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Model_KhachHang> selectByTrangThai(String trangThai) {
        sql = "SELECT MaKhachHang, HoTen, SoDT, Email, DiaChi, TrangThai FROM KhachHang WHERE TrangThai=?";
        List<Model_KhachHang> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            rs = ps.executeQuery();

            while (rs.next()) {
                Model_KhachHang kh = new Model_KhachHang(
                        rs.getString("MaKhachHang"),
                        rs.getString("HoTen"),
                        rs.getString("SoDT"),
                        rs.getString("Email"),
                        rs.getString("DiaChi"),
                        rs.getBoolean("TrangThai")
                );
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
