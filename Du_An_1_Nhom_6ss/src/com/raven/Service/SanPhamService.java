/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class SanPhamService extends InterfaceCRUD<Model_SanPham, String>{
        Connection conn= DBconnect.getConnection();

    @Override
    public void insert(Model_SanPham sp) {
        String sql= "insert into SANPHAM(MaSanPham,TenSanPham,CreateAt,CreateBY,trangthai) values(?,?,?,'NV002',1)";
        int check=0;
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,sp.getMaSP());
            ps.setObject(2, sp.getTenSP());
            ps.setObject(3, sp.getNgayTao());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public List<Model_SanPham> selectALll() {
        String sql="select id,masanpham,tensanpham,TrangThai,CreateAt,CreateBy from sanpham";
        List<Model_SanPham>listSp= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_SanPham xx= new Model_SanPham();
                xx.setId(rs.getInt(1));
                xx.setMaSP(rs.getString(2));
                xx.setTenSP(rs.getString(3));
                xx.setTrangthai(rs.getBoolean(4));
                xx.setNgayTao(rs.getDate(5));
                xx.setNguoitao(rs.getString(6));
               listSp.add(xx);
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Model_SanPham sp) {
        String sql="Update sanpham set Tensanpham=?,trangthai where masanpham=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,sp.getTenSP());
            
            ps.setObject(2,sp.isTrangthai());
            ps.setObject(3, sp.getMaSP());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String MaSP) {
        String sql="delete sanpham where masanpham=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,MaSP);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Model_SanPham selectById(String maSP) {
        String sql="Select  masanpham,tensanpham,createAt,CreateBy,trangthai from SanPham where masanpham=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, maSP);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_SanPham sp = new Model_SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setNgayTao(rs.getDate(3));
                sp.setNguoitao(rs.getString(4));
                sp.setTrangthai(rs.getBoolean(5));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Model_SanPham selectByTen(String ma) {
        String sql="Select  id,masanpham,tensanpham,createAt,CreateBy,trangthai from SanPham where tensanpham=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, ma);
            ResultSet rs= ps.executeQuery();
                while(rs.next()){
                Model_SanPham sp= new Model_SanPham();
                sp.setId(rs.getInt(1));
                sp.setMaSP(rs.getString(2));
                sp.setTenSP(rs.getString(3));
                sp.setNgayTao(rs.getDate(4));
                sp.setNguoitao(rs.getString(5));
                sp.setTrangthai(rs.getBoolean(6));
                return sp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Model_SanPham> FindByTen(String tenSp) {
        String sql="Select  masanpham,tensanpham,trangthai from SanPham where tensanpham like ?";
        List<Model_SanPham> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,tenSp+"%");
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_SanPham sp= new Model_SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangthai(rs.getBoolean(3));
               listSPCT.add(sp);
            }
            return listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public List<Model_SanPham> FindByTrangThaiNOT() {
        String sql="Select  masanpham,tensanpham,trangthai from SanPham where trangthai=0";
        List<Model_SanPham> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_SanPham sp= new Model_SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangthai(rs.getBoolean(3));
               listSPCT.add(sp);
            }
            return listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public List<Model_SanPham> FindByTrangThaiOn() {
        String sql="Select  masanpham,tensanpham,trangthai from SanPham where trangthai=1";
        List<Model_SanPham> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_SanPham sp= new Model_SanPham();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangthai(rs.getBoolean(3));
               listSPCT.add(sp);
            }
            return listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public int countSL(String Ma) {
        String sql="select sum(SANPHAMCHITIET.SoLuongTon)as soluong  from SANPHAM\n" +
"join SANPHAMCHITIET on SANPHAM.id=SANPHAMCHITIET.IdSanPham\n" +
"where SANPHAM.MaSanpham=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,Ma);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("soluong");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return 0;
     }
    public void updateKD(Model_SanPham sp) {
        String sql="Update sanpham set trangthai=1 where masanpham=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, sp.getMaSP());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateNKD(Model_SanPham sp) {
        String sql="Update sanpham set trangthai=0 where masanpham=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, sp.getMaSP());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
       

   

