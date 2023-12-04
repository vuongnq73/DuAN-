/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.raven.model.Model_NhanVien;


/**
 *
 * @author ACER
 */
public class Auth {
    public static Model_NhanVien user=null;
    
    public static void clear(){
        Auth.user=null;
    }
    
    public static boolean isLogin(){
        return Auth.user!=null;
    }
    
    public static int idNhanVien(){
        return user.getIdNhanVien();
    }
    
    public static String maNhanVien(){
        return user.getMaNhanVien();
    }
    public static String tenNhanVien(){
        return user.getHoTen();
    }
    public static Boolean ChucVuNhanVien(){
        return user.isChuVu();
    }
    
    public static boolean isManager(){
        return Auth.isLogin() && user.isChuVu();
    }
}
