/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import com.raven.model.Model_SanPhamChiTiet;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class SanPhamChiTietService extends InterfaceCRUD<Model_SanPhamChiTiet, String>{
    Connection conn= DBconnect.getConnection();
    @Override
    public void insert(Model_SanPhamChiTiet spct) {
        String sql= "insert into sanphamchitiet(MaSanPhamCT,TenSanPhamCT,SoLuongTon,GiaBan,TrangThai,CreateBy,UpdateBy,IdXuatXu,IdSanPham,IdMauSac,IdKichThuoc,IdCoAo,IdDangAo,IdNhanHieu,IdChatLieu)\n" +
"	values(?,?,?,?,1,'NV002','NV002',?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,spct.getMaSPCT());
            ps.setObject(2,spct.getTenSPCT() );
            ps.setObject(3,spct.getSoLuong());
            ps.setObject(4,spct.getGiaBan());
            ps.setObject(5,spct.getIdXuatXu());
            ps.setObject(6,spct.getIdsanpham());
            ps.setObject(7,spct.getIdMauSac());
            ps.setObject(8,spct.getIdKichThuoc());            
            ps.setObject(9,spct.getIdCoAo());
            ps.setObject(10,spct.getIdDangAo());
            ps.setObject(11,spct.getIdNhanHieu());
            ps.setObject(12
                    ,spct.getIdChatLieu());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    @Override
    public void update(Model_SanPhamChiTiet spct) {
        String sql="update SANPHAMCHITIET set TenSanPhamCT=?,SoLuongTon=?,GiaBan=?,TrangThai=?,UpdateAt=GETDATE(),IdXuatXu=?,IdSanPham=?,IdMauSac=?,"
                + "IdKichThuoc=?,IdCoAo=?,IdDangAo=?,IdNhanHieu=?,IdChatLieu=? where MaSanPhamCT=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,spct.getTenSPCT());
            ps.setObject(2, spct.getSoLuong());
            ps.setObject(3,spct.getGiaBan());
            ps.setObject(4,spct.isTrangthai());
            ps.setObject(5,spct.getIdXuatXu());
            ps.setObject(6,spct.getIdsanpham());
            ps.setObject(7,spct.getIdMauSac());
            ps.setObject(8,spct.getIdKichThuoc());
            ps.setObject(9,spct.getIdCoAo());
            ps.setObject(10,spct.getIdDangAo());
            ps.setObject(11,spct.getIdNhanHieu());
            ps.setObject(12,spct.getIdChatLieu());
            ps.setObject(13,spct.getMaSPCT());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String maSPCT) {
        String sql="delete sanphamchitiet where masanphamCT=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1,maSPCT);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Model_SanPhamChiTiet selectById(String maSPCT) {
        String sql="select MaSanPhamCT,tensanPhamCt,SoLuongTon,GiaBan,TrangThai from SANPHAMCHITIET where MaSanPhamCT=?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setObject(1, maSPCT);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Model_SanPhamChiTiet spct = new Model_SanPhamChiTiet();
                spct.setMaSPCT(rs.getString(1));
                spct.setTenSPCT(rs.getString(2));
                spct.setSoLuong(rs.getInt(3));
                spct.setGiaBan(rs.getBigDecimal(4));
                spct.setTrangthai(rs.getBoolean(5));
                return spct;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Model_SanPhamChiTiet selectByTen(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_SanPhamChiTiet> selectALll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
