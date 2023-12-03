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
public class Trahang_SerVice {

    String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Model_FindSP selectHoaDonByQR(String id) {

        Model_FindSP HDCT = null;
        sql = "   select  HoaDon.MaHoaDon, SANPHAMCHITIET.MaSanPhamCT, SANPHAMCHITIET.TenSanPhamCT,HoaDonChiTiet.DonGia,HoaDonChiTiet.SoLuong,HoaDon.TongTien,HoaDon.CreateAt   from SANPHAMCHITIET\n"
                + "                               join NhanHieu on NhanHieu.Id= SANPHAMCHITIET.IdNhanHieu\n"
                + "                               join KichThuoc on KichThuoc.Id= SANPHAMCHITIET.IdKichThuoc\n"
                + "                			join HoaDonChiTiet on HoaDonChiTiet.IdSanPhamCt= SANPHAMCHITIET.Id\n"
                + "                                join HoaDon on HoaDonChiTiet.IdHoaDon= HoaDon.Id where HoaDon.MaHoaDon=?";
        try {
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                HDCT = new Model_FindSP(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getBigDecimal(6),
                        rs.getDate(7)
                );

            }
            return HDCT;

        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public List<Model_FindSP> selectALllSanPhamFind() {
        sql = "   select  HoaDon.MaHoaDon, SANPHAMCHITIET.MaSanPhamCT, SANPHAMCHITIET.TenSanPhamCT,HoaDonChiTiet.DonGia,HoaDonChiTiet.SoLuong,HoaDon.TongTien,HoaDon.CreateAt   from SANPHAMCHITIET\n"
                + "                               join NhanHieu on NhanHieu.Id= SANPHAMCHITIET.IdNhanHieu\n"
                + "                               join KichThuoc on KichThuoc.Id= SANPHAMCHITIET.IdKichThuoc\n"
                + "                			join HoaDonChiTiet on HoaDonChiTiet.IdSanPhamCt= SANPHAMCHITIET.Id\n"
                + "                                join HoaDon on HoaDonChiTiet.IdHoaDon= HoaDon.Id";
        List<Model_FindSP> listHDCT = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_FindSP HDCT = new Model_FindSP(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getBigDecimal(6),
                        rs.getDate(7)
                );
                listHDCT.add(HDCT);
            }
            return listHDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public Model_TraHang selectALllTraHangBYID(String ID) {
        sql = " 						select  SANPHAMCHITIET.MaSanPhamCT, SANPHAMCHITIET.TenSanPhamCT,NhanHieu.TenNhanHieu,KichThuoc.TenKichThuoc,HoaDonChiTiet.DonGia,HoaDon.NgayThanhToan   from SANPHAMCHITIET\n" +
"                                            join NhanHieu on NhanHieu.Id= SANPHAMCHITIET.IdNhanHieu\n" +
"                                               join KichThuoc on KichThuoc.Id= SANPHAMCHITIET.IdKichThuoc\n" +
"                                			join HoaDonChiTiet on HoaDonChiTiet.IdSanPhamCt= SANPHAMCHITIET.Id\n" +
"                                               join HoaDon on HoaDonChiTiet.IdHoaDon= HoaDon.Id where SANPHAMCHITIET.MaSanPhamCT=?\n" +
"";
        Model_TraHang HDCT = new Model_TraHang();
        try {
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                HDCT = new Model_TraHang(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBigDecimal(5),
                        rs.getDate(6)
                );

            }
            return HDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

//    public model_TexFilL selectALllTextTraHang(String ID) {
//        sql = "   select HoaDon.CreateAt,HinhThucThanhToan.TenHinhThucThanhToan,HoaDon.MaHoaDon,HoaDon.TongTien,HoaDon.PhiShip,KhachHang.Hoten,KhachHang.DiaChi,NhanVien.Hoten,HoaDon.NgayThanhToan,HoaDon.GhiChu  from HoaDon         \n"
//                + "   join HinhThucThanhToan on HinhThucThanhToan.id= HoaDon.IdHinhThucThanhToan\n"
//                + "   join NhanVien on NhanVien.Id= HoaDon.IdNhanVien\n"
//                + "   join KhachHang on KhachHang.id= HoaDon.IdKhachHang=? ";
//        model_TexFilL HDCT = new model_TexFilL();
//        try {
//            con = DBconnect.getConnection();// kết nôi
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, ID);
//            rs = ps.executeQuery();
//            while (rs.next()) {// đọc từng dòng dữ liệu
//                
//
//    HDCT  = new model_TexFilL(
//            rs.getDate(1),
//            rs.getString(2),
//            rs.getString(3),
//            rs.getBigDecimal(4),
//            rs.getBigDecimal(5),
//            rs.getString(6),
//            rs.getString(7),
//            rs.getString(8),
//            rs.getDate(9),
//            rs.getString(10)
//    );
//
//}
//return HDCT;
//        } catch (Exception e) {// k lấy dc dữ liệu
//            e.printStackTrace();
//            return null;
//        }
//    }
    public List<model_TexFilL> selectALllTextTraHang(String ID) {
        sql = " select HoaDon.MaHoaDon,HoaDon.CreateAt,HinhThucThanhToan.TenHinhThucThanhToan,SANPHAMCHITIET.MaSanPhamCT,HoaDon.TongTien,HoaDon.PhiShip,KhachHang.Hoten,KhachHang.DiaChi,NhanVien.Hoten,HoaDon.NgayThanhToan,HoaDon.GhiChu  from HoaDon        \n"
                + "  join HinhThucThanhToan on HinhThucThanhToan.id= HoaDon.IdHinhThucThanhToan\n"
                + "   join NhanVien on NhanVien.Id= HoaDon.IdNhanVien\n"
                + "  join KhachHang on KhachHang.id= HoaDon.IdKhachHang\n"
                + "  join HoaDonChiTiet  on HoaDonChiTiet.IdHoaDon=hoadon.id\n"
                + "  join SANPHAMCHITIET on SANPHAMCHITIET.Id= HoaDonChiTiet.IdSanPhamCt where HoaDon.MaHoaDon=?";
        List<model_TexFilL> listHDCT = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                model_TexFilL HDCT = new model_TexFilL(
                        rs.getString(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBigDecimal(5),
                        rs.getBigDecimal(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getString(11)
                );
                listHDCT.add(HDCT);
            }
            return listHDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }
}
