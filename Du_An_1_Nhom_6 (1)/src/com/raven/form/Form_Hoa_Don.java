package com.raven.form;

import Utils.MsgBox;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.Service.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.raven.model.*;
import com.raven.model.Model_HoaDonChiTiet;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.swing.JTable;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.poi.ss.usermodel.Name;

/**
 *
 * @author RAVEN
 */
public class Form_Hoa_Don extends javax.swing.JPanel implements Runnable, ThreadFactory {

//    private WebcamPanel panel = null;
//    private Webcam webcam = null;
//    private Executor executor = Executors.newSingleThreadExecutor(this);
    private HoaDonService service = new HoaDonService();
    private LichSuHoaDon_SerVice serviceLS = new LichSuHoaDon_SerVice();
    private HinhThucThanhToan_Service servicehttt = new HinhThucThanhToan_Service();
    private Trahang_SerVice servicetraHang = new Trahang_SerVice();
//    Web Cam1
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public Form_Hoa_Don() {
        initComponents();
//        initWebcam();
        initWebcam();
        filltableSanPhamFind();
        filltableHoaDon();
        fillComBotrangThai();
        fillComBoHinhThucThanhToan();
        search.setFocusable(true);

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
                String LHD = "";
                if (hd.isLoaiHoaDon()) {
                    LHD = "Bán Tại Quầy";
                }
                if (hd.isLoaiHoaDon() == false) {
                    LHD = "Đặt Hàng";
                }

                Object[] row = {
                    stt++,
                    hd.getMaHD(),
                    hd.getMaNV(),
                    hd.getTenKH(),
                    hd.getSDT(),
                    hd.getDiaChi(),
                    hd.getTongTien(),
                    LHD,
                    hd.getNgayThanhToan(),
                    hd.getHTThanhToan(),
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
                        hd.getMaNV(),
                        hd.getTenKH(),
                        hd.getSDT(),
                        hd.getDiaChi(),
                        hd.getTongTien(),
                        (hd.isLoaiHoaDon() ? "Bán Hàng Tại Quầy" : "Đặt Hàng"),
                        hd.getNgayThanhToan(),
                        hd.getHTThanhToan(),
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
                            hd.getMaNV(),
                            hd.getTenKH(),
                            hd.getSDT(),
                            hd.getDiaChi(),
                            hd.getTongTien(),
                            (hd.isLoaiHoaDon() ? "Bán Hàng Tại Quầy" : "Đặt Hàng"),
                            hinhThucThanhToan,
                            hd.getHTThanhToan(),
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

    void filltableLichSuHoaDon(int Row) {
        DefaultTableModel model = (DefaultTableModel) tableLS.getModel();
        model.setRowCount(0);

        try {
            String MAHD = String.valueOf(table.getValueAt(Row, 1));
            Model_lichSuHoaDon1 HDCT = serviceLS.selectLichSuHoaDonByID(MAHD);

            int stt = 1;
            String tt = "";
            if (HDCT.isTrangThai()) {
                tt = "Đã Thanh Toán";
            }
            if (HDCT.isTrangThai() == false) {
                tt = "Chưa Thanh Toán";
            }
            Object[] row = {
                stt++,
                HDCT.getNgayTaoHD(),
                HDCT.getNguoiTao(),
                tt

            };
            model.addRow(row);
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
            System.out.println("NgayTHD hoặc giá trị ngày là  null");
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
                HDCT.getSoLuong(),
                HDCT.getDonGia(),
                HDCT.getTongTien(),};
            model.addRow(row);
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }

//    void filltableLichSuHoaDonChiTiet(int Row) {
//        DefaultTableModel model = (DefaultTableModel) tableLS.getModel();
//        model.setRowCount(0);
//
//        try {
//            String MAHD = String.valueOf(table.getValueAt(Row, 1));
//            Model_lichSuHoaDon HDCT = serviceLS.selectLichSuHoaDonByID(MAHD);
//
//            int stt = 1;
//            Object[] row = {
//                stt++,
//                HDCT.getNgayTaoHD(),
//                HDCT.getNguoiTao(),
//                HDCT.isTrangThai()
//
//            };
//            model.addRow(row);
//        } catch (Exception e) {
//            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        httt = new javax.swing.JComboBox<>();
        tt = new javax.swing.JComboBox<>();
        NgayTHD = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        danhSach = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        spTable6 = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        panel6 = new javax.swing.JPanel();
        spTable3 = new javax.swing.JScrollPane();
        tableHDCT = new com.raven.swing.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        Bill = new javax.swing.JTextArea();
        InHD = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        spTable5 = new javax.swing.JScrollPane();
        tableLS = new com.raven.swing.Table();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        spTable4 = new javax.swing.JScrollPane();
        tableSP = new com.raven.swing.Table();
        QR = new javax.swing.JPanel();
        off = new javax.swing.JButton();
        on = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        spTable7 = new javax.swing.JScrollPane();
        TableHDct = new com.raven.swing.Table();
        jButton2 = new javax.swing.JButton();
        panelBorder5 = new com.raven.swing.PanelBorder();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
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
        ma = new javax.swing.JTextField();
        tongTien = new javax.swing.JTextField();
        PhiShip = new javax.swing.JTextField();
        KH = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        NV = new javax.swing.JTextField();
        NgayTT = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        hoaDon = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 204, 255));
        setPreferredSize(new java.awt.Dimension(945, 675));

