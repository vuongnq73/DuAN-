/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import Utils.Image;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.raven.DBConnect.DBconnect;
import com.raven.Service.ChatLieuService;
import com.raven.Service.CoAoService;
import com.raven.Service.DangAoService;
import com.raven.Service.KichThuocService;
import com.raven.Service.MauSacService;
import com.raven.Service.NhanHieuService;
import com.raven.Service.SanPhamChiTietSV;
import com.raven.Service.SanPhamChiTietService;
import com.raven.Service.SanPhamService;
import com.raven.Service.XImage1;
import com.raven.Service.XuatXuService;
import com.raven.formTT.*;
import com.raven.model.Model_ChatLieu;
import com.raven.model.Model_CoAo;
import com.raven.model.Model_DangAo;
import com.raven.model.Model_KichThuoc;
import com.raven.model.Model_MauSac;
import com.raven.model.Model_NhanHieu;
import com.raven.model.Model_SanPham;
import com.raven.model.Model_SanPhamChiTiet;
import com.raven.model.Model_XuatXu;
import com.raven.model.SanPhamChiTiet;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author admin
 */
public class Form_San_Pham extends javax.swing.JPanel {

    /**
     * Creates new form form_Ban_Hang
     */
     DefaultTableModel model;
    DefaultTableModel model2;
    DefaultTableModel modelTT;
    Date thoiGianHienTai = new Date();
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
    
    SanPhamService spsv= new SanPhamService();
    SanPhamChiTietService spctsv= new SanPhamChiTietService();
    CoAoService cacv= new CoAoService();
    DangAoService dasv= new DangAoService();
    ChatLieuService clsv= new ChatLieuService();
    KichThuocService ktsv= new KichThuocService();
    MauSacService mssv= new MauSacService();
    NhanHieuService nhsv= new NhanHieuService();
    XuatXuService xxsv = new XuatXuService();
    List<Model_XuatXu> listxx = new ArrayList<>();
    List<Model_ChatLieu> listChatLieu = new ArrayList<>();
    List<Model_CoAo> listCoAo = new ArrayList<>();
    List<Model_DangAo> listDangAo = new ArrayList<>();
    List<Model_KichThuoc> listKichThuoc = new ArrayList<>();
    List<Model_NhanHieu> listNhanHieu = new ArrayList<>();
    List<Model_SanPhamChiTiet> listSPCT = new ArrayList<>();
    List<Model_SanPham> listSP= new ArrayList<>();
    List<Model_MauSac> listMauS= new ArrayList<>();
    List<SanPhamChiTiet> listCTSP= new ArrayList<>();
    SanPhamChiTietSV ctspsv= new SanPhamChiTietSV();
        JFileChooser jpc = new JFileChooser();

    private Form_NhanHieu nhanhieu;
    private Form_ChatLieu chatlieu;
    private Form_CoAo coao;
    private Form_MauSac mausac;
    private Form_KichThuoc kichthuoc;
    private Form_DangAo dangao;
    private Form_XuatXu xuatxu;
    int index=0;
    public Form_San_Pham() {
        initComponents();
        nhanhieu= new Form_NhanHieu();
        chatlieu= new Form_ChatLieu();
        coao= new Form_CoAo();
        mausac= new Form_MauSac();
        kichthuoc= new Form_KichThuoc();
        dangao= new Form_DangAo();
        xuatxu= new Form_XuatXu();
        model= (DefaultTableModel) tblSanPham.getModel();
        model2= (DefaultTableModel) tblSPCT.getModel();
        modelTT= (DefaultTableModel) tblTTSanPham.getModel();
        listSP= spsv.selectALll();
        listCTSP= ctspsv.selectALll();
        listChatLieu= clsv.selectALll();
       fillCbbXuatXu();
       fillCbbChatLieu();
       fillCbbCoAo();
       fillCbbDangAo();
       fillCbbKichThuoc();
       fillCbbMauSac();
       fillCbbNhanHieu();
       fillCbbSanPham();
        

        this.showDataClieu(listChatLieu);
        this.showDataCoAo(listCoAo);
        this.showData2(listCTSP);
        this.showData(spsv.FindByTrangThaiOn());
        Model_SanPhamChiTiet spct= new Model_SanPhamChiTiet();
        spctsv.updateSoLuong(spct);
    }
    
