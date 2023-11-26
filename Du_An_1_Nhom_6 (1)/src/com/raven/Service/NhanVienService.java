/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import java.util.List;
import com.raven.model.Model_NhanVien;
import java.util.ArrayList;
import java.sql.*;

public class NhanVienService extends InterfaceCRUD<Model_NhanVien, String> {

    String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Model_NhanVien> listNV = new ArrayList<>();

    @Override
    public void insert(Model_NhanVien entity) {
        sql = "insert into NhanVien(MaNhanVien,MatKhau,ChucVu,Hoten,GioiTinh,CCCD,SoDT,Email,DiaChi,TrangThai,CreateBy,UpdateBy) values (?,?,?,?,?,?,?,?,?,?,'NV002','NV002')";
        int check = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getMaNhanVien());
            ps.setObject(2, entity.getMatKhau());
            ps.setObject(3, entity.isChuVu());
            ps.setObject(4, entity.getHoTen());
            ps.setObject(5, entity.isGioiTinh());
            ps.setObject(6, entity.getCCCD());
            ps.setObject(7, entity.getSDT());
            ps.setObject(8, entity.getEmail());
            ps.setObject(9, entity.getDiaChi());
            ps.setObject(10, entity.isTrangThai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model_NhanVien entity) {
        sql = "Update NhanVien set MatKhau =?,ChucVu=?,Hoten=?,GioiTinh=?,CCCD=?,SoDT=?,Email=?,DiaChi=?,TrangThai=?,CreateBy='NV002',UpdateBy='NV002' where MaNhanVien=?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, entity.getMatKhau());
            ps.setObject(2, entity.isChuVu());
            ps.setObject(3, entity.getHoTen());
            ps.setObject(4, entity.isGioiTinh());
            ps.setObject(5, entity.getCCCD());
            ps.setObject(6, entity.getSDT());
            ps.setObject(7, entity.getEmail());
            ps.setObject(8, entity.getDiaChi());
            ps.setObject(9, entity.isTrangThai());
            ps.setObject(10, entity.getMaNhanVien());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        sql = "delete NhanVien where MaNhanVien=?";
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
    public List<Model_NhanVien> selectALll() {
        List<Model_NhanVien> listKH = new ArrayList<>();
        sql = "select MaNhanVien, MatKhau, ChucVu, HoTen, GioiTinh, CCCD, SoDT, Email,DiaChi, TrangThai from NhanVien";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_NhanVien kh = new Model_NhanVien(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model_NhanVien selectById(String id) {
        Model_NhanVien NV = null;
        sql = "select MaNhanVien,MatKhau,ChucVu,Hoten,GioiTinh,CCCD,SoDT,Email,DiaChi,TrangThai from NhanVien where MaNhanVien=?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                NV = new Model_NhanVien(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
            }
            return NV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Model_NhanVien> Find(String MaNhanVien,String MatKhau, boolean ChucVu,String HoTen,boolean GioiTinh, String CCCD, String SDT, String Email, String DiaChi, boolean TrangThai) {
        List<Model_NhanVien> listKH = new ArrayList<>();
        sql = "SELECT  MaNhanVien,MatKhau,ChucVu,Hoten,GioiTinh,CCCD,SoDT,Email,DiaChi,TrangThai FROM [dbo].[NhanVien] where MaNhanVien like ? or MatKhau like ? or ChucVu like ? or HoTen like ? or GioiTinh like ? or CCCD like ? or SoDT like ? or Email like ? or DiaChi like ? or TrangThai like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%"+MaNhanVien+"%");
            ps.setObject(2, "%"+MatKhau+"%");
            ps.setObject(3, "%"+ChucVu+"%");
            ps.setObject(4, "%"+HoTen+"%");
            ps.setObject(5, "%"+GioiTinh+"%");
            ps.setObject(6, "%"+CCCD+"%");
            ps.setObject(7, "%"+SDT+"%");
            ps.setObject(8, "%"+Email+"%");
            ps.setObject(9, "%"+DiaChi+"%");
            ps.setObject(10, "%"+TrangThai+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_NhanVien kh = new Model_NhanVien(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Model_NhanVien> selectTrangThai(boolean TrangThai){
         List<Model_NhanVien> listNV = new ArrayList<>();
         sql = "select * from [dbo].[NhanVien] where TrangThai =?";
         try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, TrangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_NhanVien kh = new Model_NhanVien(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
                listNV.add(kh);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Model_NhanVien> selectByTrangThai(String trangThai) {
        sql = "SELECT MaNhanVien,MatKhau,ChucVu,Hoten,GioiTinh,CCCD,SoDT,Email,DiaChi, TrangThai FROM NhanVien WHERE TrangThai=?";
        List<Model_NhanVien> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            rs = ps.executeQuery();

            while (rs.next()) {
                Model_NhanVien kh = new Model_NhanVien(
                        rs.getString("MaNhanVien"),
                        rs.getString("MatKhau"),
                        rs.getBoolean("ChucVu"),
                        rs.getString("Hoten"),
                        rs.getBoolean("GioiTinh"),
                        rs.getString("CCCD"),
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
    public List<Model_NhanVien> selectByChucVu(String chucVu) {
        sql = "SELECT MaNhanVien,MatKhau,ChucVu,Hoten,GioiTinh,CCCD,SoDT,Email,DiaChi, TrangThai FROM NhanVien WHERE ChucVu=?";
        List<Model_NhanVien> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, chucVu);
            rs = ps.executeQuery();

            while (rs.next()) {
                Model_NhanVien kh = new Model_NhanVien(
                        rs.getString("MaNhanVien"),
                        rs.getString("MatKhau"),
                        rs.getBoolean("ChucVu"),
                        rs.getString("Hoten"),
                        rs.getBoolean("GioiTinh"),
                        rs.getString("CCCD"),
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
    public List<Model_NhanVien> selectByGioiTinh(String gioiTinh) {
        sql = "SELECT MaNhanVien,MatKhau,ChucVu,Hoten,GioiTinh,CCCD,SoDT,Email,DiaChi, TrangThai FROM NhanVien WHERE GioiTinh=?";
        List<Model_NhanVien> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, gioiTinh);
            rs = ps.executeQuery();

            while (rs.next()) {
                Model_NhanVien kh = new Model_NhanVien(
                        rs.getString("MaNhanVien"),
                        rs.getString("MatKhau"),
                        rs.getBoolean("ChucVu"),
                        rs.getString("Hoten"),
                        rs.getBoolean("GioiTinh"),
                        rs.getString("CCCD"),
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
