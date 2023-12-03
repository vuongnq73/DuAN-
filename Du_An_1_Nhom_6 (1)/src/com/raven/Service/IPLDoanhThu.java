/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raven.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IPLDoanhThu {
        List<DoanhThu_Service> getSPTKThang(int month, int year);
    
    List<DoanhThu_Service> getSPTKNam(int year);
    
    List<DoanhThu_Service> getSPTKNgay(LocalDateTime ngayDau, LocalDateTime ngayCuoi);
}