     private static String genMaSP() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genSPCT = "SP" + formattedNumber;
        return genSPCT;
    }
     
    private void showData(List<Model_SanPham>listSP){
        int index=1;
        model.setRowCount(0);
        for (Model_SanPham sp : listSP) {
            String trangthai="";
            if(sp.isTrangthai()==true){
                trangthai="Kinh doanh";
            }else{
                trangthai="Ngừng kinh doanh";
            }
            Object row[]={index++,sp.getMaSP(),sp.getTenSP(),spsv.countSL(sp.getMaSP()),trangthai};
            model.addRow(row);
        }
    }
    public void date(){
        Date thoiGianHienTai = new Date();
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
    }
     private static String genMaSPCT() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genSPCT = "SPCT" + formattedNumber;
        return genSPCT;
    }
    private void showData2(List<SanPhamChiTiet>listCTSP){
        int index=1;
        model2.setRowCount(0);
        for (SanPhamChiTiet spct : listCTSP) {
            String trangthai="";
             if(spct.isTrangthai()==true){
                trangthai="Đang bán";
            }else{
                trangthai="Ngừng bán";
            }
            Object row2[]={index++,spct.getTensanpham(),spct.getMaSPCT(),spct.getTenSPCT(),spct.getNoiXuatXu(),spct.getTenMauSac(),
spct.getTenNhanHieu(),spct.getTenKichThuoc(),spct.getTenCoAo(),spct.getTenDangAo(),spct.getTenChatLieu(),spct.getSoLuong(),spct.getGiaBan(),trangthai
            };
            model2.addRow(row2);
        }
        
    }
     
    private void fillTable(){
        int selectedrow= tblSanPham.getSelectedRow();
        txtMaSP.setText((String) tblSanPham.getValueAt(selectedrow, 1));
        txtTenSP.setText((String) tblSanPham.getValueAt(selectedrow, 2));
    }
    
    
     void fillTableSPCt(){
        int selectedrow= tblSPCT.getSelectedRow();
        cboSanPham.setSelectedItem(tblSPCT.getValueAt(selectedrow, 1));
        txtMaSPCT.setText((String) tblSPCT.getValueAt(selectedrow, 2));
        txtTenSPCT.setText((String) tblSPCT.getValueAt(selectedrow, 3));
        cboXuatXu.setSelectedItem(tblSPCT.getValueAt(selectedrow,4));
        cboMauSac.setSelectedItem(tblSPCT.getValueAt(selectedrow, 5));
        cboNhanHieu.setSelectedItem(tblSPCT.getValueAt(selectedrow, 6));
        cboKichThuoc.setSelectedItem(tblSPCT.getValueAt(selectedrow, 7));
        cboCoAo.setSelectedItem(tblSPCT.getValueAt(selectedrow, 8));
        cboDangAo.setSelectedItem(tblSPCT.getValueAt(selectedrow, 9));
        cboChatLieu.setSelectedItem(tblSPCT.getValueAt(selectedrow, 10));
        txtSoLuong.setText(String.valueOf((int) tblSPCT.getValueAt(selectedrow, 11)));
        txtGiaBan.setText(String.valueOf((BigDecimal) tblSPCT.getValueAt(selectedrow, 12)));
        

    }
  
    private Model_SanPham checkSP() {
        
             String ma = txtMaSP.getText();
             String ten = txtTenSP.getText();
             
             int count=0;
             if(ma.isEmpty()){
                 errMaSP.setText("Bạn chưa nhập mã sản phẩm");
                 errMaSP.setForeground(Color.red);
                 count++;
             }else if(ma.length()>20){
                 errMaSP.setText("Mã sản phẩm không được quá 20 kí tự");
                 errMaSP.setForeground(Color.red);
                 count++;
             }else{
                 errMaSP.setText("");
             }
             if(ten.isEmpty()){
                 errTenSP.setText("Bạn chưa nhâp tên sản phẩm");
                 errTenSP.setForeground(Color.red);
                 count++;
             }else if(ten.length()>50){
                 count++;
                 errTenSP.setText("Tên sản phẩm không được quá 50 kí tự");
                 errTenSP.setForeground(Color.red);
             }else{
                 errTenSP.setText("");
             }
             
             
             if(count==0){
                 Model_SanPham sp= new Model_SanPham(ma, ten);
                 return sp;
             }
         
             return null;

    }
    private Model_SanPhamChiTiet CheckSPCT(){
     
        String ma= txtMaSPCT.getText();
        String ten= txtTenSPCT.getText();
        String soLuong= txtSoLuong.getText();
        String giaBan= txtGiaBan.getText();
        String tenSp=(String) cboSanPham.getSelectedItem();
        String tenChatL=(String) cboChatLieu.getSelectedItem();
        String tenCoAo=(String) cboCoAo.getSelectedItem();
        String tenDangAo=(String) cboDangAo.getSelectedItem();
        String tenKichThuoc=(String) cboKichThuoc.getSelectedItem();
        String tenMauSac=(String) cboMauSac.getSelectedItem();
        String tenNhanHieu=(String) cboNhanHieu.getSelectedItem();
        String tenXuatXu=(String) cboXuatXu.getSelectedItem();
        Model_XuatXu xx= xxsv.selectByTen(tenXuatXu);
        Model_MauSac ms= mssv.selectByTen(tenMauSac);
        Model_ChatLieu cl= clsv.selectByTen(tenChatL);
        Model_CoAo ca= cacv.selectByTen(tenCoAo);
        Model_DangAo da= dasv.selectByTen(tenDangAo);
        Model_KichThuoc kt = ktsv.selectByTen(tenKichThuoc);
        Model_NhanHieu nh= nhsv.selectByTen(tenNhanHieu);
        Model_SanPham sp= spsv.selectByTen(tenSp);
        int count=0;
        if(ma.isEmpty()){
           count++;
           errMaSaPCT.setText("Bạn chưa nhập mã");
           errMaSaPCT.setForeground(Color.red);
        }else if(ma.length()>50){
            count++;
            errMaSaPCT.setText("Mã không được vượt quá 20 kí tự");
            errMaSaPCT.setForeground(Color.red);
        }else{
            errMaSaPCT.setText("");
        }
        if(ten.isEmpty()){
            count++;
            errTenSPCT.setText("Bạn chưa nhập tên");
            errTenSPCT.setForeground(Color.red);
        }else if(ten.length()>50){
            errTenSPCT.setText("Tên sản phẩm không được vượt quá 50 kí tự");
            errTenSPCT.setForeground(Color.red);
            count++;
        }else{
            errTenSPCT.setText("");
        }
        if(soLuong.isEmpty()){
            count++;
            errSoLuong.setText("Bạn chưa nhập số lượng");
            errSoLuong.setForeground(Color.red);
        }else{
            try {
                int sL= Integer.parseInt(soLuong);
                if(sL<0){
                    count++;
                    errSoLuong.setText("Vui lòng chỉ nhập số dương");
                    errSoLuong.setForeground(Color.red);
                }else{
                    errSoLuong.setText("");
                }
            } catch (Exception e) {
                errSoLuong.setText("Vui lòng chỉ nhập số");
                errSoLuong.setForeground(Color.red);
                    count++;
            }
        }
        if(giaBan.isEmpty()){
            count++;
            errgiaBan.setText("Bạn chưa nhập giá");
            errgiaBan.setForeground(Color.red);
        }else{
            try {
                double giaN= Double.parseDouble(giaBan);
                if(giaN<=0){
                    count++;
                    errgiaBan.setText("Vui lòng chỉ nhập số dương");
                    errgiaBan.setForeground(Color.red);
                }else{
                    errgiaBan.setText("");
                }
            } catch (Exception e) {
                errgiaBan.setText("Vui lòng chỉ nhập số");
                errgiaBan.setForeground(Color.red);
                    count++;
            }
        }
      
        
        int sL= Integer.parseInt(soLuong);
         double giaB= Double.parseDouble(giaBan);
        BigDecimal giaBa= BigDecimal.valueOf(giaB);

         if(count==0){
             Model_SanPhamChiTiet spct= new Model_SanPhamChiTiet(sp.getId(), ma, ten, sL, xx.getIdXX(), ms.getIdMauSac(), nh.getIdNhanHieu(), kt.getIdKichThuoc(), ca.getIdCoAo(), da.getIdDangAo(), cl.getIdCl(), giaBa);
             return spct;
         }
        
        return null;
    }
    
   private void addSPCT(){
       Model_SanPhamChiTiet spct=CheckSPCT();
       Model_SanPhamChiTiet spct1= spctsv.selectById(txtMaSPCT.getText());
       if(spct1!=null){
           JOptionPane.showMessageDialog(this,"Mã sản phẩm chi tiết đã tồn tại");
       }else if(spct!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm sản phẩm chi tiết không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            spctsv.insert(spct);
            showData2(ctspsv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm sản phẩm thành chi tiết công");
       }
       }
   }
   private void reset(){
       txtMaSP.setText("");
       txtTenSP.setText("");
       tblSanPham.clearSelection();
   }
   private void addSP() {
       Model_SanPham sp=checkSP();
       Model_SanPham sp1= spsv.selectById(txtMaSP.getText());
       
       if(sp1!=null){
           JOptionPane.showMessageDialog(this,"Mã sản phẩm đã tồn tại");
       }else if(sp!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm sản phẩm không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            spsv.insert(sp);
            showData(spsv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm sản phẩm thành công");
       }
       }
}
   private void removeSPCT(){
       int selectedrow= tblSPCT.getSelectedRow();
       if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSPCT= (String) tblSPCT.getValueAt(selectedrow,3);
           spctsv.delete(maSPCT);
           showData2(ctspsv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa sản phẩm thành công");
        }
       }else{
           JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
        
   }
   private void updateSPCT() {
        int selectedrow= tblSPCT.getSelectedRow();
        if(selectedrow>=0){
            Model_SanPhamChiTiet spct= CheckSPCT();
            
                if(spct!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                spctsv.update(spct);
                showData2(ctspsv.selectALll());
                JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
        }
        
}
    private void update() {
        int selectedrow= tblSanPham.getSelectedRow();
        if(selectedrow>=0){
            Model_SanPham sp= checkSP();
            
                if(sp!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    spsv.update(sp);
                    showData(spsv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
        }
        
}
    private void updateTTH() {
        int selectedrow= tblSanPham.getSelectedRow();
        if(selectedrow>=0){
            Model_SanPham sp= checkSP();
            
                if(sp!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn cập nhật trạng thái không", "Xác nhận",JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    spsv.updateKD(sp);
                    showData(spsv.FindByTrangThaiOn());
                    JOptionPane.showMessageDialog(this,"Cập nhật trạng thái thành công");
                                    rdoKinhDoanh.setSelected(true);

                }else if(choice==JOptionPane.NO_OPTION){
                    
                    spsv.updateNKD(sp);
                    showData(spsv.FindByTrangThaiNOT());
                                        rdoNgung.setSelected(true);

                    JOptionPane.showMessageDialog(this,"Cập nhật trạng thái thành công");
                }
            
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
        }
        
}
    private void updateTTSPCT() {
        int selectedrow= tblSPCT.getSelectedRow();
        if(selectedrow>=0){
            Model_SanPhamChiTiet spct= CheckSPCT();
            
                if(spct!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn cập nhật trạng thái không", "Xác nhận",JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    spctsv.updateON(spct);
                    showData2(ctspsv.selectALll());
                    JOptionPane.showMessageDialog(this,"Cập nhật trạng thái thành công");
                }else if(choice==JOptionPane.NO_OPTION){
                    spctsv.updateOff(spct);
                    showData2(ctspsv.selectALll());
                    JOptionPane.showMessageDialog(this,"Cập nhật trạng thái thành công");
                }
            
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
        }
        
}
     void fillCbbXuatXu(){
        cboXuatXu.removeAllItems();
        listxx= xxsv.selectALll();
         for (Model_XuatXu xx : listxx) {
             cboXuatXu.addItem(xx.getNoiXuatXu());
         }
    }
     void fillCbbChatLieu(){
        cboChatLieu.removeAllItems();
        listChatLieu= clsv.selectALll();
         for (Model_ChatLieu cl : listChatLieu) {
             cboChatLieu.addItem(cl.getTenChatLieu());
         }
    }
     void fillCbbCoAo(){
        cboCoAo.removeAllItems();
        listCoAo= cacv.selectALll();
         for (Model_CoAo ca : listCoAo) {
             cboCoAo.addItem(ca.getTenCoAo());
         }
    }
     
     void fillCbbDangAo(){
        cboDangAo.removeAllItems();
        listDangAo= dasv.selectALll();
         for (Model_DangAo da : listDangAo) {
             cboDangAo.addItem(da.getTenDangAo());
         }
    }
     void fillCbbKichThuoc(){
        cboKichThuoc.removeAllItems();
        listKichThuoc= ktsv.selectALll();
         for (Model_KichThuoc kt : listKichThuoc) {
             cboKichThuoc.addItem(kt.getTenKichThuoc());
         }
    }
     void fillCbbMauSac(){
        cboMauSac.removeAllItems();
        listMauS= mssv.selectALll();
         for (Model_MauSac ms : listMauS) {
             cboMauSac.addItem(ms.getTenMauSac());
         }
    }
     void fillCbbNhanHieu(){
        cboNhanHieu.removeAllItems();
        listNhanHieu= nhsv.selectALll();
         for (Model_NhanHieu nh : listNhanHieu) {
             cboNhanHieu.addItem(nh.getTenNhanHieu());
         }
    }
     void fillCbbSanPham(){
         cboSanPham.removeAllItems();
        listSP= spsv.selectALll();
        cboSanPham1.removeAllItems();
        cboSanPham1.addItem("Tất cả");
         for (Model_SanPham sp : listSP) {
             cboSanPham.addItem(sp.getTenSP());
             cboSanPham1.addItem(sp.getTenSP());
         }
         
    }
       
      
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txtFindSP = new javax.swing.JTextField();
        rdoKinhDoanh = new javax.swing.JRadioButton();
        rdoNgung = new javax.swing.JRadioButton();
        rdoTatCa = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        errNguoitao = new javax.swing.JLabel();
        errTenSP = new javax.swing.JLabel();
        errMaSP = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnTrangthai = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        a = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        txtMaSPCT = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cboNhanHieu = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboCoAo = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnTrangThaiSPCT = new javax.swing.JButton();
        cboXuatXu = new javax.swing.JComboBox<>();
        cboDangAo = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        errMaSaPCT = new javax.swing.JLabel();
        errTenSPCT = new javax.swing.JLabel();
        errSoLuong = new javax.swing.JLabel();
        errgiaBan = new javax.swing.JLabel();
        errGiaBan = new javax.swing.JLabel();
        errGIaBann = new javax.swing.JLabel();
        btnNhanHieu = new javax.swing.JButton();
        btnChatLieu = new javax.swing.JButton();
        btnDangAo = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        btnXuatXu = new javax.swing.JButton();
        btnCoAo = new javax.swing.JButton();
        btnKichThuoc = new javax.swing.JButton();
        txtTenSPCT = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtFindSPCT = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cboSanPham1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        errMaSPCT1 = new javax.swing.JPanel();
        rdoTTXuatXu = new javax.swing.JRadioButton();
        rdoTTMauSac = new javax.swing.JRadioButton();
        rdoTTChatLieu = new javax.swing.JRadioButton();
        rdoTTNhanHieu = new javax.swing.JRadioButton();
        rdoTTCoAo = new javax.swing.JRadioButton();
        rdoTTKichThuoc = new javax.swing.JRadioButton();
        rdoTTDangAo = new javax.swing.JRadioButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jpanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTTSanPham = new javax.swing.JTable();
        errMaSPCT2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtTenTT = new javax.swing.JTextField();
        txtMaTT = new javax.swing.JTextField();
        errMaTT = new javax.swing.JLabel();
        errTenTT = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("File");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        jMenu7.setText("jMenu7");

        jMenu8.setText("jMenu8");

        jMenu9.setText("jMenu9");

        jMenu10.setText("jMenu10");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(915, 600));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));
        jPanel9.setPreferredSize(new java.awt.Dimension(915, 600));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Trạng thái", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        tblSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSanPhamKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(5).setMinWidth(20);
            tblSanPham.getColumnModel().getColumn(5).setPreferredWidth(20);
            tblSanPham.getColumnModel().getColumn(5).setMaxWidth(20);
        }

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel18.setText("Tìm kiếm theo tên");

        txtFindSP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtFindSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindSPKeyReleased(evt);
            }
        });

        buttonGroup4.add(rdoKinhDoanh);
        rdoKinhDoanh.setSelected(true);
        rdoKinhDoanh.setText("Kinh doanh");
        rdoKinhDoanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKinhDoanhMouseClicked(evt);
            }
        });
        rdoKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKinhDoanhActionPerformed(evt);
            }
        });
        rdoKinhDoanh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdoKinhDoanhKeyReleased(evt);
            }
        });

        buttonGroup4.add(rdoNgung);
        rdoNgung.setText("Ngừng kinh doanh");
        rdoNgung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNgungActionPerformed(evt);
            }
        });
        rdoNgung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdoNgungKeyReleased(evt);
            }
        });

        buttonGroup4.add(rdoTatCa);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });
        rdoTatCa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdoTatCaKeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel26.setText("Trạng thái");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel18)
                .addGap(30, 30, 30)
                .addComponent(txtFindSP, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoTatCa)
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoKinhDoanh)
                    .addComponent(rdoNgung))
                .addGap(162, 162, 162))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtFindSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoTatCa)
                                    .addComponent(jLabel26)))
                            .addComponent(rdoKinhDoanh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoNgung)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel14.setText("Tên sản phẩm");

        txtMaSP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtMaSP.setEnabled(false);

        txtTenSP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel15.setText("Mã sản phẩm");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jButton1.setBackground(new java.awt.Color(99, 146, 238));
        jButton1.setText("Thêm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(99, 146, 238));
        jButton2.setText("Sửa");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(99, 146, 238));
        jButton3.setText("Làm mới");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        btnTrangthai.setBackground(new java.awt.Color(99, 146, 238));
        btnTrangthai.setText("Trạng thái");
        btnTrangthai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrangthaiMouseClicked(evt);
            }
        });
        btnTrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangthaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTrangthai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(errTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(30, 30, 30)
                        .addComponent(txtTenSP)))
                .addGap(129, 129, 129)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195)
                .addComponent(errNguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errNguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(12, 12, 12)
                .addComponent(errMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel10);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        a.setBackground(new java.awt.Color(255, 255, 255));
        a.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("Sản phẩm");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("Mã sản phẩm chi tiết");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setText("Tên sản phẩm chi tiết");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setText("Số lượng :");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setText("Giá bán:");

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        txtMaSPCT.setEnabled(false);
        txtMaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPCTActionPerformed(evt);
            }
        });
        txtMaSPCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaSPCTKeyPressed(evt);
            }
        });

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel20.setText("Nhãn hiệu:");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setText("Chất liệu:");

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setText("Kích thước:");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setText("Màu sắc");

        jLabel12.setText("VNĐ");

        jLabel22.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel22.setText("Cổ áo");

        cboNhanHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNhanHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhanHieuActionPerformed(evt);
            }
        });

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChatLieuActionPerformed(evt);
            }
        });

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKichThuocActionPerformed(evt);
            }
        });

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauSacActionPerformed(evt);
            }
        });

        cboCoAo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCoAoActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton4.setBackground(new java.awt.Color(99, 146, 238));
        jButton4.setText("Thêm");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(99, 146, 238));
        jButton5.setText("Sửa");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(99, 146, 238));
        jButton7.setText("Làm mới");

        btnTrangThaiSPCT.setBackground(new java.awt.Color(99, 146, 238));
        btnTrangThaiSPCT.setText("Trạng thái");
        btnTrangThaiSPCT.setEnabled(false);
        btnTrangThaiSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrangThaiSPCTMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrangThaiSPCT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(btnTrangThaiSPCT)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        cboXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboXuatXuActionPerformed(evt);
            }
        });

        cboDangAo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDangAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDangAoActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel16.setText("Xuất xứ");

        jLabel28.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel28.setText("Dáng áo");

        btnNhanHieu.setBackground(new java.awt.Color(255, 51, 51));
        btnNhanHieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnNhanHieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnNhanHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhanHieuMouseClicked(evt);
            }
        });
        btnNhanHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanHieuActionPerformed(evt);
            }
        });

        btnChatLieu.setBackground(new java.awt.Color(255, 51, 51));
        btnChatLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnChatLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChatLieuMouseClicked(evt);
            }
        });
        btnChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieuActionPerformed(evt);
            }
        });

        btnDangAo.setBackground(new java.awt.Color(255, 51, 51));
        btnDangAo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnDangAo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnDangAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangAoMouseClicked(evt);
            }
        });
        btnDangAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangAoActionPerformed(evt);
            }
        });

        btnMauSac.setBackground(new java.awt.Color(255, 51, 51));
        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnMauSac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMauSacMouseClicked(evt);
            }
        });
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        btnXuatXu.setBackground(new java.awt.Color(255, 51, 51));
        btnXuatXu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnXuatXu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatXuMouseClicked(evt);
            }
        });
        btnXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatXuActionPerformed(evt);
            }
        });

        btnCoAo.setBackground(new java.awt.Color(255, 51, 51));
        btnCoAo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnCoAo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnCoAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCoAoMouseClicked(evt);
            }
        });
        btnCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoAoActionPerformed(evt);
            }
        });

        btnKichThuoc.setBackground(new java.awt.Color(255, 51, 51));
        btnKichThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnKichThuoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKichThuocMouseClicked(evt);
            }
        });
        btnKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aLayout = new javax.swing.GroupLayout(a);
        a.setLayout(aLayout);
        aLayout.setHorizontalGroup(
            aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(errMaSaPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(aLayout.createSequentialGroup()
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(errTenSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(aLayout.createSequentialGroup()
                                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(errGIaBann, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(aLayout.createSequentialGroup()
                                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(errgiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(errGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(aLayout.createSequentialGroup()
                                                .addComponent(cboDangAo, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDangAo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(206, 206, 206))
                    .addGroup(aLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7))
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboNhanHieu, 0, 173, Short.MAX_VALUE)
                            .addComponent(cboChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboCoAo, 0, 173, Short.MAX_VALUE)
                            .addComponent(cboXuatXu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(aLayout.createSequentialGroup()
                        .addComponent(btnNhanHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        aLayout.setVerticalGroup(
            aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aLayout.createSequentialGroup()
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2)
                .addComponent(errMaSaPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(errTenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(errSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errgiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboDangAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28))
                    .addComponent(btnDangAo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errGIaBann, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(aLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNhanHieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(cboNhanHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(aLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(aLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(aLayout.createSequentialGroup()
                                        .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(125, 125, 125)
                                        .addComponent(btnXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(aLayout.createSequentialGroup()
                                        .addComponent(btnKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cboCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel22)))
                                        .addGap(27, 27, 27)
                                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16))))))
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel23.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel23.setText("Tìm kiếm sản phẩm:");

        txtFindSPCT.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtFindSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFindSPCTMouseClicked(evt);
            }
        });
        txtFindSPCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindSPCTKeyReleased(evt);
            }
        });

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm", "Mã SPCT", "Tên SPCT", "Xuất xứ", "Màu sắc", "Nhãn hiệu", "Kích thước", "Cổ áo", "Dáng áo", "Chất liệu", "Số lượng tồn", "Giá bán", "Trạng thái", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, true, true, true, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPCTMouseClicked(evt);
            }
        });
        tblSPCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSPCTKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblSPCT);
        if (tblSPCT.getColumnModel().getColumnCount() > 0) {
            tblSPCT.getColumnModel().getColumn(14).setMinWidth(20);
            tblSPCT.getColumnModel().getColumn(14).setPreferredWidth(20);
            tblSPCT.getColumnModel().getColumn(14).setMaxWidth(20);
        }

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("Sản phẩm");

        cboSanPham1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSanPham1ItemStateChanged(evt);
            }
        });
        cboSanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSanPham1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboSanPham1MousePressed(evt);
            }
        });
        cboSanPham1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPham1ActionPerformed(evt);
            }
        });
        cboSanPham1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboSanPham1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(txtFindSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFindSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", jPanel4);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        errMaSPCT1.setBackground(new java.awt.Color(255, 255, 255));
        errMaSPCT1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonGroup3.add(rdoTTXuatXu);
        rdoTTXuatXu.setSelected(true);
        rdoTTXuatXu.setText("Xuất xứ");
        rdoTTXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTTXuatXuMouseClicked(evt);
            }
        });
        rdoTTXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTTXuatXuActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoTTMauSac);
        rdoTTMauSac.setText("Màu sắc");
        rdoTTMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTTMauSacMouseClicked(evt);
            }
        });

        buttonGroup3.add(rdoTTChatLieu);
        rdoTTChatLieu.setText("Chất liệu");
        rdoTTChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTTChatLieuMouseClicked(evt);
            }
        });

        buttonGroup3.add(rdoTTNhanHieu);
        rdoTTNhanHieu.setText("Nhãn hiệu");
        rdoTTNhanHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTTNhanHieuMouseClicked(evt);
            }
        });

        buttonGroup3.add(rdoTTCoAo);
        rdoTTCoAo.setText("Cổ áo");
        rdoTTCoAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTTCoAoMouseClicked(evt);
            }
        });

        buttonGroup3.add(rdoTTKichThuoc);
        rdoTTKichThuoc.setText("Kích thước");
        rdoTTKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTTKichThuocMouseClicked(evt);
            }
        });

        buttonGroup3.add(rdoTTDangAo);
        rdoTTDangAo.setText("Dáng áo");
        rdoTTDangAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTTDangAoMouseClicked(evt);
            }
        });
        rdoTTDangAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTTDangAoActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(99, 146, 238));
        jButton8.setText("Thêm");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(99, 146, 238));
        jButton9.setText("Sửa");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(99, 146, 238));
        jButton10.setText("Xóa");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(99, 146, 238));
        jButton11.setText("Làm mới");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout errMaSPCT1Layout = new javax.swing.GroupLayout(errMaSPCT1);
        errMaSPCT1.setLayout(errMaSPCT1Layout);
        errMaSPCT1Layout.setHorizontalGroup(
            errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errMaSPCT1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(errMaSPCT1Layout.createSequentialGroup()
                        .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTTChatLieu)
                            .addComponent(rdoTTXuatXu)
                            .addComponent(rdoTTCoAo))
                        .addGap(48, 48, 48)
                        .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoTTMauSac)
                            .addGroup(errMaSPCT1Layout.createSequentialGroup()
                                .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoTTNhanHieu)
                                    .addComponent(rdoTTKichThuoc))
                                .addGap(46, 46, 46)
                                .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton11)))))
                    .addComponent(rdoTTDangAo))
                .addGap(13, 13, Short.MAX_VALUE))
        );
        errMaSPCT1Layout.setVerticalGroup(
            errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errMaSPCT1Layout.createSequentialGroup()
                .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(errMaSPCT1Layout.createSequentialGroup()
                        .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoTTXuatXu)
                            .addComponent(rdoTTMauSac))
                        .addGap(22, 22, 22)
                        .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoTTChatLieu)
                            .addComponent(rdoTTNhanHieu))
                        .addGap(24, 24, 24)
                        .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoTTCoAo)
                            .addComponent(rdoTTKichThuoc)))
                    .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton10)
                        .addGroup(errMaSPCT1Layout.createSequentialGroup()
                            .addComponent(jButton8)
                            .addGap(22, 22, 22)
                            .addGroup(errMaSPCT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton9)
                                .addComponent(jButton11)))))
                .addGap(28, 28, 28)
                .addComponent(rdoTTDangAo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpanel1.setBackground(new java.awt.Color(255, 255, 255));
        jpanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc tính sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        jpanel1.setLayout(new javax.swing.BoxLayout(jpanel1, javax.swing.BoxLayout.LINE_AXIS));

        tblTTSanPham.setBorder(null);
        tblTTSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã thuộc tính", "Tên thuộc tính"
            }
        ));
        tblTTSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTTSanPhamMouseClicked(evt);
            }
        });
        tblTTSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblTTSanPhamKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblTTSanPham);

        jpanel1.add(jScrollPane4);

        errMaSPCT2.setBackground(new java.awt.Color(255, 255, 255));
        errMaSPCT2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thuộc tính", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.BELOW_TOP));

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel11.setText("Tên thuộc tính:");

        jLabel29.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel29.setText("Mã thuộc tính:");

        txtTenTT.setAutoscrolls(false);
        txtTenTT.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtMaTT.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtMaTT.setEnabled(false);
        txtMaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout errMaSPCT2Layout = new javax.swing.GroupLayout(errMaSPCT2);
        errMaSPCT2.setLayout(errMaSPCT2Layout);
        errMaSPCT2Layout.setHorizontalGroup(
            errMaSPCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errMaSPCT2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(errMaSPCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(errMaSPCT2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(errMaSPCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenTT, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                            .addGroup(errMaSPCT2Layout.createSequentialGroup()
                                .addComponent(errTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, errMaSPCT2Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtMaTT))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, errMaSPCT2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(errMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(149, 149, 149))
        );
        errMaSPCT2Layout.setVerticalGroup(
            errMaSPCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errMaSPCT2Layout.createSequentialGroup()
                .addGroup(errMaSPCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(errMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(errMaSPCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errTenTT, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(errMaSPCT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errMaSPCT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(errMaSPCT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errMaSPCT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính sản phẩm", jPanel1);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel9);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void tblSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseClicked
        // TODO add your handling code here:
        int seleted= tblSPCT.getSelectedRow();
        this.fillTableSPCt();
         btnTrangThaiSPCT.setEnabled(false);
       int[] selectedRows = tblSPCT.getSelectedRows();
if (selectedRows.length > 0) {
    for (int selectedRow : selectedRows) {
        Boolean valueAtColumn14 = (Boolean) tblSPCT.getValueAt(selectedRow, 14);
        if (Boolean.TRUE.equals(valueAtColumn14)) {
                btnTrangThaiSPCT.setEnabled(true);            
        } else {
                btnTrangThaiSPCT.setEnabled(false);
        }
    }
} else {
    JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng");
} 
    }//GEN-LAST:event_tblSPCTMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        txtMaSP.setText(genMaSP());
        addSP();
        fillCbbSanPham();
    }//GEN-LAST:event_jButton1MouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        
        int selectedrow= tblSanPham.getSelectedRow();
        this.fillTable();
       if (evt.getClickCount()==2) {
                    if (selectedrow != -1) {
                        String ma= (String) tblSanPham.getValueAt(selectedrow, 2);
                        jTabbedPane1.setSelectedIndex(1);
                        showData2(ctspsv.selectSPCTTen(ma));
                    }
       }
       btnTrangthai.setEnabled(false);
       int[] selectedRows = tblSanPham.getSelectedRows();
if (selectedRows.length > 0) {
    for (int selectedRow : selectedRows) {
        Boolean valueAtColumn5 = (Boolean) tblSanPham.getValueAt(selectedRow, 5);
        if (Boolean.TRUE.equals(valueAtColumn5)) {
                btnTrangthai.setEnabled(true);            
        } else {
                btnTrangthai.setEnabled(false);
        }
    }
} else {
    JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng");
} 
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtMaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTTActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
        txtMaTT.setText("");
        txtTenTT.setText("");
        modelTT.setRowCount(0);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        // TODO add your handling code here:
        this.removeTT();
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        this.updateTT();
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        if(rdoTTChatLieu.isSelected()){
            txtMaTT.setText(genMaTTCL());
        }else if(rdoTTCoAo.isSelected()){
            txtMaTT.setText(genMaTTCA());
        }else if(rdoTTDangAo.isSelected()){
            txtMaTT.setText(genMaTTDA());
        }else if(rdoTTKichThuoc.isSelected()){
            txtMaTT.setText(genMaTTKT());
        }else if(rdoTTMauSac.isSelected()){
            txtMaTT.setText(genMaTTMS());
        }else if(rdoTTNhanHieu.isSelected()){
            txtMaTT.setText(genMaTTNH());
        }else if(rdoTTXuatXu.isSelected()){
            txtMaTT.setText(genMaTTXX());
        }
        this.addTT();
        fillCbbXuatXu();
       fillCbbChatLieu();
       fillCbbCoAo();
       fillCbbDangAo();
       fillCbbKichThuoc();
       fillCbbMauSac();
       fillCbbNhanHieu();
    }//GEN-LAST:event_jButton8MouseClicked

    private void rdoTTDangAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTTDangAoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTTDangAoActionPerformed

    private void rdoTTDangAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTTDangAoMouseClicked
        // TODO add your handling code here:
        modelTT.setRowCount(0);
        this.showDataDangAo(dasv.selectALll());
    }//GEN-LAST:event_rdoTTDangAoMouseClicked

    private void rdoTTKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTTKichThuocMouseClicked
        // TODO add your handling code here:
        modelTT.setRowCount(0);
        this.showDataKichThuoc(ktsv.selectALll());
    }//GEN-LAST:event_rdoTTKichThuocMouseClicked

    private void rdoTTCoAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTTCoAoMouseClicked
        // TODO add your handling code here:
        modelTT.setRowCount(0);
        this.showDataCoAo(cacv.selectALll());
    }//GEN-LAST:event_rdoTTCoAoMouseClicked

    private void rdoTTNhanHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTTNhanHieuMouseClicked
        // TODO add your handling code here:
        modelTT.setRowCount(0);
        this.showDataNhanHieu(nhsv.selectALll());
    }//GEN-LAST:event_rdoTTNhanHieuMouseClicked

    private void rdoTTChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTTChatLieuMouseClicked
        // TODO add your handling code here:
        modelTT.setRowCount(0);
        this.showDataClieu(clsv.selectALll());
    }//GEN-LAST:event_rdoTTChatLieuMouseClicked

    private void rdoTTMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTTMauSacMouseClicked
        // TODO add your handling code here:
        modelTT.setRowCount(0);
        this.showDataMauSac(mssv.selectALll());
    }//GEN-LAST:event_rdoTTMauSacMouseClicked

    private void rdoTTXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTTXuatXuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTTXuatXuActionPerformed

    private void rdoTTXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTTXuatXuMouseClicked
        // TODO add your handling code here:
        modelTT.setRowCount(0);
        this.showDataXuatXu(xxsv.selectALll());
    }//GEN-LAST:event_rdoTTXuatXuMouseClicked

    private void tblTTSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTTSanPhamKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTTSanPhamKeyPressed

    private void tblTTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTTSanPhamMouseClicked
        // TODO add your handling code here:
        int selectedrow= tblTTSanPham.getSelectedRow();
        this.fillThuocTinh(selectedrow);
    }//GEN-LAST:event_tblTTSanPhamMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void cboDangAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDangAoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboDangAoActionPerformed

    private void cboXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboXuatXuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboXuatXuActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        updateSPCT();
        fillCbbSanPham();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        QrCode();
        txtMaSPCT.setText(genMaSPCT());
        this.addSPCT();
        fillCbbSanPham();
    }//GEN-LAST:event_jButton4MouseClicked

    private void cboCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCoAoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboCoAoActionPerformed

    private void cboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSacActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboMauSacActionPerformed

    private void cboKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKichThuocActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboKichThuocActionPerformed

    private void cboChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChatLieuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboChatLieuActionPerformed

    private void cboNhanHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhanHieuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboNhanHieuActionPerformed

    private void txtMaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPCTActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void btnNhanHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhanHieuActionPerformed

    private void btnChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChatLieuActionPerformed

    private void btnDangAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangAoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangAoActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatXuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatXuActionPerformed

    private void btnCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoAoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCoAoActionPerformed

    private void btnKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKichThuocActionPerformed

    private void btnNhanHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanHieuMouseClicked
        // TODO add your handling code here:
        nhanhieu.setVisible(true);
    }//GEN-LAST:event_btnNhanHieuMouseClicked

    private void btnChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatLieuMouseClicked
        // TODO add your handling code here:
        chatlieu.setVisible(true);
    }//GEN-LAST:event_btnChatLieuMouseClicked

    private void btnMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMauSacMouseClicked
        // TODO add your handling code here:
        mausac.setVisible(true);
    }//GEN-LAST:event_btnMauSacMouseClicked

    private void btnKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKichThuocMouseClicked
        // TODO add your handling code here:
        kichthuoc.setVisible(true);
    }//GEN-LAST:event_btnKichThuocMouseClicked

    private void btnCoAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCoAoMouseClicked
        // TODO add your handling code here:
        coao.setVisible(true);
    }//GEN-LAST:event_btnCoAoMouseClicked

    private void btnXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatXuMouseClicked
        // TODO add your handling code here:
        xuatxu.setVisible(true);
    }//GEN-LAST:event_btnXuatXuMouseClicked

    private void btnDangAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangAoMouseClicked
        // TODO add your handling code here:
        dangao.setVisible(true);
    }//GEN-LAST:event_btnDangAoMouseClicked

    private void txtMaSPCTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSPCTKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtMaSPCTKeyPressed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtFindSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindSPKeyReleased
        // TODO add your handling code here:
        if(!txtFindSP.getText().isEmpty()){
        listSP=spsv.FindByTen(txtFindSP.getText());
         showData(listSP);
        }else{
            showData(spsv.selectALll());
        }
    }//GEN-LAST:event_txtFindSPKeyReleased

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseEntered

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        // TODO add your handling code here:
        showData(spsv.selectALll());
    }//GEN-LAST:event_rdoTatCaActionPerformed

    private void rdoKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKinhDoanhActionPerformed
        // TODO add your handling code here:
      if(rdoKinhDoanh.isSelected()){
            showData(spsv.FindByTrangThaiOn());
        }
    }//GEN-LAST:event_rdoKinhDoanhActionPerformed

    private void rdoNgungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNgungActionPerformed
        // TODO add your handling code here:
        if(rdoNgung.isSelected()){
            showData(spsv.FindByTrangThaiNOT());
        }
    }//GEN-LAST:event_rdoNgungActionPerformed

    private void tblSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSanPhamKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblSanPhamKeyReleased

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton3MouseClicked

    private void btnTrangthaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangthaiMouseClicked
        // TODO add your handling code here:
        updateTTH();
    }//GEN-LAST:event_btnTrangthaiMouseClicked

    private void rdoKinhDoanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKinhDoanhMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKinhDoanhMouseClicked

    private void rdoKinhDoanhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoKinhDoanhKeyReleased
        // TODO add your handling code here:
          
    }//GEN-LAST:event_rdoKinhDoanhKeyReleased

    private void rdoNgungKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoNgungKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_rdoNgungKeyReleased

    private void rdoTatCaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdoTatCaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTatCaKeyReleased

    private void txtFindSPCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindSPCTKeyReleased
        // TODO add your handling code here:
        if(!txtFindSPCT.getText().isEmpty()){
            String find= txtFindSPCT.getText();
            listCTSP=ctspsv.selectSPCTByTen(find,find, find, find, find);
            showData2(listCTSP);
        }else{
            showData2(ctspsv.selectALll());
        }
    }//GEN-LAST:event_txtFindSPCTKeyReleased

    private void txtFindSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFindSPCTMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtFindSPCTMouseClicked

    private void cboSanPham1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPham1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSanPham1ActionPerformed

    private void cboSanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSanPham1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboSanPham1MouseClicked

    private void cboSanPham1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboSanPham1KeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cboSanPham1KeyReleased

    private void cboSanPham1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSanPham1MousePressed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_cboSanPham1MousePressed

    private void cboSanPham1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSanPham1ItemStateChanged
        // TODO add your handling code here:
        
        if(cboSanPham1.getSelectedIndex()==0){
            showData2(ctspsv.selectALll());
        }else{
        showData2(ctspsv.selectSPCTTen((String) cboSanPham1.getSelectedItem()));
        }
    }//GEN-LAST:event_cboSanPham1ItemStateChanged

    private void btnTrangThaiSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangThaiSPCTMouseClicked
        // TODO add your handling code here:
        this.updateTTSPCT();
    }//GEN-LAST:event_btnTrangThaiSPCTMouseClicked

    private void btnTrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrangthaiActionPerformed

    private void tblSPCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSPCTKeyReleased
        // TODO add your handling code here:
        

    }//GEN-LAST:event_tblSPCTKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel a;
    private javax.swing.JButton btnChatLieu;
    private javax.swing.JButton btnCoAo;
    private javax.swing.JButton btnDangAo;
    private javax.swing.JButton btnKichThuoc;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnNhanHieu;
    private javax.swing.JButton btnTrangThaiSPCT;
    private javax.swing.JButton btnTrangthai;
    private javax.swing.JButton btnXuatXu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboCoAo;
    private javax.swing.JComboBox<String> cboDangAo;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNhanHieu;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboSanPham1;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JLabel errGIaBann;
    private javax.swing.JLabel errGiaBan;
    private javax.swing.JLabel errMaSP;
    private javax.swing.JPanel errMaSPCT1;
    private javax.swing.JPanel errMaSPCT2;
    private javax.swing.JLabel errMaSaPCT;
    private javax.swing.JLabel errMaTT;
    private javax.swing.JLabel errNguoitao;
    private javax.swing.JLabel errSoLuong;
    private javax.swing.JLabel errTenSP;
    private javax.swing.JLabel errTenSPCT;
    private javax.swing.JLabel errTenTT;
    private javax.swing.JLabel errgiaBan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JRadioButton rdoKinhDoanh;
    private javax.swing.JRadioButton rdoNgung;
    private javax.swing.JRadioButton rdoTTChatLieu;
    private javax.swing.JRadioButton rdoTTCoAo;
    private javax.swing.JRadioButton rdoTTDangAo;
    private javax.swing.JRadioButton rdoTTKichThuoc;
    private javax.swing.JRadioButton rdoTTMauSac;
    private javax.swing.JRadioButton rdoTTNhanHieu;
    private javax.swing.JRadioButton rdoTTXuatXu;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblTTSanPham;
    private javax.swing.JTextField txtFindSP;
    private javax.swing.JTextField txtFindSPCT;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSPCT;
    private javax.swing.JTextField txtMaTT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenSPCT;
    private javax.swing.JTextField txtTenTT;
    // End of variables declaration//GEN-END:variables
    private void showDataCoAo(List<Model_CoAo> listCA) {
        int index=1;
            modelTT.setRowCount(0);
            for (Model_CoAo ca : listCA) {

                Object row[]={index++,ca.getMaCoAo(),ca.getTenCoAo()};
                modelTT.addRow(row);
         }
    }
     private void showDataClieu(List<Model_ChatLieu> listCL) {
        int index=1;
            modelTT.setRowCount(0);
            for (Model_ChatLieu cl : listCL) {

                Object row[]={index++,cl.getMaChatLieu(),cl.getTenChatLieu()};
                modelTT.addRow(row);
         }
    }
     private void showDataXuatXu(List<Model_XuatXu> listXX) {
        int index=1;
            modelTT.setRowCount(0);
            for (Model_XuatXu xx : listXX) {

                Object row[]={index++,xx.getMaXuatXu(),xx.getNoiXuatXu()};
                modelTT.addRow(row);
         }
    }
     private void showDataDangAo(List<Model_DangAo> listDA) {
        int index=1;
            modelTT.setRowCount(0);
            for (Model_DangAo da : listDA) {

                Object row[]={index++,da.getMaDangAo(),da.getTenDangAo()};
                modelTT.addRow(row);
         }
    }
     private void showDataKichThuoc(List<Model_KichThuoc> listKT) {
        int index=1;
            modelTT.setRowCount(0);
            for (Model_KichThuoc kt : listKT) {

                Object row[]={index++,kt.getMaKichThuoc(),kt.getTenKichThuoc()};
                modelTT.addRow(row);
         }
    }
     private void showDataMauSac(List<Model_MauSac> listMS) {
        int index=1;
            modelTT.setRowCount(0);
            for (Model_MauSac ms : listMS) {

                Object row[]={index++,ms.getMaMauSac(),ms.getTenMauSac()};
                modelTT.addRow(row);
         }
    }
     private void showDataNhanHieu(List<Model_NhanHieu> listNH) {
        int index=1;
            modelTT.setRowCount(0);
            for (Model_NhanHieu nh : listNH) {

                Object row[]={index++,nh.getMaNhanHieu(),nh.getTenNhanHieu()};
                modelTT.addRow(row);
         }
    }
    private void fillThuocTinh(int selected){
        if(rdoTTChatLieu.isSelected()){
            Model_ChatLieu cl= clsv.selectALll().get(selected);
            txtMaTT.setText(cl.getMaChatLieu());
            txtTenTT.setText(cl.getTenChatLieu());
        }else if(rdoTTCoAo.isSelected()){
            Model_CoAo ca= cacv.selectALll().get(selected);
            txtMaTT.setText(ca.getMaCoAo());
            txtTenTT.setText(ca.getTenCoAo());
        }else if(rdoTTXuatXu.isSelected()){
            Model_XuatXu xx= xxsv.selectALll().get(selected);
            txtMaTT.setText(xx.getMaXuatXu());
            txtTenTT.setText(xx.getNoiXuatXu());
        }else if(rdoTTDangAo.isSelected()){
            Model_DangAo da= dasv.selectALll().get(selected);
            txtMaTT.setText(da.getMaDangAo());
            txtTenTT.setText(da.getTenDangAo());
        }else if(rdoTTKichThuoc.isSelected()){
            Model_KichThuoc kt= ktsv.selectALll().get(selected);
            txtMaTT.setText(kt.getMaKichThuoc());
            txtTenTT.setText(kt.getTenKichThuoc());
        }else if(rdoTTMauSac.isSelected()){
            Model_MauSac ms= mssv.selectALll().get(selected);
            txtMaTT.setText(ms.getMaMauSac());
            txtTenTT.setText(ms.getTenMauSac());
        }else if(rdoTTNhanHieu.isSelected()){
            Model_NhanHieu nh= nhsv.selectALll().get(selected);
            txtMaTT.setText(nh.getMaNhanHieu());
            txtTenTT.setText(nh.getTenNhanHieu());
    }
    }
      void addTT(){
          String ma= txtMaTT.getText();
          String ten=txtTenTT.getText();
          int count=0;
          if(ma.isEmpty()){
              errMaTT.setText("Bạn chưa nhập mã");
              errMaTT.setForeground(Color.red);
              count++;
          }else if(ma.length()>20){
              errMaTT.setText("Mã không được quá 20 kí tự");
              errMaTT.setForeground(Color.red);
              count++;
          }else{
              errMaTT.setText("");
          }
          if(ten.isEmpty()){
              errTenTT.setText("Bạn chưa nhập tên");
              errTenTT.setForeground(Color.red);
              count++;
          }else if(ten.length()>50){
              errTenTT.setText("Tên không được quá 50 kí tự");
              errTenTT.setForeground(Color.red);
              count++;
          }else{
              errTenTT.setText("");
          }
          if(count==0){
              if(rdoTTChatLieu.isSelected()){
          Model_ChatLieu cl= new Model_ChatLieu(ma, ten);
          Model_ChatLieu cl1= clsv.selectById(txtMaTT.getText());
          if(cl1!=null){
           JOptionPane.showMessageDialog(this,"Mã thuộc tính đã tồn tại");
       }else if(cl!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm thuộc tính không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            clsv.insert(cl);
            showDataClieu(clsv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm thuộc tính thành công");
       }
       }
      }else if(rdoTTCoAo.isSelected()){
                  Model_CoAo ca= new Model_CoAo(ma, ten);
          Model_CoAo ca1= cacv.selectById(txtMaTT.getText());
          if(ca1!=null){
           JOptionPane.showMessageDialog(this,"Mã thuộc tính đã tồn tại");
       }else if(ca!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm thuộc tính không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            cacv.insert(ca);
            showDataCoAo(cacv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm thuộc tính thành công");
              }
          }
      }if(rdoTTXuatXu.isSelected()){
          Model_XuatXu xx= new Model_XuatXu(ma, ten);
          Model_XuatXu xx1= xxsv.selectById(txtMaTT.getText());
          if(xx1!=null){
           JOptionPane.showMessageDialog(this,"Mã thuộc tính đã tồn tại");
       }else if(xx!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm thuộc tính không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            xxsv.insert(xx);
            showDataXuatXu(xxsv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm thuộc tính thành công");
       }
       }
      }else if(rdoTTDangAo.isSelected()){
                  Model_DangAo da= new Model_DangAo(ma, ten);
          Model_DangAo da1= dasv.selectById(txtMaTT.getText());
          if(da1!=null){
           JOptionPane.showMessageDialog(this,"Mã thuộc tính đã tồn tại");
       }else if(da!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm thuộc tính không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            dasv.insert(da);
            showDataDangAo(dasv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm thuộc tính thành công");
              }
          }
      }if(rdoTTKichThuoc.isSelected()){
          Model_KichThuoc kt= new Model_KichThuoc(ma, ten);
          Model_KichThuoc kt1= ktsv.selectById(txtMaTT.getText());
          if(kt1!=null){
           JOptionPane.showMessageDialog(this,"Mã thuộc tính đã tồn tại");
       }else if(kt!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm thuộc tính không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            ktsv.insert(kt);
            showDataKichThuoc(ktsv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm thuộc tính thành công");
       }
       }
      }else if(rdoTTMauSac.isSelected()){
                  Model_MauSac ms= new Model_MauSac(ma, ten);
          Model_MauSac ms1= mssv.selectById(txtMaSP.getText());
          if(ms1!=null){
           JOptionPane.showMessageDialog(this,"Mã thuộc tính đã tồn tại");
       }else if(ms!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm thuộc tính không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            mssv.insert(ms);
            showDataMauSac(mssv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm thuộc tính thành công");
              }
          }
      }else if(rdoTTNhanHieu.isSelected()){
                  Model_NhanHieu nh= new Model_NhanHieu(ma, ten);
          Model_NhanHieu nh1= nhsv.selectById(txtMaSP.getText());
          if(nh1!=null){
           JOptionPane.showMessageDialog(this,"Mã thuộc tính đã tồn tại");
       }else if(nh!=null){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn thêm thuộc tính không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
            nhsv.insert(nh);
            showDataNhanHieu(nhsv.selectALll());
            JOptionPane.showMessageDialog(this,"Thêm thuộc tính thành công");
              }
          }
      }
          }
      }
      private void removeTT(){
          
       int selectedrow= tblTTSanPham.getSelectedRow();
       if(rdoTTChatLieu.isSelected()){
       if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSP= (String) tblTTSanPham.getValueAt(selectedrow,1);
           clsv.delete(maSP);
           showDataClieu(clsv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa thuộc tính  thành công");
        }
       }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
                  
       }
       }else if(rdoTTCoAo.isSelected()){
           if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSP= (String) tblTTSanPham.getValueAt(selectedrow,1);
           cacv.delete(maSP);
           showDataCoAo(cacv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa thuộc tính  thành công");
       }
           }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTDangAo.isSelected()){
       if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSP= (String) tblTTSanPham.getValueAt(selectedrow,1);
           dasv.delete(maSP);
           showDataDangAo(dasv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa thuộc tính  thành công");
        }
       }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTKichThuoc.isSelected()){
           if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSP= (String) tblTTSanPham.getValueAt(selectedrow,1);
           ktsv.delete(maSP);
           showDataKichThuoc(ktsv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa thuộc tính  thành công");
       }
           }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTMauSac.isSelected()){
           if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSP= (String) tblTTSanPham.getValueAt(selectedrow,1);
           mssv.delete(maSP);
           showDataMauSac(mssv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa thuộc tính  thành công");
       }
           }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTNhanHieu.isSelected()){
       if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSP= (String) tblTTSanPham.getValueAt(selectedrow,1);
           nhsv.delete(maSP);
           showDataNhanHieu(nhsv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa thuộc tính  thành công");
        }
       }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTXuatXu.isSelected()){
           if(selectedrow>=0){
           int choice;
           choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
       if(choice==JOptionPane.YES_OPTION){
           String maSP= (String) tblTTSanPham.getValueAt(selectedrow,1);
           xxsv.delete(maSP);
           showDataXuatXu(xxsv.selectALll());
           JOptionPane.showMessageDialog(this,"Xóa thuộc tính  thành công");
       }
           }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else{
           JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
      }
       private void updateTT() {
           String ma= txtMaTT.getText();
          String ten=txtTenTT.getText();
          
              
        int selectedrow= tblTTSanPham.getSelectedRow();
        if(rdoTTChatLieu.isSelected()){
        if(selectedrow>=0){
             Model_ChatLieu cl= new Model_ChatLieu(ma, ten);
                if(cl!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    clsv.update(cl);
                    showData(spsv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
        }
       }else if(rdoTTCoAo.isSelected()){
           if(selectedrow>=0){
             Model_CoAo ca= new Model_CoAo(ma, ten);
                if(ca!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    cacv.update(ca);
                    showDataCoAo(cacv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
        }
       }else if(rdoTTDangAo.isSelected()){
            if(selectedrow>=0){
             Model_DangAo da= new Model_DangAo(ma, ten);
                if(da!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    dasv.update(da);
                    showDataDangAo(dasv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTKichThuoc.isSelected()){
            if(selectedrow>=0){
             Model_KichThuoc kt= new Model_KichThuoc(ma, ten);
                if(kt!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    ktsv.update(kt);
                    showDataKichThuoc(ktsv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTMauSac.isSelected()){
            if(selectedrow>=0){
             Model_MauSac ms= new Model_MauSac(ma, ten);
                if(ms!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    mssv.update(ms);
                    showDataMauSac(mssv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTNhanHieu.isSelected()){
            if(selectedrow>=0){
             Model_NhanHieu nh= new Model_NhanHieu(ma, ten);
                if(nh!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    nhsv.update(nh);
                    showDataNhanHieu(nhsv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }else if(rdoTTXuatXu.isSelected()){
            if(selectedrow>=0){
             Model_XuatXu xx= new Model_XuatXu(ma, ten);
                if(xx!=null){
                    int choice;
            choice= JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa không", "Xác nhận",JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.YES_OPTION){
                    xxsv.update(xx);
                    showDataXuatXu(xxsv.selectALll());
                    JOptionPane.showMessageDialog(this,"Sửa thành công");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng");
       }
       }
          }
       private static String genMaTTMS() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genTT = "MS" + formattedNumber;
        return genTT;
    }
       private static String genMaTTCL() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genTT = "CL" + formattedNumber;
        return genTT;
    }
       private static String genMaTTCA() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genTT = "CA" + formattedNumber;
        return genTT;
    }
       private static String genMaTTDA() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genTT = "DA" + formattedNumber;
        return genTT;
    }
       private static String genMaTTKT() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genTT = "KT" + formattedNumber;
        return genTT;
    }
       private static String genMaTTNH() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genTT = "NH" + formattedNumber;
        return genTT;
    }
       private static String genMaTTXX() {
        int randomNumber = new Random().nextInt(100000);
        String formattedNumber = String.format("%06d", randomNumber);
        String genTT = "XX" + formattedNumber;
        return genTT;
    }
       private static String genBarcode() {
        int randomNumber = new Random().nextInt(1000000000);
        String formattedNumber = String.format("%10d", randomNumber);
        String genTT = formattedNumber;
        return genTT;
    }
       
       private void QrCode(){
           try {
            ByteArrayOutputStream out = QRCode.from(txtMaSPCT.getText())
                    .to(ImageType.PNG).stream();
    
            String f_name = txtMaSPCT.getText();
            String Path_name = "D:\\12345ksa\\Du_An_1_Nhom_6 (1)\\barcode\\" ;
            
            FileOutputStream fout = new FileOutputStream(new File(Path_name +(f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
       }
}

