/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author MSI
 */
public class HoaDonCTService {
    public List<Model_HoaDonChiTiet> selectHoaDonBHByMa(String ma) {
        String sql = "select * from HoaDonChiTiet\n" +
"select SANPHAMCHITIET.TenSanPhamCT,SANPHAMCHITIET.GiaBan,HoaDonChiTiet.SoLuong,HoaDonChiTiet.Thanhtien\n" +
"from HoaDonChiTiet\n" +
"join SANPHAMCHITIET on HoaDonChiTiet.IdSanPhamCt=SANPHAMCHITIET.id\n" +
"join hoadon on hoadon.id= HoaDonChiTiet.IdHoaDon\n" +
"where hoadon.MaHoaDon=? ";
        List<Model_HoaDonChiTiet> listNV = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_HoaDonChiTiet hd= new Model_HoaDonChiTiet();
                hd.setTenSP(rs.getString(1));
                hd.setDonGia(rs.getBigDecimal(2));
                hd.setSoLuong(rs.getInt(3));
                hd.setTongTien(rs.getBigDecimal(4));
                listNV.add(hd);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
   
}
