/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.raven.model.Model_NhanVien;

/**
 *
 * @author admin
 */
public class Auth {
    public static Model_NhanVien user=null;
    public static void clear(){
        Auth.user=null;
    }
    public static boolean isLogin(){
        return Auth.user!=null;
    }
    public static boolean isManager(){
      return Auth.isLogin() && user.isChuVu();
    }
}
