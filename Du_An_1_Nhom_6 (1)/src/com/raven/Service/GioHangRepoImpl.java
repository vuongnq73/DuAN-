/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import java.util.List;
import com.raven.model.modelGioHang;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class GioHangRepoImpl {
    Connection con= DBconnect.getConnection();
    public List<modelGioHang> getAll() {
        String sql="""
                   select chiTietSanPham.idCTSP,SanPham.maSanPham,SanPham.tenSanPham,GioHang.soLuong,chiTietSanPham.donGia,GioHang.tongTien
                   from GioHang 
                   join chiTietSanPham on GioHang.idSPCT=chiTietSanPham.idCTSP
                   join SanPham on chiTietSanPham.idSanPham=SanPham.idSanPham
                   """;
        try(PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            List<modelGioHang> list=new ArrayList<>();
            while(rs.next()){
                modelGioHang mgh=new modelGioHang();
                mgh.setIdCTSP(rs.getInt(1));
                mgh.setMaSanPham(rs.getString(2));
                mgh.setTenSanPham(rs.getString(3));
                mgh.setSoLuong( rs.getInt(4));
                mgh.setDonGia(rs.getDouble(5));
                mgh.setTongTien(rs.getDouble(6));
                list.add(mgh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public modelGioHang getGioHangByIDCTSP(Integer idCTSP) {
        String sql="""
                   select chiTietSanPham.idCTSP,SanPham.maSanPham,SanPham.tenSanPham,GioHang.soLuong,chiTietSanPham.donGia,GioHang.tongTien
                                      from GioHang 
                                      join chiTietSanPham on GioHang.idSPCT=chiTietSanPham.idCTSP
                                      join SanPham on chiTietSanPham.idSanPham=SanPham.idSanPham where idCTSP=?
                   """;
        try(PreparedStatement ps=con.prepareStatement(sql)){
            ps.setObject(1, idCTSP);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                modelGioHang mgh=new modelGioHang();
                mgh.setIdCTSP(rs.getInt(1));
                mgh.setMaSanPham(rs.getString(2));
                mgh.setTenSanPham(rs.getString(3));
                mgh.setSoLuong( rs.getInt(4));
                mgh.setDonGia(rs.getDouble(5));
                mgh.setTongTien(rs.getDouble(6));
                return mgh;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    
    
}
