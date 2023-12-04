/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;
import com.raven.DBConnect.DBconnect;
import com.raven.model.HoaDon;
import com.raven.model.HoaDonChiTiet;
import com.raven.model.Model_Hoa_Don;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author MSI
 */
public class HoaDonChiTietService {
     public boolean createBill(HoaDonChiTiet hdct) {
        String sql="""
                   insert into HoaDonChiTiet(IdSanPhamCt,IdHoaDon,SoLuong,Thanhtien)
                   values(?,?,?,?)
                   """;
        try(Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, hdct.getIdSP());
            ps.setObject(2, hdct.getIdHD());
            ps.setObject(3, hdct.getSoluong());
            ps.setObject(4, hdct.getThanhtien());
           ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return true;
    }
     public HoaDon selectALllHoaDonByMa(String ma) {
         Connection con= DBconnect.getConnection();
        String sql = " SELECT id, mahoadon from hoadon where mahoadon=?";
        List<HoaDon> listNV = new ArrayList<>();
        try {// lấy dc dữ liệu
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setObject(1, ma);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {// đọc từng dòng dữ liệu
                HoaDon HD = new HoaDon();
                HD.setIdHd(rs.getInt(1));
                HD.setMaHD(rs.getString(2));
                return HD;
            }
        } catch (Exception e) {// k lấy dc dữ liệu
            e.printStackTrace();
        }
                    return null;

    }

}
