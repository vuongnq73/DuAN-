package com.raven.form;

import Utils.MsgBox;
import com.raven.DBConnect.DBconnect;
import com.raven.Service.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.raven.model.*;
import com.raven.model.Model_HoaDonChiTiet;
import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.*;

/**
 *
 * @author RAVEN
 */
public class Form_Hoa_Don extends javax.swing.JPanel {

    private HoaDonService service = new HoaDonService();
    private LichSuHoaDon_SerVice serviceLS = new LichSuHoaDon_SerVice();
    private HinhThucThanhToan_Service servicehttt = new HinhThucThanhToan_Service();

    public Form_Hoa_Don() {
        initComponents();

        filltableHoaDon();
        filltableLichSuHoaDon();
        fillComBotrangThai();
        fillComBoHinhThucThanhToan();
        tiemKiem.setFocusable(true);
        
    }
// FillComBobox Trạng Thái

    void fillComBotrangThai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) tt.getModel();
        try {
            List<Model_Hoa_Don> list = service.selectALllHoaDon();
            for (Model_Hoa_Don CD : list) {
                model.addElement(CD);
                System.out.println(CD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Loi Truy Van du Lieu");
        }
    }

    void fillComBoHinhThucThanhToan() {
        httt.removeAllItems();
        httt.addItem("Tất Cả");
        DefaultComboBoxModel model = (DefaultComboBoxModel) httt.getModel();

        try {
            List<Model_HinhThucThanhToan> list = servicehttt.selectALllHinhThucThanhToan();
            for (Model_HinhThucThanhToan CD : list) {
                model.addElement(CD);
                System.out.println(CD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Loi Truy Van du Lieu");
        }
    }

    // đẩy dữ liệu vào bảng
    void filltableHoaDon() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            List<Model_Hoa_Don> list = service.selectALllHoaDon();
            int stt = 1;

            for (Model_Hoa_Don hd : list) {
                String tt = "";
                if (hd.isTrangThai()) {
                    tt = "Đã Thanh Toán";
                } else {
                    tt = "Chưa Thanh Toán";
                }
                Object[] row = {
                    stt++,
                    hd.getMaHD(),
                    hd.getNguoiTao(),
                    hd.getNgayTao(),
                    hd.getPhiShip(),
                    hd.getMaVoucher(),
                    hd.getTongTien(),
                    hd.getHinhThucThanhToan(),
                    hd.getTenKH(),
                    hd.getDiaChi(),
                    hd.getSDT(),
                    tt

                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }

    void fillTableByTrangThai() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            int stt = 1;
            String selectedStatus = (tt.getSelectedItem() != null) ? tt.getSelectedItem().toString() : "Tất Cả";

            // Chuyển đổi giá trị từ ComboBox thành boolean
            boolean trangThai = ("Đã Thanh Toán".equals(selectedStatus));

            List<Model_Hoa_Don> list = service.selectALllHoaDonByTrangThai(trangThai);

            for (Model_Hoa_Don hd : list) {
                // Kiểm tra trạng thái được chọn từ ComboBox
                if (("Tất Cả".equals(selectedStatus))
                        || ("Chưa Thanh Toán".equals(selectedStatus) && !hd.isTrangThai())
                        || ("Đã Thanh Toán".equals(selectedStatus) && hd.isTrangThai())) {
                    Object[] row = {
                        stt++,
                        hd.getMaHD(),
                        hd.getNguoiTao(),
                        hd.getNgayTao(),
                        hd.getPhiShip(),
                        hd.getMaVoucher(),
                        hd.getTongTien(),
                        hd.getHinhThucThanhToan(),
                        hd.getTenKH(),
                        hd.getDiaChi(),
                        hd.getSDT(),
                        (hd.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán")
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }

    void fillTableByHinhThucThanhToan() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            int stt = 1;
            String selectedHinhThuc = (httt.getSelectedItem() != null) ? httt.getSelectedItem().toString() : "Tất Cả";

            List<Model_HinhThucThanhToan> list = servicehttt.selectHoaDonHInhThucTT(selectedHinhThuc);

            for (Model_HinhThucThanhToan hd : list) {
                // Kiểm tra giá trị của getHTThanhToan() trước khi sử dụng
                String hinhThucThanhToan = hd.getHTThanhToan();

                if (hinhThucThanhToan != null) {
                    // Kiểm tra hình thức thanh toán được chọn từ ComboBox
                    if (("Tất Cả".equals(selectedHinhThuc))
                            || ("Tiền Mặt".equals(selectedHinhThuc) && !hd.isTrangThai())
                            || ("Chuyển Khoản".equals(selectedHinhThuc) && hd.isTrangThai())) {
                        Object[] row = {
                            stt++,
                            hd.getMaHD(),
                            hd.getNguoiTao(),
                            hd.getNgayTao(),
                            hd.getPhiShip(),
                            hd.getMaVoucher(),
                            hd.getTongTien(),
                            hinhThucThanhToan, // Sử dụng giá trị đã kiểm tra
                            hd.getTenKH(),
                            hd.getDiaChi(),
                            hd.getSDT(),
                            (hd.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán")
                        };
                        model.addRow(row);
                    }
                } else {
                    // Xử lý trường hợp giá trị null (nếu cần thiết)
                    // Ví dụ: Gán một giá trị mặc định hoặc bỏ qua dòng này
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }
    
      private BigDecimal tinhToanThamSoTrenDong(int selectedRow) {
        BigDecimal giaTriCotCanTinh = (BigDecimal) table.getValueAt(selectedRow, 6); 
        return giaTriCotCanTinh;
    }
 void fillTableByTongTien() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            int stt = 1;
            String selectedHinhThuc = (httt.getSelectedItem() != null) ? httt.getSelectedItem().toString() : "Tất Cả";

            List<Model_HinhThucThanhToan> list = servicehttt.selectHoaDonHInhThucTT(selectedHinhThuc);

            for (Model_HinhThucThanhToan hd : list) {
                // Kiểm tra giá trị của getHTThanhToan() trước khi sử dụng
                String hinhThucThanhToan = hd.getHTThanhToan();

                if (hinhThucThanhToan != null) {
                    // Kiểm tra hình thức thanh toán được chọn từ ComboBox
                    if (("Tất Cả".equals(selectedHinhThuc))
                            || ("Tiền Mặt".equals(selectedHinhThuc) && !hd.isTrangThai())
                            || ("Chuyển Khoản".equals(selectedHinhThuc) && hd.isTrangThai())) {
                        Object[] row = {
                            stt++,
                            hd.getMaHD(),
                            hd.getNguoiTao(),
                            hd.getNgayTao(),
                            hd.getPhiShip(),
                            hd.getMaVoucher(),
                            hd.getTongTien(),
                            hinhThucThanhToan, // Sử dụng giá trị đã kiểm tra
                            hd.getTenKH(),
                            hd.getDiaChi(),
                            hd.getSDT(),
                            (hd.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán")
                        };
                        model.addRow(row);
                    }
                } else {
                    // Xử lý trường hợp giá trị null (nếu cần thiết)
                    // Ví dụ: Gán một giá trị mặc định hoặc bỏ qua dòng này
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }
    void filltableLichSuHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tableLS.getModel();
        model.setRowCount(0);
        try {
            List<Model_lichSuHoaDon> list = serviceLS.selectALllLichSuHoaDon();
            int stt = 1;
            for (Model_lichSuHoaDon hd : list) {

                Object[] row = {
                    stt++,
                    hd.getMaHD(),
                    hd.getNgaytao(),
                    hd.getNguoitao(),
                    hd.getTenKH(),
                    hd.getPhiShip(),
                    hd.getHinhThuc(),
                    hd.getTongtien(),
                    hd.getMaVoucher(),
                    hd.getNgaySua(),
                    hd.getNguoiSua(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }

    void fillNgayTaoHoaDon() {
        JDateChooser ngayTHD = NgayTHD;  // Chắc chắn rằng bạn đã khai báo NgayTHD

        if (ngayTHD != null && ngayTHD.getDate() != null) {
            SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
            String date = sDate.format(ngayTHD.getDate());

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
            table.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(date.trim()));
        } else {
            // Xử lý khi NgayTHD hoặc giá trị ngày là null
            System.out.println("NgayTHD hoặc giá trị ngày là null");
        }
    }

    void filltableHoaDonChiTiet(int Row) {
        DefaultTableModel model = (DefaultTableModel) tableHDCT.getModel();
        model.setRowCount(0);

        try {
            String MAHD = String.valueOf(table.getValueAt(Row, 1));
            Model_HoaDonChiTiet HDCT = service.selectALllHoaDonChiTietByID(MAHD);

            int stt = 1;
            Object[] row = {
                stt++,
                HDCT.getMaCTSP(),
                HDCT.getTenSP(),
                HDCT.getNhanHieu(),
                HDCT.getMau(),
                HDCT.getSize(),
                HDCT.getSoLuong(),
                HDCT.getDonGia(),
                HDCT.getTongTien(),};
            model.addRow(row);
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }

   void filltableLichSuHoaDonChiTiet(int Row) {
    DefaultTableModel model = (DefaultTableModel) tableLS.getModel();
    if (Row >= 0 && Row < tableLS.getRowCount()) {
        String MAHD = String.valueOf(tableLS.getValueAt(Row, 1));

        try {
            Model_LichSuHoaDonChiTiet LSHD = serviceLS.selectLichSuHoaDonChiTietByID(MAHD);

            // Phần còn lại của mã của bạn để điền chi tiết sử dụng LSHD
            time.setText(LSHD.getNgaytao().toString());
            ht.setText(LSHD.getHinhThucTT());
            ttThanhToan.setText(LSHD.isTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán");
            ma.setText(LSHD.getMaHD());
            DiaChi.setText(LSHD.getDiaChi());
            NV.setText(LSHD.getNhanVien());
            ghiChu.setText(LSHD.getGhiChu());
            KH.setText(LSHD.getTenKH());
            tongTien.setText(LSHD.getTongtien().toString());
            PhiShip.setText(LSHD.getPhiShip().toString());
            ngaysua.setText(LSHD.getNgaySua().toString());
            nguoiSua.setText(LSHD.getNguoiSua());
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    } else {
        MsgBox.alert(this, "Dòng được chọn không hợp lệ");
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        tiemKiem = new javax.swing.JTextField();
        httt = new javax.swing.JComboBox<>();
        tt = new javax.swing.JComboBox<>();
        NgayTHD = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        spTable6 = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        jPanel6 = new javax.swing.JPanel();
        spTable3 = new javax.swing.JScrollPane();
        tableHDCT = new com.raven.swing.Table();
        jButton6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        tkls = new javax.swing.JTextField();
        panelBorder5 = new com.raven.swing.PanelBorder();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ttThanhtoan = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DiaChi = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ghiChu = new javax.swing.JTextArea();
        time = new javax.swing.JTextField();
        ht = new javax.swing.JTextField();
        ttThanhToan = new javax.swing.JTextField();
        ma = new javax.swing.JTextField();
        tongTien = new javax.swing.JTextField();
        PhiShip = new javax.swing.JTextField();
        KH = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        NV = new javax.swing.JTextField();
        ngaysua = new javax.swing.JTextField();
        nguoiSua = new javax.swing.JTextField();
        spTable4 = new javax.swing.JScrollPane();
        tableLS = new com.raven.swing.Table();

        setBackground(new java.awt.Color(0, 204, 255));
        setPreferredSize(new java.awt.Dimension(945, 675));

        jTabbedPane1.setBackground(new java.awt.Color(0, 204, 255));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));

        jPanel5.setBackground(new java.awt.Color(0, 204, 255));

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Thời Gian Tạo Hóa Đơn:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Trạng Thái Thanh Toán");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Hình Thức Thanh Toán");

        jButton2.setBackground(new java.awt.Color(36, 77, 197));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Tìm Kiếm Hóa Đơn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tiemKiem.setBackground(new java.awt.Color(204, 204, 204));
        tiemKiem.setForeground(new java.awt.Color(51, 51, 51));
        tiemKiem.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        tiemKiem.setToolTipText("");
        tiemKiem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tiemKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tiemKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tiemKiemFocusLost(evt);
            }
        });
        tiemKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tiemKiemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tiemKiemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tiemKiemMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tiemKiemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tiemKiemMouseReleased(evt);
            }
        });
        tiemKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiemKiemActionPerformed(evt);
            }
        });

        httt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                htttItemStateChanged(evt);
            }
        });

        tt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả" }));
        tt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ttItemStateChanged(evt);
            }
        });
        tt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ttMouseClicked(evt);
            }
        });
        tt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttActionPerformed(evt);
            }
        });

        NgayTHD.setToolTipText("");
        NgayTHD.setDateFormatString("yyyy-MM-d");
        NgayTHD.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                NgayTHDAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        NgayTHD.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                NgayTHDMouseMoved(evt);
            }
        });
        NgayTHD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NgayTHDFocusLost(evt);
            }
        });
        NgayTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NgayTHDMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NgayTHDMouseExited(evt);
            }
        });
        NgayTHD.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                NgayTHDPropertyChange(evt);
            }
        });
        NgayTHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NgayTHDKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tạo Hóa Đơn");

        jButton4.setBackground(new java.awt.Color(51, 51, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Xuất Danh Sách");

        jButton5.setBackground(new java.awt.Color(255, 102, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Hủy");

        spTable6.setBorder(null);

        table.setBackground(new java.awt.Color(153, 204, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Mã NV", "Tên KH", "SDT", "Địa Chỉ", "Tổng Tiền", "Loại HĐ", "Ngày TT", "HTThanhToán", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        spTable6.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(6).setMinWidth(40);
        }

        jPanel6.setBackground(new java.awt.Color(0, 153, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        spTable3.setBorder(null);

        tableHDCT.setBackground(new java.awt.Color(0, 153, 255));
        tableHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Chi Tiết Sản Phẩm", "Tên SPCT", "Nhãn Hiệu", "Màu", "Size", "Số Lượng", "Đơn Giá", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable3.setViewportView(tableHDCT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(spTable3, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(spTable3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton6.setBackground(new java.awt.Color(51, 51, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("In Hóa Đơn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6)
                                    .addComponent(NgayTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)
                        .addGap(56, 56, 56)))
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(httt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(tiemKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, Short.MAX_VALUE)
                    .addComponent(spTable6, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(httt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(NgayTHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton5)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tiemKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4)
                                .addComponent(jButton6)))
                        .addGap(20, 20, 20)))
                .addComponent(spTable6, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        NgayTHD.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel5);

        jPanel8.setBackground(new java.awt.Color(51, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setBackground(new java.awt.Color(36, 77, 197));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tìm Kiếm Hóa Đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tkls.setBackground(new java.awt.Color(204, 204, 204));
        tkls.setForeground(new java.awt.Color(51, 51, 51));
        tkls.setText("Tìm Kiếm");
        tkls.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tkls.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tklsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tklsFocusLost(evt);
            }
        });
        tkls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tklsActionPerformed(evt);
            }
        });

        panelBorder5.setBackground(new java.awt.Color(0, 153, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Thời Gian Tạo Hóa Đơn:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Hình Thức Thanh Toán");

        ttThanhtoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ttThanhtoan.setForeground(new java.awt.Color(102, 102, 102));
        ttThanhtoan.setText("Trạng Thái Thanh Toán");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Mã Hóa Đơn");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Nhân Viên");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Tổng Tiền");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Phí Ship");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Khách Hàng");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Ghi Chú");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Địa Chỉ");

        DiaChi.setBackground(new java.awt.Color(0, 153, 255));
        DiaChi.setColumns(20);
        DiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DiaChi.setForeground(new java.awt.Color(255, 255, 255));
        DiaChi.setRows(5);
        jScrollPane1.setViewportView(DiaChi);

        ghiChu.setBackground(new java.awt.Color(0, 153, 255));
        ghiChu.setColumns(20);
        ghiChu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ghiChu.setForeground(new java.awt.Color(255, 255, 255));
        ghiChu.setRows(5);
        jScrollPane2.setViewportView(ghiChu);

        time.setBackground(new java.awt.Color(0, 153, 255));
        time.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setBorder(null);

        ht.setBackground(new java.awt.Color(0, 153, 255));
        ht.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ht.setForeground(new java.awt.Color(255, 255, 255));
        ht.setBorder(null);

        ttThanhToan.setBackground(new java.awt.Color(0, 153, 255));
        ttThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ttThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        ttThanhToan.setBorder(null);

        ma.setBackground(new java.awt.Color(0, 153, 255));
        ma.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ma.setForeground(new java.awt.Color(255, 255, 255));
        ma.setBorder(null);

        tongTien.setBackground(new java.awt.Color(0, 153, 255));
        tongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tongTien.setForeground(new java.awt.Color(255, 255, 255));
        tongTien.setBorder(null);
        tongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tongTienActionPerformed(evt);
            }
        });

        PhiShip.setBackground(new java.awt.Color(0, 153, 255));
        PhiShip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PhiShip.setForeground(new java.awt.Color(255, 255, 255));
        PhiShip.setBorder(null);

        KH.setBackground(new java.awt.Color(0, 153, 255));
        KH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        KH.setForeground(new java.awt.Color(255, 255, 255));
        KH.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Ngày Sửa");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Người Sửa");

        NV.setBackground(new java.awt.Color(0, 153, 255));
        NV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NV.setForeground(new java.awt.Color(255, 255, 255));
        NV.setBorder(null);
        NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NVActionPerformed(evt);
            }
        });

        ngaysua.setBackground(new java.awt.Color(0, 153, 255));
        ngaysua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ngaysua.setForeground(new java.awt.Color(255, 255, 255));
        ngaysua.setBorder(null);
        ngaysua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngaysuaActionPerformed(evt);
            }
        });

        nguoiSua.setBackground(new java.awt.Color(0, 153, 255));
        nguoiSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nguoiSua.setForeground(new java.awt.Color(255, 255, 255));
        nguoiSua.setBorder(null);
        nguoiSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nguoiSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ttThanhtoan)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ma, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ttThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(jLabel25))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(PhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder5Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(18, 18, 18)
                                    .addComponent(KH, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngaysua, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(PhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(ngaysua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttThanhtoan)
                    .addComponent(jLabel24)
                    .addComponent(ttThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel26)
                    .addComponent(ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(nguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 31, Short.MAX_VALUE))
        );

        spTable4.setBorder(null);

        tableLS.setBackground(new java.awt.Color(153, 204, 255));
        tableLS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Người Tạo", "Tên KH", "Phí Ship", "Hình Thức Thanh Toán", "Tổng Tiền ", "Mã Voucher", "Ngày Sửa", "Người Sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLSMouseClicked(evt);
            }
        });
        spTable4.setViewportView(tableLS);
        if (tableLS.getColumnModel().getColumnCount() > 0) {
            tableLS.getColumnModel().getColumn(6).setMinWidth(40);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tkls, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton3)
                        .addGap(71, 71, 71))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(spTable4)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tkls, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(spTable4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch Sử HĐ", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tiemKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiemKiemActionPerformed

    }//GEN-LAST:event_tiemKiemActionPerformed

    private void tklsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tklsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tklsActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        if (row >= 0) {
            this.filltableHoaDonChiTiet(row);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        int banGhi = table.getSelectedRow();
        String keyword = tiemKiem.getText();
        if (keyword.length() == 0) {
            sorter.setRowFilter(null);
            JOptionPane.showMessageDialog(null, "vui lòng tìm lại");
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i).*" + keyword + ".*"));

            int rowCount = table.getRowCount();
            if (rowCount > 0) {

            } else {
                JOptionPane.showMessageDialog(null, " Thông Tin Không Tồn Tại ");

            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void NgayTHDAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_NgayTHDAncestorAdded

    }//GEN-LAST:event_NgayTHDAncestorAdded

    private void ttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttActionPerformed


    }//GEN-LAST:event_ttActionPerformed

    private void tongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tongTienActionPerformed

    private void NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NVActionPerformed

    private void ngaysuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngaysuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngaysuaActionPerformed

    private void nguoiSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nguoiSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nguoiSuaActionPerformed


    private void tableLSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLSMouseClicked
      int row = tableLS.getSelectedRow();
        if (row >= 0) {
            this.filltableLichSuHoaDonChiTiet(row);
        }


    }//GEN-LAST:event_tableLSMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableLS.getModel());
        tableLS.setRowSorter(sorter);
        int banGhi = tableLS.getSelectedRow();
        String keyword = tkls.getText();
        if (keyword.length() == 0) {
            sorter.setRowFilter(null);
            JOptionPane.showMessageDialog(null, "vui lòng tìm lại");
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i).*" + keyword + ".*"));

            int rowCount = table.getRowCount();
            if (rowCount > 0) {

            } else {
                JOptionPane.showMessageDialog(null, " Thông Tin Không Tồn Tại ");

            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void tiemKiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiemKiemMouseEntered

    }//GEN-LAST:event_tiemKiemMouseEntered

    private void tiemKiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiemKiemMouseExited
