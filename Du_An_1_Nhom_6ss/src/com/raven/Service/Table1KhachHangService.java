/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_LichSuHoaDon;
import com.raven.model.table1_KhachHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class Table1KhachHangService {

    public List<table1_KhachHang> getAll() {
        String query = "select kh.MaKhachHang, kh.Hoten, kh.SoDT ,spct.TenSanPhamCT, hdct.SoLuong, hdct.DonGia, hd.TongTien, hd.TrangThai from HoaDon hd\n"
                + " inner join HoaDonChiTiet hdct on hdct.IdHoaDon = hd.id\n"
                + " inner join SANPHAMCHITIET spct on spct.id = hdct.IdSanPhamCt\n"
                + " inner join KhachHang kh on kh.id = hd.IdKhachHang";
        List<table1_KhachHang> list = new ArrayList<>();

        try {
            // kết nối và chạy câu lệnh sql trả về 1 table kết quả
            ResultSet rs = new DBconnect().getConnection().createStatement().executeQuery(query);
            // duyệt qua table để add nó vào list
            while (rs.next()) {
                list.add(new table1_KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getBoolean(8)));
            }
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<table1_KhachHang> getLichSuByIdKhachHang(String idKhachHang) {
        String query = "select kh.MaKhachHang, kh.Hoten, kh.SoDT ,spct.TenSanPhamCT, hdct.SoLuong, hdct.DonGia, hd.TongTien, hd.TrangThai from HoaDon hd\n"
                + " inner join HoaDonChiTiet hdct on hdct.IdHoaDon = hd.id\n"
                + " inner join SANPHAMCHITIET spct on spct.id = hdct.IdSanPhamCt\n"
                + " inner join KhachHang kh on kh.id = hd.IdKhachHang "
                + " where kh.MaKhachHang = ?";
        List<table1_KhachHang> list = new ArrayList<>();

        try {
            // kết nối và chạy câu lệnh sql trả về 1 table kết quả
            PreparedStatement ps = new DBconnect().getConnection().prepareStatement(query);
            ps.setString(1, idKhachHang);
            ResultSet rs = ps.executeQuery();
            // duyệt qua table để add nó vào list
            while (rs.next()) {
                list.add(new table1_KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getBoolean(8)));
            }
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
