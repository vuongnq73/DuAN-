/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.SanPhamChiTiet;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class SanPhamChiTietSV implements SanPhamChiTietIplm{
    Connection conn= DBconnect.getConnection();
    @Override
    public List<SanPhamChiTiet> selectALll() {
        String sql="exec GetCTSP";
        List<SanPhamChiTiet> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                SanPhamChiTiet spct= new SanPhamChiTiet();
                spct.setTensanpham(rs.getString(1));
                spct.setMaSPCT(rs.getString(2));
                spct.setTenSPCT(rs.getString(3));
                spct.setSoLuong(rs.getInt(4));
                spct.setNoiXuatXu(rs.getString(5));
                spct.setTenMauSac(rs.getString(6));
                spct.setTenNhanHieu(rs.getString(7));
                spct.setTenKichThuoc(rs.getString(8));
                spct.setTenCoAo(rs.getString(9));
                spct.setTenDangAo(rs.getString(10));
                spct.setTenChatLieu(rs.getString(11));
                spct.setGiaBan(rs.getBigDecimal(12));
                spct.setTrangthai(rs.getBoolean(13));
                listSPCT.add(spct);
            }
            return listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<SanPhamChiTiet> selectSPCTByTen(String tenspct) {
        String sql="	Select SANPHAM.TenSanPham\n" +
"	,SANPHAMCHITIET.MaSanPhamCT\n" +
"	,SANPHAMCHITIET.TenSanPhamCT\n" +
"	,SANPHAMCHITIET.SoLuongTon\n" +
"	,XuatXu.NoiXuatXu\n" +
"	,MauSac.TenMauSac\n" +
"	,NhanHieu.TenNhanHieu\n" +
"	,KichThuoc.TenKichThuoc\n" +
"	,CoAo.TenCoAo\n" +
"	,DangAo.TenDangAo\n" +
"	,ChatLieu.TenChatLieu\n" +
"	,SANPHAMCHITIET.GiaBan\n" +
"	,SANPHAMCHITIET.TrangThai\n" +
"	from SANPHAMCHITIET\n" +
"	join sanpham on SANPHAM.Id=SANPHAMCHITIET.IdSanPham\n" +
"	join XuatXu on XuatXu.id= SANPHAMCHITIET.IdXuatXu\n" +
"	join MauSac on MauSac.id=SANPHAMCHITIET.IdMauSac\n" +
"	join  NhanHieu on NhanHieu.id= SANPHAMCHITIET.IdNhanHieu\n" +
"	join KichThuoc on KichThuoc.id=SANPHAMCHITIET.IdKichThuoc\n" +
"	join CoAo on CoAo.id=SANPHAMCHITIET.IdCoAo\n" +
"	join DangAo on DangAo.id= SANPHAMCHITIET.IdDangAo\n" +
"	join ChatLieu on ChatLieu.id= SANPHAMCHITIET.IdChatLieu\n" +
"	where Sanphamchitiet.tensanphamCT like ? ";
        List<SanPhamChiTiet> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,tenspct+"%");
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                SanPhamChiTiet spct= new SanPhamChiTiet();
                spct.setTensanpham(rs.getString(1));
                spct.setMaSPCT(rs.getString(2));
                spct.setTenSPCT(rs.getString(3));
                spct.setSoLuong(rs.getInt(4));
                spct.setNoiXuatXu(rs.getString(5));
                spct.setTenMauSac(rs.getString(6));
                spct.setTenNhanHieu(rs.getString(7));
                spct.setTenKichThuoc(rs.getString(8));
                spct.setTenCoAo(rs.getString(9));
                spct.setTenDangAo(rs.getString(10));
                spct.setTenChatLieu(rs.getString(11));
                spct.setGiaBan(rs.getBigDecimal(12));
                spct.setTrangthai(rs.getBoolean(13));
                listSPCT.add(spct);
            }
            return listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<SanPhamChiTiet> selectSPCTTen(String tensp) {
        String sql="	Select SANPHAM.TenSanPham\n" +
"	,SANPHAMCHITIET.MaSanPhamCT\n" +
"	,SANPHAMCHITIET.TenSanPhamCT\n" +
"	,SANPHAMCHITIET.SoLuongTon\n" +
"	,XuatXu.NoiXuatXu\n" +
"	,MauSac.TenMauSac\n" +
"	,NhanHieu.TenNhanHieu\n" +
"	,KichThuoc.TenKichThuoc\n" +
"	,CoAo.TenCoAo\n" +
"	,DangAo.TenDangAo\n" +
"	,ChatLieu.TenChatLieu\n" +
"	,SANPHAMCHITIET.GiaBan\n" +
"	,SANPHAMCHITIET.TrangThai\n" +
"	from SANPHAMCHITIET\n" +
"	join sanpham on SANPHAM.Id=SANPHAMCHITIET.IdSanPham\n" +
"	join XuatXu on XuatXu.id= SANPHAMCHITIET.IdXuatXu\n" +
"	join MauSac on MauSac.id=SANPHAMCHITIET.IdMauSac\n" +
"	join  NhanHieu on NhanHieu.id= SANPHAMCHITIET.IdNhanHieu\n" +
"	join KichThuoc on KichThuoc.id=SANPHAMCHITIET.IdKichThuoc\n" +
"	join CoAo on CoAo.id=SANPHAMCHITIET.IdCoAo\n" +
"	join DangAo on DangAo.id= SANPHAMCHITIET.IdDangAo\n" +
"	join ChatLieu on ChatLieu.id= SANPHAMCHITIET.IdChatLieu\n" +
"	where sanpham.tensanpham=? ";
        List<SanPhamChiTiet> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,tensp);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                SanPhamChiTiet spct= new SanPhamChiTiet();
                spct.setTensanpham(rs.getString(1));
                spct.setMaSPCT(rs.getString(2));
                spct.setTenSPCT(rs.getString(3));
                spct.setSoLuong(rs.getInt(4));
                spct.setNoiXuatXu(rs.getString(5));
                spct.setTenMauSac(rs.getString(6));
                spct.setTenNhanHieu(rs.getString(7));
                spct.setTenKichThuoc(rs.getString(8));
                spct.setTenCoAo(rs.getString(9));
                spct.setTenDangAo(rs.getString(10));
                spct.setTenChatLieu(rs.getString(11));
                spct.setGiaBan(rs.getBigDecimal(12));
                spct.setTrangthai(rs.getBoolean(13));
                listSPCT.add(spct);
            }
            return listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