//        if (tiemKiem.getText().isEmpty()) {
//            tiemKiem.setText("Tìm Kiếm");
//
//        }
    }//GEN-LAST:event_tiemKiemMouseExited

    private void tiemKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiemKiemMousePressed

    }//GEN-LAST:event_tiemKiemMousePressed

    private void tiemKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiemKiemMouseClicked


    }//GEN-LAST:event_tiemKiemMouseClicked

    private void tiemKiemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiemKiemMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tiemKiemMouseReleased

    private void tiemKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tiemKiemFocusGained
        String tk = tiemKiem.getText();
        if (tk.equalsIgnoreCase("Tìm Kiếm Theo Mã,Tên Hóa Đơn......")) {
            tiemKiem.setText("");
        }
    }//GEN-LAST:event_tiemKiemFocusGained

    private void tiemKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tiemKiemFocusLost
        String tk = tiemKiem.getText();
        if (tk.equalsIgnoreCase("")) {
            tiemKiem.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        }
    }//GEN-LAST:event_tiemKiemFocusLost

    private void tklsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tklsFocusGained
        String tk = tkls.getText();
        if (tk.equalsIgnoreCase("Tìm Kiếm Theo Mã,Tên Hóa Đơn......")) {
            tkls.setText("");
        }
    }//GEN-LAST:event_tklsFocusGained

    private void tklsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tklsFocusLost
        String tk = tkls.getText();
        if (tk.equalsIgnoreCase("")) {
            tkls.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        }
    }//GEN-LAST:event_tklsFocusLost

    private void NgayTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NgayTHDMouseClicked
    }//GEN-LAST:event_NgayTHDMouseClicked

    private void NgayTHDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NgayTHDMouseExited

    }//GEN-LAST:event_NgayTHDMouseExited

    private void NgayTHDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NgayTHDFocusLost
    }//GEN-LAST:event_NgayTHDFocusLost

    private void NgayTHDMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NgayTHDMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_NgayTHDMouseMoved

    private void NgayTHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NgayTHDKeyReleased
    }//GEN-LAST:event_NgayTHDKeyReleased

    private void NgayTHDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_NgayTHDPropertyChange
        fillNgayTaoHoaDon();
          DefaultTableModel model = (DefaultTableModel) tableHDCT.getModel();
          model.setRowCount(0);

    }//GEN-LAST:event_NgayTHDPropertyChange

    private void ttMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttMouseClicked

    }//GEN-LAST:event_ttMouseClicked

    private void ttItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ttItemStateChanged

        String selectedStatus = tt.getSelectedItem().toString();

        if (selectedStatus.equals("Đã Thanh Toán") || selectedStatus.equals("Chưa Thanh Toán")) {
            fillTableByTrangThai();

        }
        if (selectedStatus.equals("Tất Cả")) {
            filltableHoaDon();
        }

    }//GEN-LAST:event_ttItemStateChanged

    private void htttItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_htttItemStateChanged
        String selectedStatus = httt.getSelectedItem().toString();
        if (selectedStatus.equals("Chuyển Khoản") || selectedStatus.equals("Tiền Mặt")) {
            fillTableByHinhThucThanhToan();

        }
        if (selectedStatus.equals("Tất Cả")) {
            filltableHoaDon();
        }
    }//GEN-LAST:event_htttItemStateChanged
//\n" +
//"Chưa Thanh Toán
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea DiaChi;
    private javax.swing.JTextField KH;
    private javax.swing.JTextField NV;
    private com.toedter.calendar.JDateChooser NgayTHD;
    private javax.swing.JTextField PhiShip;
    private javax.swing.JTextArea ghiChu;
    private javax.swing.JTextField ht;
    private javax.swing.JComboBox<String> httt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField ma;
    private javax.swing.JTextField ngaysua;
    private javax.swing.JTextField nguoiSua;
    private com.raven.swing.PanelBorder panelBorder5;
    private javax.swing.JScrollPane spTable3;
    private javax.swing.JScrollPane spTable4;
    private javax.swing.JScrollPane spTable6;
    private com.raven.swing.Table table;
    private com.raven.swing.Table tableHDCT;
    private com.raven.swing.Table tableLS;
    private javax.swing.JTextField tiemKiem;
    private javax.swing.JTextField time;
    private javax.swing.JTextField tkls;
    private javax.swing.JTextField tongTien;
    private javax.swing.JComboBox<String> tt;
    private javax.swing.JTextField ttThanhToan;
    private javax.swing.JLabel ttThanhtoan;
    // End of variables declaration//GEN-END:variables
}
