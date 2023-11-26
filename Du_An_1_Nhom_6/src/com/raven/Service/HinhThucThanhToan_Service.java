/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HinhThucThanhToan_Service {

    String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Model_HinhThucThanhToan> listNV = new ArrayList<>();

    public List<Model_HinhThucThanhToan> selectALllHinhThucThanhToan() {
        sql = "  exec getHoaDonn";
        List<Model_HinhThucThanhToan> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_HinhThucThanhToan NV = new Model_HinhThucThanhToan(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBigDecimal(4), rs.getString(5), rs.getBigDecimal(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBoolean(11));
                listNV.add(NV);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }
    public List<Model_HinhThucThanhToan> selectHoaDonHInhThucTT(String HTThanhToan) {
    List<Model_HinhThucThanhToan> listHD = new ArrayList<>();
    sql = "SELECT HoaDon.MaHoaDon, HoaDon.CreateBy, HoaDon.CreateAt, HoaDon.PhiShip, Voucher.MaVoucher, HoaDon.TongTien, HinhThucThanhToan.TenHinhThucThanhToan, KhachHang.Hoten, KhachHang.DiaChi, KhachHang.SoDT, HoaDon.TrangThai \n" +
"FROM HoaDon \n" +
"JOIN Voucher ON Voucher.id = HoaDon.IdVoucher \n" +
"JOIN KhachHang ON KhachHang.id = HoaDon.IdKhachHang \n" +
"JOIN HinhThucThanhToan ON HinhThucThanhToan.id = HoaDon.IdHinhThucThanhToan\n" +
"WHERE HinhThucThanhToan.TenHinhThucThanhToan=?";

    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setObject(1, HTThanhToan);
        rs = ps.executeQuery();

        while (rs.next()) {
            Model_HinhThucThanhToan HD = new Model_HinhThucThanhToan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getBigDecimal(4),
                    rs.getString(5),
                    rs.getBigDecimal(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getBoolean(11)
            );
            listHD.add(HD);
        }
        return listHD;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    } finally {
        // Đóng các tài nguyên (ResultSet, PreparedStatement, Connection) ở đây
    }
}
}