        jTabbedPane1.setBackground(new java.awt.Color(0, 204, 255));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));

        jPanel5.setBackground(new java.awt.Color(0, 204, 255));

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Thời Gian Tạo Hóa Đơn:");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(19, 18, 150, 16);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Trạng Thái Thanh Toán");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(250, 20, 150, 16);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Hình Thức Thanh Toán");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(450, 20, 150, 16);

        search.setBackground(new java.awt.Color(204, 204, 204));
        search.setForeground(new java.awt.Color(51, 51, 51));
        search.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        search.setToolTipText("");
        search.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                searchMouseReleased(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        jPanel3.add(search);
        search.setBounds(670, 40, 320, 28);

        httt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                htttItemStateChanged(evt);
            }
        });
        jPanel3.add(httt);
        httt.setBounds(450, 40, 127, 22);

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
        jPanel3.add(tt);
        tt.setBounds(250, 40, 130, 22);

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
        jPanel3.add(NgayTHD);
        NgayTHD.setBounds(25, 40, 127, 22);
        NgayTHD.getAccessibleContext().setAccessibleName("");

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tạo Hóa Đơn");
        jPanel3.add(jButton1);
        jButton1.setBounds(1070, 30, 120, 50);

        danhSach.setBackground(new java.awt.Color(51, 51, 255));
        danhSach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        danhSach.setForeground(new java.awt.Color(255, 255, 255));
        danhSach.setText("Xuất Danh Sách");
        danhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                danhSachActionPerformed(evt);
            }
        });
        jPanel3.add(danhSach);
        danhSach.setBounds(120, 70, 140, 30);

        jButton5.setBackground(new java.awt.Color(255, 102, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Hủy");
        jPanel3.add(jButton5);
        jButton5.setBounds(580, 70, 80, 30);

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

        jPanel3.add(spTable6);
        spTable6.setBounds(10, 110, 1220, 233);

        panel6.setBackground(new java.awt.Color(0, 153, 255));
        panel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        spTable3.setBorder(null);

        tableHDCT.setBackground(new java.awt.Color(0, 153, 255));
        tableHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Chi Tiết SP", "Tên SPCT", "Số Lượng", "Đơn Giá", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHDCT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        spTable3.setViewportView(tableHDCT);

        Bill.setColumns(20);
        Bill.setRows(5);
        jScrollPane3.setViewportView(Bill);

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable3, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
            .addGroup(panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable3, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(panel6);
        panel6.setBounds(10, 350, 700, 240);

        InHD.setBackground(new java.awt.Color(51, 51, 255));
        InHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        InHD.setForeground(new java.awt.Color(255, 255, 255));
        InHD.setText("In Hóa Đơn");
        InHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InHDActionPerformed(evt);
            }
        });
        jPanel3.add(InHD);
        InHD.setBounds(370, 70, 93, 30);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch Sử Hóa Đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        spTable5.setBorder(null);

        tableLS.setBackground(new java.awt.Color(51, 153, 255));
        tableLS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày TT", "Người Tạo", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
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
        spTable5.setViewportView(tableLS);
        if (tableLS.getColumnModel().getColumnCount() > 0) {
            tableLS.getColumnModel().getColumn(1).setHeaderValue("Ngày Tạo");
            tableLS.getColumnModel().getColumn(2).setHeaderValue("Người Tạo");
            tableLS.getColumnModel().getColumn(3).setHeaderValue("Người Sửa");
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable5, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel1);
        jPanel1.setBounds(710, 350, 520, 240);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel5);

        jPanel8.setBackground(new java.awt.Color(51, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        spTable4.setBorder(null);

        tableSP.setBackground(new java.awt.Color(255, 255, 255));
        tableSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Thương Hiệu", "Kích Thước", "Đơn Giá", "Ngày Thanh Toán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSPMouseClicked(evt);
            }
        });
        spTable4.setViewportView(tableSP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable4, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        QR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QR Sản Phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        QR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        off.setBackground(new java.awt.Color(102, 102, 255));
        off.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        off.setForeground(new java.awt.Color(255, 255, 255));
        off.setText("Tắt WebCam");
        off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offActionPerformed(evt);
            }
        });

        on.setBackground(new java.awt.Color(102, 102, 255));
        on.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        on.setForeground(new java.awt.Color(255, 255, 255));
        on.setText("Bật WebCam");
        on.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onActionPerformed(evt);
            }
        });

        txtSearch.setBackground(new java.awt.Color(204, 204, 204));
        txtSearch.setForeground(new java.awt.Color(51, 51, 51));
        txtSearch.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        txtSearch.setToolTipText("");
        txtSearch.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtSearchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSearchMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtSearchMouseReleased(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        spTable7.setBorder(null);

        TableHDct.setBackground(new java.awt.Color(255, 255, 255));
        TableHDct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Mã Sản Phẩm", "Tên SP", "Đơn Giá", "Số Lượng", "Tổng Tiền", "Ngày Tạo HD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableHDct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableHDctMouseClicked(evt);
            }
        });
        TableHDct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableHDctKeyReleased(evt);
            }
        });
        spTable7.setViewportView(TableHDct);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable7)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable7, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ALL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(off)
                            .addComponent(on))
                        .addGap(74, 74, 74)
                        .addComponent(QR, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(off)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(on)
                            .addComponent(jButton2))
                        .addGap(19, 19, 19))
                    .addComponent(QR, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Thời Gian Tạo Hóa Đơn:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Hình Thức Thanh Toán");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Mã Sản Phẩm");

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

        DiaChi.setBackground(new java.awt.Color(204, 204, 204));
        DiaChi.setColumns(20);
        DiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DiaChi.setForeground(new java.awt.Color(255, 255, 255));
        DiaChi.setRows(5);
        jScrollPane1.setViewportView(DiaChi);

        ghiChu.setBackground(new java.awt.Color(204, 204, 204));
        ghiChu.setColumns(20);
        ghiChu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ghiChu.setForeground(new java.awt.Color(255, 255, 255));
        ghiChu.setRows(5);
        jScrollPane2.setViewportView(ghiChu);

        time.setBackground(new java.awt.Color(255, 255, 255));
        time.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        time.setForeground(new java.awt.Color(51, 51, 51));
        time.setBorder(null);

        ht.setBackground(new java.awt.Color(255, 255, 255));
        ht.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ht.setForeground(new java.awt.Color(51, 51, 51));
        ht.setBorder(null);

        ma.setBackground(new java.awt.Color(255, 255, 255));
        ma.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ma.setForeground(new java.awt.Color(51, 51, 51));
        ma.setBorder(null);

        tongTien.setBackground(new java.awt.Color(255, 255, 255));
        tongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tongTien.setForeground(new java.awt.Color(51, 51, 51));
        tongTien.setBorder(null);
        tongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tongTienActionPerformed(evt);
            }
        });

        PhiShip.setBackground(new java.awt.Color(255, 255, 255));
        PhiShip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PhiShip.setForeground(new java.awt.Color(51, 51, 51));
        PhiShip.setBorder(null);

        KH.setBackground(new java.awt.Color(255, 255, 255));
        KH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        KH.setForeground(new java.awt.Color(51, 51, 51));
        KH.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Ngày Thanh Toán");

        NV.setBackground(new java.awt.Color(255, 255, 255));
        NV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NV.setForeground(new java.awt.Color(51, 51, 51));
        NV.setBorder(null);
        NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NVActionPerformed(evt);
            }
        });

        NgayTT.setBackground(new java.awt.Color(255, 255, 255));
        NgayTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NgayTT.setForeground(new java.awt.Color(51, 51, 51));
        NgayTT.setBorder(null);
        NgayTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NgayTTActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Mã Hóa Đơn");

        hoaDon.setBackground(new java.awt.Color(255, 255, 255));
        hoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hoaDon.setForeground(new java.awt.Color(51, 51, 51));
        hoaDon.setBorder(null);
        hoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoaDonActionPerformed(evt);
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
                            .addComponent(jLabel21)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ma, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel18)
                            .addComponent(jLabel26)
                            .addComponent(jLabel19)
                            .addComponent(jLabel25)
                            .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel24))
                            .addComponent(jLabel22))
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NgayTT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(KH, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ht, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(27, 27, 27)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(ma, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(KH, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NgayTT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(43, 43, 43)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tìm Kiếm Đơn Hàng", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

    }//GEN-LAST:event_searchActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        if (row >= 0) {
            this.filltableHoaDonChiTiet(row);
            this.filltableLichSuHoaDon(row);
        }
    }//GEN-LAST:event_tableMouseClicked

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

    private void NgayTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NgayTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NgayTTActionPerformed


    private void tableSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSPMouseClicked


    }//GEN-LAST:event_tableSPMouseClicked

    private void searchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseEntered

    }//GEN-LAST:event_searchMouseEntered

    private void searchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseExited
