/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_HoaDonChiTiet;
import com.raven.model.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LichSuHoaDon_SerVice {
        String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Model_lichSuHoaDon> listNV = new ArrayList<>();
    List<Model_lichSuHoaDon> listHDCT = new ArrayList<>();

    public List<Model_lichSuHoaDon> selectALllLichSuHoaDon() {
        sql = " select  HoaDon.MaHoaDon, HoaDon.CreateAt,HoaDon.CreateBy,KhachHang.Hoten,HoaDon.PhiShip,HinhThucThanhToan.TenHinhThucThanhToan,HoaDon.TongTien,Voucher.MaVoucher,HoaDon.UpdateAt,HoaDon.UpdateBy\n" +
" from HoaDon\n" +
"join Voucher on Voucher.id= HoaDon.IdVoucher\n" +
"join NhanVien on NhanVien.id= HoaDon.IdNhanVien\n" +
"join KhachHang on KhachHang.id= HoaDon.IdKhachHang\n" +
"join HinhThucThanhToan on HinhThucThanhToan.id= HoaDon.IdHinhThucThanhToan";
        List<Model_lichSuHoaDon> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_lichSuHoaDon LSHD = new Model_lichSuHoaDon(rs.getString(1), rs.getDate(2),rs.getString(3),rs.getString(4), rs.getBigDecimal(5), rs.getString(6), rs.getBigDecimal(7), rs.getString(8), rs.getDate(9), rs.getString(10));
                listNV.add(LSHD);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }
    
        public Model_LichSuHoaDonChiTiet selectLichSuHoaDonChiTietByID(String ID) {
        Model_LichSuHoaDonChiTiet LSHD = null;
        sql = "		select HoaDon.CreateAt,HinhThucThanhToan.TenHinhThucThanhToan,HoaDon.TrangThai,HoaDon.MaHoaDon,HoaDon.TongTien,HoaDon.PhiShip,KhachHang.Hoten,KhachHang.DiaChi,NhanVien.Hoten,HoaDon.UpdateAt,HoaDon.CreateBy,HoaDon.GhiChu from HoaDon\n" +
"                  join HinhThucThanhToan on HinhThucThanhToan.Id= HoaDon.IdHinhThucThanhToan\n" +
"				  join KhachHang on KhachHang.id= HoaDon.IdKhachHang\n" +
"				  join NhanVien on NhanVien.Id= HoaDon.IdNhanVien\n" +
"                  where HoaDon.MaHoaDon=?";
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
              LSHD = new Model_LichSuHoaDonChiTiet(rs.getDate(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getBigDecimal(5), rs.getBigDecimal(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getString(11), rs.getString(12));

            }
            return LSHD;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

}
