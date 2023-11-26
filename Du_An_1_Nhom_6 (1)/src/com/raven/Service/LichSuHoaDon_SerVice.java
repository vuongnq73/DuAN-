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
    List<Model_lichSuHoaDon1> listNV = new ArrayList<>();
    List<Model_lichSuHoaDon1> listHDCT = new ArrayList<>();

    public List<Model_lichSuHoaDon1> selectALllLichSuHoaDon() {
        sql = "  select HoaDon.NgayThanhToan,HoaDon.CreateBy,HoaDon.TrangThai   from HoaDon";
        List<Model_lichSuHoaDon1> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                Model_lichSuHoaDon1 LSHD = new Model_lichSuHoaDon1(rs.getDate(1), rs.getString(2), rs.getBoolean(3));
                listNV.add(LSHD);
            }
            return listNV;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }
    
        public Model_lichSuHoaDon1 selectLichSuHoaDonByID(String ID) {
        Model_lichSuHoaDon1 LSHD = null;
        sql = "	 select HoaDon.NgayThanhToan,HoaDon.CreateBy,HoaDon.TrangThai   from HoaDon where HoaDon.MaHoaDon=?";
        try {// lấy dc dữ liệu
            con = DBconnect.getConnection();// kết nôi
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
              LSHD = new Model_lichSuHoaDon1(rs.getDate(1), rs.getString(2), rs.getBoolean(3));

            }
            return LSHD;
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
            return null;
        }
    }

}
