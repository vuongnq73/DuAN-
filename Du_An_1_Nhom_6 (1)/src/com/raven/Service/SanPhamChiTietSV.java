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
    public List<SanPhamChiTiet> selectSPCTByTen(String ma,String tenspct,String mausac,String kichthuoc,String tenNH) {
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
"	where sanphamchitiet.masanphamCT like ? or Sanphamchitiet.tensanphamCT like ? or mausac.tenmausac like ? or kichthuoc.tenkichthuoc like ? or nhanhieu.tennhanhieu like ? ";
        List<SanPhamChiTiet> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,ma+"%");
            ps.setObject(2,tenspct+"%");
            ps.setObject(3,mausac+"%");
            ps.setObject(4,kichthuoc+"%");            
            ps.setObject(5,tenNH+"%");
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
    public List<SanPhamChiTiet> selectSPCTBanHang() {
        String sql="select SANPHAMCHITIET.MaSanPhamCT,SANPHAMCHITIET.TenSanPhamCT,NhanHieu.TenNhanHieu,MauSac.TenMauSac,KichThuoc.TenKichThuoc,SANPHAMCHITIET.SoLuongTon,SANPHAMCHITIET.GiaBan,SANPHAMCHITIET.Id\n" +
"from SANPHAMCHITIET\n" +
"join NhanHieu on SANPHAMCHITIET.IdNhanHieu=NhanHieu.id\n" +
"join XuatXu on XuatXu.Id= SANPHAMCHITIET.IdXuatXu\n" +
"join KichThuoc on KichThuoc.id = SANPHAMCHITIET.IdXuatXu\n" +
"join MauSac on MauSac.id= SANPHAMCHITIET.IdMauSac\n" +
"where SANPHAMCHITIET.TrangThai=1";
        List<SanPhamChiTiet> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                SanPhamChiTiet spct= new SanPhamChiTiet();
                
                spct.setMaSPCT(rs.getString(1));
                spct.setTenSPCT(rs.getString(2));
                spct.setTenNhanHieu(rs.getString(3));
                spct.setTenMauSac(rs.getString(4));
                spct.setTenKichThuoc(rs.getString(5));
                spct.setSoLuong(rs.getInt(6));
                spct.setGiaBan(rs.getBigDecimal(7));
                spct.setId(rs.getInt(8));
                listSPCT.add(spct);
            }
            return listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  List<SanPhamChiTiet> selectByMABH(String ma){
        String sql="select SANPHAMCHITIET.TenSanPhamCT,SANPHAMCHITIET.GiaBan from SANPHAMCHITIET where MaSanPhamCT=?";
         List<SanPhamChiTiet> listSPCT= new ArrayList<>();
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,ma);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                SanPhamChiTiet spct= new SanPhamChiTiet();
                spct.setTenSPCT(rs.getString(1));
                spct.setGiaBan(rs.getBigDecimal(2));
                listSPCT.add(spct);
            }
            return  listSPCT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
      
}