//        if (tiemKiem.getText().isEmpty()) {
//            tiemKiem.setText("Tìm Kiếm");
//
//        }
    }//GEN-LAST:event_searchMouseExited

    private void searchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMousePressed

    }//GEN-LAST:event_searchMousePressed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked


    }//GEN-LAST:event_searchMouseClicked

    private void searchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchMouseReleased

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        String tk = search.getText();
        if (tk.equalsIgnoreCase("Tìm Kiếm Theo Mã,Tên Hóa Đơn......")) {
            search.setText("");
        }
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        String tk = search.getText();
        if (tk.equalsIgnoreCase("")) {
            search.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        }
    }//GEN-LAST:event_searchFocusLost

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
        DefaultTableModel model1 = (DefaultTableModel) tableLS.getModel();
        model1.setRowCount(0);

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

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed

    }//GEN-LAST:event_searchKeyPressed

    private void tableLSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableLSMouseClicked
// private void createQR(){
//     try {
//            
//            ByteArrayOutputStream out = QRCode.from(qr_text.getText())
//                    .to(ImageType.PNG).stream();
//            
//            String f_name = qr_text.getText();
//            String Path_name = "D:\\aaaaaaaaaa\\" ;
//            
//            FileOutputStream fout = new FileOutputStream(new File(Path_name +(f_name + ".PNG")));
//            fout.write(out.toByteArray());
//            fout.flush();
//            
//            
//            
//        } catch (Exception e) {
//            System.out.println(e);
//        }
// }
    private void InHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InHDActionPerformed

        String Path = "E:\\HoaDon";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            Path = j.getSelectedFile().getPath();
        }
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(Path + "\\HoaDon.pdf"));
            doc.open();

            // Thêm thông tin hoá đơn vào văn bản
            Paragraph bill = new Paragraph();
            bill.add("                                                                       HOA ĐON THANH TOAN BEE SHIRT \n");
            bill.add("                                                                         1Kieu Mai/ FPT Polytechnic, \n");
            bill.add("                                                                         Hottline: 0363652758, \n");
                 int selectedRow = table.getSelectedRow();
        DefaultTableModel cf = (DefaultTableModel) table.getModel();
        if (selectedRow != -1) {
            bill.add("                                                                         Ma Hoa Don: " + cf.getValueAt(selectedRow, 1) + "\n");
        }
            bill.add("                                     --------------------------------------------------------------------------------------------\n");
            bill.add("                                     --------------------------------------------------------------------------------------------\n");

            bill.add("                               STT         MaSP               Ten        soluong        don gia       tong tien\n");
            bill.add("                                                                \n");

            DefaultTableModel df = (DefaultTableModel) tableHDCT.getModel();
            for (int i = 0; i < tableHDCT.getRowCount(); i++) {
                bill.add("                               "
                        + df.getValueAt(i, 0) + "       "
                        + df.getValueAt(i, 1) + "          "
                        + df.getValueAt(i, 2) + "       "
                        + df.getValueAt(i, 3) + "       "
                        + df.getValueAt(i, 4) + "          "
                        + df.getValueAt(i, 5) + "         \n");
            }
            bill.add("                                                                \n");
            bill.add("                                                                \n");
            bill.add("                                                                \n");
            bill.add("                                                                \n");
            bill.add("                                    -------------------------------------------------------------------------------------------\n");
            for (int i = 0; i < tableHDCT.getRowCount(); i++) {
                bill.add("                                            " + "Tong Tien :" + df.getValueAt(i, 5) + "VND\n");
            }
            bill.add("                                               Tien Nhan :0 VND\n");
            bill.add("                                               Tien Thua :0 VND\n");
            bill.add("                                        =================================================\n");
            bill.add("                                                                   Thanks For Your Business...!" + "\n");
            bill.add("                                        =================================================\n");

            doc.add(bill);

            JOptionPane.showMessageDialog(null, "PDF generated");

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }


    }//GEN-LAST:event_InHDActionPerformed

    private void danhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_danhSachActionPerformed
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\admin\\OneDrive\\Documents");
        excelFileChooser.setDialogTitle("Save AS");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");

                // Tạo một CellStyle cho các ô in đậm
                CellStyle boldStyle = excelJTableExporter.createCellStyle();
                Font font = excelJTableExporter.createFont();
                font.setBold(true);
                boldStyle.setFont(font);
                String[] columnNames = {"STT", "Mã HĐ", "Mã Nhân Viên", "Tên KH", "SĐT", "Địa Chỉ", "Tổng Tiền", "Loại HĐ", "Ngày TT", "Hình Thức TT", "Trạng Thái"};
                XSSFRow headerRow = excelSheet.createRow(0);
                for (int j = 0; j < columnNames.length; j++) {
                    XSSFCell cell = headerRow.createCell(j);
                    cell.setCellValue(columnNames[j]);
                    cell.setCellStyle(boldStyle); // Áp dụng CellStyle cho ô in đậm
                }
                for (int i = 0; i < model.getRowCount(); i++) {
                    XSSFRow excelrow = excelSheet.createRow(i + 1);
                    for (int j = 0; j < columnNames.length; j++) {
                        XSSFCell excelCell = excelrow.createCell(j);
                        excelCell.setCellValue(model.getValueAt(i, j).toString());
                    }
                }
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Export Successfully");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Form_Hoa_Don.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Form_Hoa_Don.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelFOU != null) {
                        excelFOU.close();
                    }

                    if (excelJTableExporter != null) {
                        excelJTableExporter.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Form_Hoa_Don.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


    }//GEN-LAST:event_danhSachActionPerformed

    private void offActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offActionPerformed
        this.closeWebcam();
    }//GEN-LAST:event_offActionPerformed

    private void onActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onActionPerformed
        this.openWebCam();
    }//GEN-LAST:event_onActionPerformed

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        String tk = txtSearch.getText();
        if (tk.equalsIgnoreCase("Tìm Kiếm Theo Mã,Tên Hóa Đơn......")) {
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        String tk = txtSearch.getText();
        if (tk.equalsIgnoreCase("")) {
            txtSearch.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseEntered

    private void txtSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseExited

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMousePressed

    private void txtSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed

    }//GEN-LAST:event_txtSearchKeyPressed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        DefaultTableModel mol = (DefaultTableModel) table.getModel();
        mol.setRowCount(0);
        int counter = 1;
        String keyword = search.getText();
        Date NgayTT = null;
        BigDecimal tongTien = null;
        List<Model_Hoa_Don> listKH = service.Find(keyword, keyword, keyword, keyword, keyword, tongTien, true, NgayTT, keyword, true);
        for (Model_Hoa_Don kh : listKH) {
            Object[] rows = {counter, kh.getMaHD(), kh.getMaNV(), kh.getTenKH(), kh.getSDT(), kh.getDiaChi(), kh.getTongTien(), kh.isLoaiHoaDon() ? "Bán Tại Quầy" : "Đặt Hàng", kh.getNgayThanhToan(), kh.getHTThanhToan(), kh.isTrangThai() ? "Còn hoạt động" : "Không hoạt động"};
            mol.addRow(rows);
            counter++;
        }

    }//GEN-LAST:event_searchKeyReleased

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        DefaultTableModel mol = (DefaultTableModel) tableSP.getModel();
        mol.setRowCount(0);
        int counter = 1;
        String keyword = txtSearch.getText();
        BigDecimal tongTien = null;
        int soluong = 0;
        BigDecimal donGia = null;
        Date NgayTT = null;
        List<Model_FindSP> listKH = service.FindSP(keyword, keyword, keyword, donGia, soluong, tongTien, NgayTT);
        for (Model_FindSP kh : listKH) {
            Object[] rows = {counter, kh.getMaHD(), kh.getMaSp(), kh.getNameSP(), kh.getDonGia(), kh.getSoLuong(), kh.getTongTien(), kh.getNgayTT()};
            mol.addRow(rows);
            counter++;
        }
    }//GEN-LAST:event_txtSearchKeyReleased
    void filltableSanPhamFind() {
        DefaultTableModel model = (DefaultTableModel) TableHDct.getModel();
        model.setRowCount(0);
        try {
            List<Model_FindSP> list = servicetraHang.selectALllSanPhamFind();
            int stt = 1;

            for (Model_FindSP hd : list) {

                Object[] row = {
                    stt++,
                    hd.getMaHD(),
                    hd.getMaSp(),
                    hd.getNameSP(),
                    hd.getDonGia(),
                    hd.getSoLuong(),
                    hd.getTongTien(),
                    hd.getNgayTT(),
                    tt

                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }
    private void TableHDctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableHDctMouseClicked
        int row = TableHDct.getSelectedRow();
        if (row >= 0) {
            this.filltableTraHang(row);
            this.fillTextTraHang(row);
        }

    }//GEN-LAST:event_TableHDctMouseClicked

    private void TableHDctKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableHDctKeyReleased

    }//GEN-LAST:event_TableHDctKeyReleased

    private void hoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoaDonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        filltableSanPhamFind();
    }//GEN-LAST:event_jButton2ActionPerformed

    void fillTextTraHang(int row) {
        // Kiểm tra giá trị row
        if (row < 0 || row >= TableHDct.getRowCount()) {
            System.out.println("Invalid row index: " + row);
            return;
        }

        // Lấy giá trị tại index 1
        Object valueAtRow1 = TableHDct.getValueAt(row, 1);

        // Kiểm tra xem index 1 có hợp lệ không cho hàng cụ thể
        if (TableHDct.getColumnCount() <= 1 || valueAtRow1 == null) {
            System.out.println("Invalid column index 1 or null value at index 1 for the table.");
            return;
        }

        String MAHD = String.valueOf(valueAtRow1);

        try {
            // Lấy danh sách model_TexFilL từ servicetraHang
            List<model_TexFilL> list = servicetraHang.selectALllTextTraHang(MAHD);

            // Kiểm tra xem có đủ dữ liệu trong danh sách không
            if (list.size() > 0) {
                model_TexFilL hd = list.get(0); // Lấy mô hình đầu tiên từ danh sách

                // Đặt giá trị vào các thành phần giao diện
                hoaDon.setText(hd.getMaHD());
                time.setText(hd.getNgayTao().toString());
                ht.setText(hd.getHTThanhToan());
                ma.setText(hd.getMaSP());
                tongTien.setText(hd.getTongTien().toString());
                PhiShip.setText(hd.getPhiShip().toString());
                KH.setText(hd.getTenKH());
                DiaChi.setText(hd.getDiaChi());
                NV.setText(hd.getTenNV());
                NgayTT.setText(hd.getNgayTT().toString());
                ghiChu.setText(hd.getGhiChu());
            } else {
                System.out.println("No data found for MAHD: " + MAHD);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            System.out.println("Error while retrieving data: " + e.getMessage());
        }
    }

    void filltableTraHang(int Row) {
        DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
        model.setRowCount(0);

        try {
            String MASP = String.valueOf(TableHDct.getValueAt(Row, 2));
            Model_TraHang HDCT = servicetraHang.selectALllTraHangBYID(MASP);

            int stt = 1;
            Object[] row = {
                stt++,
                HDCT.getMaSPCT(),
                HDCT.getTenSPCT(),
                HDCT.getNhanHieu(),
                HDCT.getKichThuoc(),
                HDCT.getDonGia(),
                HDCT.getNgayTT(),};
            model.addRow(row);
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }

//    
    void filltableSanPhamFindByID(Model_FindSP row) {
        DefaultTableModel model = (DefaultTableModel) TableHDct.getModel();
        DefaultTableModel model1 = (DefaultTableModel) tableSP.getModel();
        model.setRowCount(0);

        try {
            String MA = String.valueOf(row.getMaHD()); // Sử dụng đối tượng row trực tiếp

            Model_FindSP hd = servicetraHang.selectHoaDonByQR(MA);
            int stt = 1;

            Object[] rowData = {
                stt++,
                hd.getMaHD(),
                hd.getMaSp(),
                hd.getNameSP(),
                hd.getDonGia(),
                hd.getSoLuong(),
                hd.getTongTien(),
                hd.getNgayTT(),
                tt
            };
            model.addRow(rowData);

        } catch (Exception e) {
            model1.setRowCount(0);
            hoaDon.setText("");
            time.setText("");
            ht.setText("");
            ma.setText("");
            tongTien.setText("");
            PhiShip.setText("");
            KH.setText("");
            DiaChi.setText("");
            NV.setText("");
            NgayTT.setText("");
            ghiChu.setText("");
            MsgBox.alert(this, "Ma QR Khong Hop Le");
        }
    }
// web cam

    private void initWebcam() {
        // Kiểm tra xem webcam có tồn tại không
        if (webcam == null) {
            List<Webcam> webcams = Webcam.getWebcams();
            if (webcams.isEmpty()) {
                System.out.println("No webcam found.");
                // Thực hiện xử lý tùy thuộc vào yêu cầu ứng dụng của bạn
                return;
            }

            webcam = webcams.get(0);
        }

        // Đóng webcam nếu nó đã mở
        if (webcam.isOpen()) {
            webcam.close();
        }

        // Thay đổi độ phân giải
        Dimension newSize = WebcamResolution.QVGA.getSize();
        webcam.setViewSize(newSize);

        // Mở lại webcam
        webcam.open();

        // Tiếp tục cấu hình các thuộc tính khác của webcam nếu cần
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(newSize);
        panel.setFPSDisplayed(true);
        QR.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 109, 114));

        // Bắt đầu thực hiện công việc của thread
        executor.execute(this);
    }

    private volatile boolean isRunning = true;

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                image = webcam.getImage();
                if (image == null) {
                    continue; // Nếu image là null, bỏ qua vòng lặp và thử lại
                }
            } else {
                continue; // Nếu webcam chưa mở, bỏ qua vòng lặp và thử lại
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                // No result...
            }

            if (result != null) {
                Model_FindSP Ma = servicetraHang.selectHoaDonByQR(result.getText());
                filltableSanPhamFindByID(Ma);
                return;
            }
            if (!isRunning) {
                break;
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    public void closeWebcam() {
        if (webcam.isOpen()) {
            webcam.close();
        }
    }

    private void openWebCam() {
        this.initWebcam();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Bill;
    private javax.swing.JTextArea DiaChi;
    private javax.swing.JButton InHD;
    private javax.swing.JTextField KH;
    private javax.swing.JTextField NV;
    private com.toedter.calendar.JDateChooser NgayTHD;
    private javax.swing.JTextField NgayTT;
    private javax.swing.JTextField PhiShip;
    private javax.swing.JPanel QR;
    private com.raven.swing.Table TableHDct;
    private javax.swing.JButton danhSach;
    private javax.swing.JTextArea ghiChu;
    private javax.swing.JTextField hoaDon;
    private javax.swing.JTextField ht;
    private javax.swing.JComboBox<String> httt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField ma;
    private javax.swing.JButton off;
    private javax.swing.JButton on;
    private javax.swing.JPanel panel6;
    private com.raven.swing.PanelBorder panelBorder5;
    private javax.swing.JTextField search;
    private javax.swing.JScrollPane spTable3;
    private javax.swing.JScrollPane spTable4;
    private javax.swing.JScrollPane spTable5;
    private javax.swing.JScrollPane spTable6;
    private javax.swing.JScrollPane spTable7;
    private com.raven.swing.Table table;
    private com.raven.swing.Table tableHDCT;
    private com.raven.swing.Table tableLS;
    private com.raven.swing.Table tableSP;
    private javax.swing.JTextField time;
    private javax.swing.JTextField tongTien;
    private javax.swing.JComboBox<String> tt;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
