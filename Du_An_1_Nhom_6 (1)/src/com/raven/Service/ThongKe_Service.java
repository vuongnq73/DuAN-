/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_ThongKe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author admin
 */
public class ThongKe_Service implements thongKeDao {

    String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Model_ThongKe> selectALllThongKe() {
        sql = " SELECT\n"
                + "sanpham.MaSanPham,\n"
                + "sanphamchitiet.tensanphamct,\n"
                + "    NhanHieu.TenNhanHieu,\n"
                + "    MauSac.TenMauSac,\n"
                + "    KichThuoc.TenKichThuoc,\n"
                + "   SUM(HoaDonChiTiet.SoLuong) AS TongSoLuong,\n"
                + "   SUM(hoadonchitiet.DonGia) as tien\n"
                + "FROM\n"
                + "    HoaDonChiTiet\n"
                + "JOIN SANPHAMCHITIET ON SANPHAMCHITIET.Id = HoaDonChiTiet.IdSanPhamCt\n"
                + "join sanpham on sanpham.Id=SANPHAMCHITIET.IdSanPham\n"
                + "JOIN nhanhieu ON NhanHieu.id = SANPHAMCHITIET.IdNhanHieu\n"
                + "JOIN mausac ON mausac.Id = SANPHAMCHITIET.IdMauSac\n"
                + "JOIN kichthuoc ON KichThuoc.id = SANPHAMCHITIET.IdKichThuoc\n"
                + "GROUP BY\n"
                + "   SANPHAM.MaSanPham,\n"
                + "    SANPHAMchitiet.TenSanPhamct,\n"
                + "    NhanHieu.TenNhanHieu,\n"
                + "   MauSac.TenMauSac, KichThuoc.TenKichThuoc";
        List<Model_ThongKe> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_ThongKe HD = new Model_ThongKe(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBigDecimal(7)
                );
                listNV.add(HD);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DoanhThu_Service> getListByDoanhThu(int timeUnit) {
        String timeUnitField;
        switch (timeUnit) {
            case Calendar.DAY_OF_MONTH:
                timeUnitField = "NgayThanhToan";
                break;
            case Calendar.MONTH:
                timeUnitField = "MONTH(NgayThanhToan)";
                break;
            case Calendar.YEAR:
                timeUnitField = "YEAR(NgayThanhToan)";
                break;
            default:
                throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
        }

        String sql = "SELECT " + timeUnitField + " as timeUnit, SUM(HoaDonChiTiet.DonGia) as doanhthu "
                + "FROM HoaDon JOIN HoaDonChiTiet ON HoaDonChiTiet.IdHoaDon = HoaDon.id "
                + "GROUP BY " + timeUnitField;

        List<DoanhThu_Service> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoanhThu_Service doanhThu = new DoanhThu_Service();
                doanhThu.setNgay_dang_ky(rs.getString("timeUnit"));
                doanhThu.setTien(rs.getInt("doanhthu"));
                list.add(doanhThu);
            }
            ps.close();
            con.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DoanhThu_Service> getListByDoanhThu1(int month) {
        String sql = "SELECT NgayThanhToan, SUM(HoaDonChiTiet.DonGia) as doanhthu "
                + "FROM HoaDon JOIN HoaDonChiTiet ON HoaDonChiTiet.IdHoaDon = HoaDon.id "
                + "WHERE  MONTH(NgayThanhToan) = ? "
                + "GROUP BY NgayThanhToan";

        List<DoanhThu_Service> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoanhThu_Service doanhThu = new DoanhThu_Service();
                doanhThu.setNgay_dang_ky(rs.getString("NgayThanhToan"));
                doanhThu.setTien(rs.getInt("doanhthu"));
                list.add(doanhThu);
            }
            ps.close();
            con.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

// ...
    public List<DoanhThu_Service> getListByDoanhThu2(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
        String sql = "SELECT NgayThanhToan, SUM(HoaDonChiTiet.DonGia) as doanhthu "
                + "FROM HoaDon JOIN HoaDonChiTiet ON HoaDonChiTiet.IdHoaDon = HoaDon.id "
                + "WHERE NgayThanhToan BETWEEN ? AND ? "
                + "GROUP BY NgayThanhToan";

        List<DoanhThu_Service> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);

            // Tạo đối tượng java.util.Date từ ngày bắt đầu và kết thúc
            Calendar calendar = Calendar.getInstance();
            calendar.set(startYear, startMonth - 1, startDay);
            Date startDate = new Date(calendar.getTimeInMillis());

            calendar.set(endYear, endMonth - 1, endDay);
            Date endDate = new Date(calendar.getTimeInMillis());

            // Đặt giá trị tham số
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

// Đặt giá trị tham số
            ps.setDate(1, sqlStartDate);
            ps.setDate(2, sqlEndDate);

            rs = ps.executeQuery();
            while (rs.next()) {
                DoanhThu_Service doanhThu = new DoanhThu_Service();
                doanhThu.setNgay_dang_ky(rs.getString("NgayThanhToan"));
                doanhThu.setTien(rs.getInt("doanhthu"));
                list.add(doanhThu);
            }
            ps.close();
            con.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

// ...

public List<DoanhThu_Service> getListByDoanhThu3(String startDate, String endDate) {
    String sql = "SELECT NgayThanhToan, SUM(HoaDonChiTiet.DonGia) as doanhthu " +
                 "FROM HoaDon JOIN HoaDonChiTiet ON HoaDonChiTiet.IdHoaDon = HoaDon.id " +
                 "WHERE NgayThanhToan BETWEEN ? AND ? " +
                 "GROUP BY NgayThanhToan";

    List<DoanhThu_Service> list = new ArrayList<>();
    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, startDate);
        ps.setString(2, endDate);

        rs = ps.executeQuery();
        while (rs.next()) {
            DoanhThu_Service doanhThu = new DoanhThu_Service();
            doanhThu.setNgay_dang_ky(rs.getString("NgayThanhToan"));
            doanhThu.setTien(rs.getInt("doanhthu"));
            list.add(doanhThu);
        }
        ps.close();
        con.close();
        return list;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


}
