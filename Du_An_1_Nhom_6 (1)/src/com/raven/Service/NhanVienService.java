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
public class NhanVienService extends InterfaceCRUD<Model_NhanVien, String>{
     String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Model_NhanVien> listNV = new ArrayList<>();

    @Override
    public void insert(Model_NhanVien entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_NhanVien> selectALll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Model_NhanVien selectById(String id) {
            Model_NhanVien NV=null;
        sql="select MaNhanVien,MatKhau,ChucVu,Hoten,GioiTinh,SoDT,Email,DiaChi,TrangThai,CreateAt,CreateBy,UpdateAt,UpdateBy,deleted from NhanVien where MaNhanVien=?";
        try {
            con=DBconnect.getConnection();
            ps=con.prepareStatement(sql);
            ps.setObject(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
NV= new Model_NhanVien(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4),  rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getString(8),  rs.getBoolean(9), rs.getDate(10), rs.getString(11), rs.getDate(12), rs.getString(13),  rs.getBoolean(14));
            }
            return NV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
    }

    }

    @Override
    public void update(Model_NhanVien entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public Model_NhanVien selectByTen(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
