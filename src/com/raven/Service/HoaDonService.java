/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.*;
import com.raven.model.Model_HoaDonChiTiet;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class HoaDonService {

    String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Model_Hoa_Don> listNV = new ArrayList<>();
    List<Model_HoaDonChiTiet> listHDCT = new ArrayList<>();

    public List<Model_Hoa_Don> selectALllHoaDon() {
        sql = "  exec getHoaDonn";
        List<Model_Hoa_Don> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_Hoa_Don NV = new Model_Hoa_Don(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBigDecimal(4), rs.getString(5), rs.getBigDecimal(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBoolean(11));
                listNV.add(NV);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }
       public List<Model_Hoa_Don> selectTrangThai() {
        sql = "   select TrangThai from HoaDon";
        List<Model_Hoa_Don> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_Hoa_Don NV = new Model_Hoa_Don(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBigDecimal(4), rs.getString(5), rs.getBigDecimal(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBoolean(11));
                listNV.add(NV);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public List<Model_HoaDonChiTiet> selectALllHoaDonChiTiet() {
        sql = "exec getHoaDonChiTiet";
        List<Model_HoaDonChiTiet> listHDCT = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_HoaDonChiTiet HDCT = new Model_HoaDonChiTiet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBigDecimal(7), rs.getBigDecimal(8));
                listHDCT.add(HDCT);
            }
            return listHDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public Model_HoaDonChiTiet selectALllHoaDonChiTietByID(String ID) {
        Model_HoaDonChiTiet HDCT = null;
        sql = "		  select SANPHAMCHITIET.MaSanPhamCT,SANPHAMCHITIET.TenSanPhamCT,NhanHieu.TenNhanHieu,MauSac.TenMauSac,KichThuoc.TenKichThuoc,HoaDonChiTiet.SoLuong,SANPHAMCHITIET.GiaBan,HoaDonChiTiet.SoLuong*SANPHAMCHITIET.GiaBan As Tongtien  from SANPHAMCHITIET\n"
                + "   join NhanHieu on NhanHieu.Id= SANPHAMCHITIET.IdNhanHieu\n"
                + "   join MauSac on MauSac.id= SANPHAMCHITIET.IdMauSac\n"
                + "   join KichThuoc on KichThuoc.id= SANPHAMCHITIET.IdKichThuoc\n"
                + "   join HoaDonChiTiet on HoaDonChiTiet.IdSanPhamCt= SANPHAMCHITIET.id\n"
                + "   join HoaDon on HoaDon.Id=HoaDonChiTiet.IdHoaDon\n"
                + "   where HoaDon.MaHoaDon=?";
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                HDCT = new Model_HoaDonChiTiet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBigDecimal(7), rs.getBigDecimal(8));

            }
            return HDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public Model_Hoa_Don selectALllHoaDonChiTietByNgay(Date Ngay) {
        Model_Hoa_Don HDCT = null;
        sql = "		  select SANPHAMCHITIET.MaSanPhamCT,SANPHAMCHITIET.TenSanPhamCT,NhanHieu.TenNhanHieu,MauSac.TenMauSac,KichThuoc.TenKichThuoc,HoaDonChiTiet.SoLuong,SANPHAMCHITIET.GiaBan,HoaDonChiTiet.SoLuong*SANPHAMCHITIET.GiaBan As Tongtien  from SANPHAMCHITIET\n"
                + "  join NhanHieu on NhanHieu.Id= SANPHAMCHITIET.IdNhanHieu\n"
                + "join MauSac on MauSac.id= SANPHAMCHITIET.IdMauSac\n"
                + "join KichThuoc on KichThuoc.id= SANPHAMCHITIET.IdKichThuoc\n"
                + "join HoaDonChiTiet on HoaDonChiTiet.IdSanPhamCt= SANPHAMCHITIET.id \n"
                + "join HoaDon on HoaDon.Id=HoaDonChiTiet.IdHoaDon where HoaDon.CreateAt=?";
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, Ngay);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                HDCT =new Model_Hoa_Don(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBigDecimal(4), rs.getString(5), rs.getBigDecimal(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getBoolean(11));

            }
            return HDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

public List<Model_Hoa_Don> selectALllHoaDonByTrangThai(boolean trangThai) {
    List<Model_Hoa_Don> listHD = new ArrayList<>();
    sql = "SELECT HoaDon.MaHoaDon, HoaDon.CreateBy, HoaDon.CreateAt, HoaDon.PhiShip, Voucher.MaVoucher, HoaDon.TongTien, NhanVien.MaNhanVien, KhachHang.Hoten, KhachHang.DiaChi, KhachHang.SoDT, HoaDon.TrangThai " +
            "FROM HoaDon " +
            "JOIN Voucher ON Voucher.id = HoaDon.IdVoucher " +
            "JOIN NhanVien ON NhanVien.id = HoaDon.IdNhanVien " +
            "JOIN KhachHang ON KhachHang.id = HoaDon.IdKhachHang " +
            "WHERE HoaDon.TrangThai = ?";

    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setObject(1, trangThai);
        rs = ps.executeQuery();

        while (rs.next()) {
            Model_Hoa_Don HD = new Model_Hoa_Don(
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
public List<Model_Hoa_Don> selectHoaDonByTongTien(BigDecimal TongTien) {
    List<Model_Hoa_Don> listHD = new ArrayList<>();
    sql = "SELECT HoaDon.MaHoaDon, HoaDon.CreateBy, HoaDon.CreateAt, HoaDon.PhiShip, Voucher.MaVoucher, HoaDon.TongTien, HinhThucThanhToan.TenHinhThucThanhToan, KhachHang.Hoten,KhachHang.SoDT, KhachHang.DiaChi,  HoaDon.TrangThai \n" +
"FROM HoaDon \n" +
"JOIN Voucher ON Voucher.id = HoaDon.IdVoucher \n" +
"JOIN KhachHang ON KhachHang.id = HoaDon.IdKhachHang \n" +
"JOIN HinhThucThanhToan ON HinhThucThanhToan.id = HoaDon.IdHinhThucThanhToan\n" +
"WHERE HoaDon.TongTien=?";

    try {
        con = DBconnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setObject(1, TongTien);
        rs = ps.executeQuery();

        while (rs.next()) {
            Model_Hoa_Don HD = new Model_Hoa_Don(
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
