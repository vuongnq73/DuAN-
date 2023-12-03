/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.DBConnect.DBconnect;
import java.sql.*;
import com.raven.model.Model_LichSuHoaDon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class LichSuHoaDonService {

    public List<Model_LichSuHoaDon> getAll() {
        String query = "SELECT * FROM LichSuHoaDon";
        List<Model_LichSuHoaDon> list = new ArrayList<>();

        try {
            // kết nối và chạy câu lệnh sql trả về 1 table kết quả
            ResultSet rs = new DBconnect().getConnection().createStatement().executeQuery(query);
            // duyệt qua table để add nó vào list
            while (rs.next()) {
                list.add(new Model_LichSuHoaDon(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4),
                        rs.getString(5), rs.getDate(6), rs.getString(7), rs.getBoolean(8)));
            }
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
