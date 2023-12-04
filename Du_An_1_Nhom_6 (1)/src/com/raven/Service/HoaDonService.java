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
import org.apache.poi.hdgf.HDGFDiagram;

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
        sql = " SELECT  HoaDon.MaHoaDon, NhanVien.MaNhanVien, KhachHang.Hoten, KhachHang.SoDT,KhachHang.DiaChi,HoaDon.TongTien,HoaDon.LoaiKhachHang,HoaDon.NgayThanhToan,HinhThucThanhToan.TenHinhThucThanhToan ,HoaDon.TrangThai FROM HoaDon\n"
                + "join NhanVien on NhanVien.Id= HoaDon.IdNhanVien\n"
                + "join KhachHang on KhachHang.Id= HoaDon.IdKhachHang\n"
                + "join HinhThucThanhToan on HinhThucThanhToan.Id= HoaDon.IdHinhThucThanhToan";
        List<Model_Hoa_Don> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_Hoa_Don HD = new Model_Hoa_Don(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBigDecimal(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getBoolean(10)
                );
                listNV.add(HD);
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
                Model_Hoa_Don HD = new Model_Hoa_Don(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBigDecimal(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getBoolean(10)
                );
                listNV.add(HD);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public List<Model_HoaDonChiTiet> selectALllHoaDonChiTiet() {
        sql = "  select SANPHAMCHITIET.MaSanPhamCT,SANPHAMCHITIET.TenSanPhamCT,HoaDonChiTiet.SoLuong,HoaDonChiTiet.DonGia,HoaDon.TongTien  from HoaDonChiTiet\n"
                + " join HoaDon on HoaDon.Id= HoaDonChiTiet.IdHoaDon\n"
                + " join SANPHAMCHITIET on SANPHAMCHITIET.Id= HoaDonChiTiet.IdSanPhamCt ";
        List<Model_HoaDonChiTiet> listHDCT = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_HoaDonChiTiet HDCT = new Model_HoaDonChiTiet(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBigDecimal(4), rs.getBigDecimal(5));
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
        sql = "	 select SANPHAMCHITIET.MaSanPhamCT,SANPHAMCHITIET.TenSanPhamCT,HoaDonChiTiet.SoLuong,HoaDonChiTiet.DonGia,HoaDon.TongTien  from HoaDonChiTiet\n"
                + " join HoaDon on HoaDon.Id= HoaDonChiTiet.IdHoaDon\n"
                + " join SANPHAMCHITIET on SANPHAMCHITIET.Id= HoaDonChiTiet.IdSanPhamCt where HoaDon.MaHoaDon=?";
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                HDCT = new Model_HoaDonChiTiet(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBigDecimal(4), rs.getBigDecimal(5));

            }
            return HDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public Model_Hoa_Don selectALllHoaDonChiTietByNgay(Date Ngay) {
        Model_Hoa_Don HDCT = null;
        sql = "	SELECT  HoaDon.MaHoaDon, NhanVien.MaNhanVien, KhachHang.Hoten, KhachHang.SoDT,KhachHang.DiaChi,HoaDon.TongTien,HoaDon.LoaiKhachHang,HoaDon.NgayThanhToan,HinhThucThanhToan.TenHinhThucThanhToan ,HoaDon.TrangThai FROM HoaDon\n"
                + "join NhanVien on NhanVien.Id= HoaDon.IdNhanVien\n"
                + "join KhachHang on KhachHang.Id= HoaDon.IdKhachHang\n"
                + "join HinhThucThanhToan on HinhThucThanhToan.Id= HoaDon.IdHinhThucThanhToan\n"
                + "where HoaDon.NgayThanhToan=?";
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, Ngay);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_Hoa_Don HD = new Model_Hoa_Don(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBigDecimal(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getBoolean(10)
                );

            }
            return HDCT;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

    public List<Model_Hoa_Don> selectALllHoaDonByTrangThai(boolean trangThai) {
        List<Model_Hoa_Don> listHD = new ArrayList<>();
        sql = "SELECT  HoaDon.MaHoaDon, NhanVien.MaNhanVien, KhachHang.Hoten, KhachHang.SoDT,KhachHang.DiaChi,HoaDon.TongTien,HoaDon.LoaiKhachHang,HoaDon.NgayThanhToan,HinhThucThanhToan.TenHinhThucThanhToan ,HoaDon.TrangThai FROM HoaDon\n"
                + "join NhanVien on NhanVien.Id= HoaDon.IdNhanVien\n"
                + "join KhachHang on KhachHang.Id= HoaDon.IdKhachHang\n"
                + "join HinhThucThanhToan on HinhThucThanhToan.Id= HoaDon.IdHinhThucThanhToan\n"
                + "where HoaDon.TrangThai=?";

        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            rs = ps.executeQuery();

            while (rs.next()) {
                Model_Hoa_Don HD = new Model_Hoa_Don(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBigDecimal(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getBoolean(10)
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

    public List<Model_Hoa_Don> Find(String MahoaDon, String MaNhanVien, String TenKH, String SoDT, String DiaChi, BigDecimal tongTien, boolean LoaiHoaDon, Date NgayTT, String HTthanhToan, boolean trangThai) {
        List<Model_Hoa_Don> listKH = new ArrayList<>();
        sql = "SELECT  HoaDon.MaHoaDon, NhanVien.MaNhanVien, KhachHang.Hoten, KhachHang.SoDT,KhachHang.DiaChi,HoaDon.TongTien,HoaDon.LoaiKhachHang,HoaDon.NgayThanhToan,HinhThucThanhToan.TenHinhThucThanhToan ,HoaDon.TrangThai FROM HoaDon\n"
                + "join NhanVien on NhanVien.Id= HoaDon.IdNhanVien\n"
                + "join KhachHang on KhachHang.Id= HoaDon.IdKhachHang\n"
                + "join HinhThucThanhToan on HinhThucThanhToan.Id= HoaDon.IdHinhThucThanhToan\n"
                + "where HoaDon.MaHoaDon like ? or NhanVien.MaNhanVien like ? or KhachHang.Hoten like ? or KhachHang.SoDT like ? or KhachHang.DiaChi like ? or HoaDon.TongTien like ? or HoaDon.LoaiKhachHang like ? or HoaDon.NgayThanhToan like ? or HinhThucThanhToan.TenHinhThucThanhToan like ? or HoaDon.TrangThai like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + MahoaDon + "%");
            ps.setObject(2, "%" + MaNhanVien + "%");
            ps.setObject(3, "%" + TenKH + "%");
            ps.setObject(4, "%" + SoDT + "%");
            ps.setObject(5, "%" + DiaChi + "%");
            ps.setObject(6, "%" + tongTien + "%");
            ps.setObject(7, "%" + LoaiHoaDon + "%");
            ps.setObject(8, "%" + NgayTT + "%");
            ps.setObject(9, "%" + HTthanhToan + "%");
            ps.setObject(10, "%" + trangThai + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_Hoa_Don HD = new Model_Hoa_Don(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBigDecimal(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getBoolean(10)
                );
                listKH.add(HD);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Model_FindSP> FindSP(String MaHD, String MaSP, String TenSp, BigDecimal donGia, int SoLuong, BigDecimal tongTien, Date ngayTT) {
        List<Model_FindSP> listKH = new ArrayList<>();
        sql = "select HoaDon.MaHoaDon, SANPHAMCHITIET.MaSanPhamCT, SANPHAMCHITIET.TenSanPhamCT,HoaDonChiTiet.DonGia,HoaDonChiTiet.SoLuong,HoaDon.TongTien,HoaDon.CreateAt   from SANPHAMCHITIET\n"
                + "                join NhanHieu on NhanHieu.Id= SANPHAMCHITIET.IdNhanHieu\n"
                + "                join KichThuoc on KichThuoc.Id= SANPHAMCHITIET.IdKichThuoc\n"
                + "				join HoaDonChiTiet on HoaDonChiTiet.IdSanPhamCt= SANPHAMCHITIET.Id\n"
                + "                join HoaDon on HoaDonChiTiet.IdHoaDon= HoaDon.Id  where HoaDon.MaHoaDon like ? or SANPHAMCHITIET.MaSanPhamCT like ? or SANPHAMCHITIET.TenSanPhamCT like ? or HoaDonChiTiet.DonGia like ? or HoaDonChiTiet.SoLuong like ? or HoaDon.TongTien  like ? or HoaDon.CreateAt like ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + MaHD + "%");
            ps.setObject(2, "%" + MaSP + "%");
            ps.setObject(3, "%" + TenSp + "%");
            ps.setObject(4, "%" + donGia + "%");
            ps.setObject(5, "%" + SoLuong + "%");
            ps.setObject(6, "%" + tongTien + "%");
            ps.setObject(7, "%" + ngayTT + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Model_FindSP HD = new Model_FindSP(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getBigDecimal(6),
                        rs.getDate(7)
                );
                listKH.add(HD);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Model_Hoa_Don> selectALllHoaDonBH() {
        sql = " SELECT TOP 4  hoadon.MaHoadon,Hoadon.createBY,hoadon.TongTien,Hoadon.CreateAt,HoaDon.TrangThai from  HoaDon\n" +
"WHERE hoadon.id IS NOT NULL\n" +
"ORDER BY hoadon.id DESC" +
" ";
        List<Model_Hoa_Don> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_Hoa_Don hd= new Model_Hoa_Don();
                hd.setMaHD(rs.getString(1));
                hd.setNguoitao(rs.getString(2));
                hd.setTongTien(rs.getBigDecimal(3));
                hd.setNgayTao(rs.getDate(4));
                hd.setTrangThai(rs.getBoolean(5));
                listNV.add(hd);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }
    public boolean createBill(Model_Hoa_Don hd) {
        String sql="""
                   insert into HoaDon(maHoaDon,createBy,createAt,trangthai) values(?,?,?,?)
                   """;
        int check=0;
        try(PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, hd.getMaHD());
            ps.setObject(2, hd.getMaHD());
            ps.setObject(4, hd.isTrangThai());
            ps.setObject(3, hd.getNgayTao());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check>0;
    }
    public void update(HoaDonBH hdbh) {
        String sql="update hoadon set LoaiKhachHang=?,TongTien=?,TrangThai=1,NgayThanhToan=GETDATE(),"
                + "Ghichu=?,UpdateAt=getdate(),updateby=?,IdNhanVien=?,IdHinhThucThanhToan=? where MaHoaDon=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,hdbh.getLoaiKH());
            ps.setObject(2, hdbh.getTongTien());
            ps.setObject(3,hdbh.getGhiChu());
            ps.setObject(4,hdbh.getUpdateBy());
            ps.setObject(5,hdbh.getIdNhanVien());
            ps.setObject(6,hdbh.getIdHTTT());
            ps.setObject(7,hdbh.getMaHD());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//  bhjfmd,salsdfsb//
}
