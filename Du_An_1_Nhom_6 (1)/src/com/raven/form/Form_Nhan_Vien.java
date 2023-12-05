/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import Utils.MsgBox;
import com.raven.Service.Genmatusinh;
import com.raven.Service.NhanVienService;
import com.raven.model.Model_Card;
import com.raven.model.Model_NhanVien;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAVEN
 */
public class Form_Nhan_Vien extends javax.swing.JPanel implements ThreadFactory, Runnable{

    private DefaultTableModel mol = new DefaultTableModel();
    private NhanVienService ser = new NhanVienService();

    /**
     * Creates new form Form_1
     */
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    public Form_Nhan_Vien() {
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        this.fillTable(ser.selectALll());
        this.initWebcam();
    }

    public void fillTable(List<Model_NhanVien> listNV) {
        mol = (DefaultTableModel) table.getModel();
        mol.setRowCount(0);
        for (int i = 0; i < listNV.size(); i++) {
            Model_NhanVien nv = listNV.get(i);

            Object[] data = new Object[]{i + 1, nv.getMaNhanVien(), nv.isChuVu() ? "Nhân viên" : "Quản lí", nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getCCCD(), nv.getSDT(), nv.getEmail(), nv.getDiaChi(), nv.isTrangThai() ? "Đang làm việc" : "Nghỉ làm"};
            mol.addRow(data);
        }
    }

    void showData(int index) {
        Model_NhanVien nv = ser.selectALll().get(index);
        txtmanv.setText(nv.getMaNhanVien());
        pwfmk.setText(nv.getMatKhau());
        if (nv.isChuVu() == true) {
            rdonv.setSelected(true);
        } else {
            rdoql.setSelected(true);
        }
        txtten.setText(nv.getHoTen());
        if (nv.isGioiTinh() == true) {
            rdonam.setSelected(true);
        } else {
            rdonu.setSelected(true);
        }
        txtsodt.setText(nv.getSDT());
        txtcccd.setText((nv.getCCCD()));
        txtemail.setText(nv.getEmail());
        txtdiachi.setText(nv.getDiaChi());
    }

    Model_NhanVien readForm() {
        Genmatusinh maTD = new Genmatusinh() {
            @Override
            public String maTuDong() {
                int gen = new Random().nextInt(1000000);
                return "NV" + gen;
            }
        };
        Model_NhanVien nv = new Model_NhanVien();
        String ma = maTD.maTuDong();
        String matkhau = pwfmk.getText().trim();
        boolean chucvu = rdonv.isSelected();
        String hoten = txtten.getText().trim();
        boolean gioitinh = rdonam.isSelected();
        String cccd = txtcccd.getText().trim();
        String sdt = txtsodt.getText().trim();
        String email = txtemail.getText().trim();
        String diachi = txtdiachi.getText().trim();
        return new Model_NhanVien(ma, matkhau, chucvu, hoten, gioitinh, cccd, sdt, email, diachi, true);
    }

    boolean check() {
        String MKpattern = "[a-zA-Z0-9]+";
        String phonePattern = "^[0-9]+$";
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String ten = txtten.getText();
        String cccd = txtcccd.getText();
        String sodienthoai = txtsodt.getText();
        String diachi = txtdiachi.getText();
        String email = txtemail.getText();
        String matkhau = pwfmk.getText();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng rỗng");
            return false;
        } else {
            try {
                if (ten.length() > 50) {
                    JOptionPane.showMessageDialog(this, "Tên khách hàng không được quá 50 kí tự");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "");
                return false;
            }
        }
        if (cccd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Căn cước công dân trống!!!");
            return false;
        } else {
            try {
                if (cccd.length() != 12) {
                    JOptionPane.showMessageDialog(this, "Căn cước công dân phải nằm trong khoảng độ dài bằng 12");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "");
                return false;
            }
        }
        if (sodienthoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại trống!!!");
            return false;
        } else {
            try {
                if (sodienthoai.length() != 10) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại phải nằm trong khoảng độ dài 10 số");
                    return false;
                } else if (!sodienthoai.matches(phonePattern)) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại phải là số!!!");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "");
                return false;
            }
        }
        if (diachi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ rỗng");
            return false;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email trống!!!");
            return false;
        } else {
            try {
                if (!email.matches(emailPattern)) {
                    JOptionPane.showMessageDialog(this, "Email không hợp lệ ");
                    return false;
                } else if (email.length() < 6 || email.length() > 40) {
                    JOptionPane.showMessageDialog(this, "Email phải nằm trong khoảng độ dài ký tự giữa 6 và 40");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "");
                return false;
            }
        }
        if (matkhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu trống!!!");
            return false;
        } else {
            try {
                if (!matkhau.matches(MKpattern)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu chứa ký tự đặc biệt");
                    return false;
                } else if (matkhau.length() < 7 || matkhau.length() > 12) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu phải nằm trong khoảng độ dài ký tự giữa 7 và 12");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "");
                return false;
            }
        }

        return true;
    }

    public void SendMail() {
        Model_NhanVien nv = readForm();
        final String username = "thanhphamq11@gmail.com";
        final String password = "pwzr eyiq cktn qufv";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("thanhphamq11@gmail.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtemail.getText()));
            mimeMessage.setSubject("Tạo tài khoản thành công của CỬA HÀNG BÁN ÁO BEE-SHIRT");
            mimeMessage.setText("Đây là tên tài khoản và mật khẩu của bạn: " + txtten.getText()
                    + "\n"
                    + "\n"
                    + "Tài Khoản: "
                    + txtmanv.getText()
                    + "\n" + "Mật khẩu: " + nv.getMatKhau()
                    + "\n" + "\n" + "\n" + "Sau khi nhận mật khẩu vui lòng đăng nhập và đổi mật khẩu theo ý của mình!!!!!"
            );

            // Gửi email
            Transport.send(mimeMessage);

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(this, "Failed to send email: " + ex.getMessage());
        }
    }



    
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
            closeWebcam();
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
        WebcamPN.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

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
                String[] cccd = result.getText().split("\\|");
                txtten.setText(cccd[2]);
                String gioiTinh = cccd[4];
                if (gioiTinh.equalsIgnoreCase("Nam")) {
                    rdonam.setSelected(true);
                } else {
                    rdonu.setSelected(false);
                }
                txtcccd.setText(cccd[0]);
                txtdiachi.setText(cccd[5]);
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
    
    
    
    void fillTableByTrangThai() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            int stt = 1;
            String selectedStatus = (cbotrangthai.getSelectedItem() != null) ? cbotrangthai.getSelectedItem().toString() : "Tất cả";

            // Chuyển đổi giá trị từ ComboBox thành boolean
            boolean trangThai = ("Đang làm việc".equals(selectedStatus));

            List<Model_NhanVien> list = ser.selectByTrangThai(trangThai);

            for (Model_NhanVien hd : list) {
                // Kiểm tra trạng thái được chọn từ ComboBox
                if (("Tất cả".equals(selectedStatus))
                        || ("Đang làm việc".equals(selectedStatus) && hd.isTrangThai())
                        || ("Nghỉ làm".equals(selectedStatus) && !hd.isTrangThai())) {
                    Object[] row = {
                        stt++,
                        hd.getMaNhanVien(),
                        hd.getMatKhau(),
                        (hd.isChuVu()?"Nhân viên":"Quản lí"),
                        hd.getHoTen(),
                        (hd.isGioiTinh()? "Nam" : "Nữ"),
                        hd.getCCCD(),
                        hd.getSDT(),
                        hd.getEmail(),
                        hd.getDiaChi(),
                        (hd.isTrangThai() ? "Đang làm việc" : "Nghỉ làm")
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }
    void fillTableByGioiTinh() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            int stt = 1;
            String selectedStatus = (cbogioitinh.getSelectedItem() != null) ? cbogioitinh.getSelectedItem().toString() : "Tất cả";

            // Chuyển đổi giá trị từ ComboBox thành boolean
            boolean gioiTinh = ("Nam".equals(selectedStatus));

            List<Model_NhanVien> list = ser.selectByGioiTinh(gioiTinh);

            for (Model_NhanVien hd : list) {
                // Kiểm tra trạng thái được chọn từ ComboBox
                if (("Tất cả".equals(selectedStatus))
                        || ("Nam".equals(selectedStatus) && hd.isGioiTinh())
                        || ("Nữ".equals(selectedStatus) && !hd.isGioiTinh())) {
                    Object[] row = {
                        stt++,
                        hd.getMaNhanVien(),
                        hd.getMatKhau(),
                        (hd.isChuVu()?"Nhân viên":"Quản lí"),
                        hd.getHoTen(),
                        (hd.isGioiTinh()? "Nam" : "Nữ"),
                        hd.getCCCD(),
                        hd.getSDT(),
                        hd.getEmail(),
                        hd.getDiaChi(),
                        (hd.isTrangThai() ? "Đang làm việc" : "Nghỉ làm")
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }
    void fillTableByChucVu() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            int stt = 1;
            String selectedStatus = (cbochucvu.getSelectedItem() != null) ? cbochucvu.getSelectedItem().toString() : "Tất cả";

            // Chuyển đổi giá trị từ ComboBox thành boolean
            boolean chucVu = ("Nhân viên".equals(selectedStatus));

            List<Model_NhanVien> list = ser.selectByChucVu(chucVu);

            for (Model_NhanVien hd : list) {
                // Kiểm tra trạng thái được chọn từ ComboBox
                if (("Tất cả".equals(selectedStatus))
                        || ("Nhân viên".equals(selectedStatus) && hd.isChuVu())
                        || ("Quản lí".equals(selectedStatus) && !hd.isChuVu())) {
                    Object[] row = {
                        stt++,
                        hd.getMaNhanVien(),
                        hd.getMatKhau(),
                        (hd.isChuVu()?"Nhân viên":"Quản lí"),
                        hd.getHoTen(),
                        (hd.isGioiTinh()? "Nam" : "Nữ"),
                        hd.getCCCD(),
                        hd.getSDT(),
                        hd.getEmail(),
                        hd.getDiaChi(),
                        (hd.isTrangThai() ? "Đang làm việc" : "Nghỉ làm")
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtten = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsodt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcccd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdonv = new javax.swing.JRadioButton();
        rdoql = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pwfmk = new javax.swing.JPasswordField();
        WebcamPN = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbogioitinh = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbochucvu = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbotrangthai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        txtfind = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(915, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 102, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã NV");

        txtmanv.setEnabled(false);

        jLabel2.setText("Giới tính");

        buttonGroup2.add(rdonam);
        rdonam.setSelected(true);
        rdonam.setText("Nam");

        buttonGroup2.add(rdonu);
        rdonu.setText("Nữ");

        jLabel3.setText("Tên NV");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("CCCD");

        jLabel7.setText("Chức vụ");

        buttonGroup1.add(rdonv);
        rdonv.setSelected(true);
        rdonv.setText("Nhân viên");

        buttonGroup1.add(rdoql);
        rdoql.setText("Quản lí");

        jLabel9.setText("Email");

        jLabel10.setText("Mật khẩu");

        pwfmk.setText("jPasswordField1");

        WebcamPN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        WebcamPN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setBackground(new java.awt.Color(102, 102, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Tắt WebCam");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 102, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Bật WebCam");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel2)))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdonam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdonu))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtsodt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(rdonv)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rdoql))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(pwfmk, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcccd, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(WebcamPN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(88, 88, 88))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtcccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(rdonv)
                    .addComponent(rdoql))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rdonam)
                    .addComponent(rdonu)
                    .addComponent(jLabel9)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtsodt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(pwfmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(WebcamPN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Giới tính:");

        cbogioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ" }));
        cbogioitinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbogioitinhItemStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Chức vụ:");

        cbochucvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nhân viên", "Quản lí" }));
        cbochucvu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbochucvuItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Trạng thái:");

        cbotrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang làm việc", "Nghỉ làm" }));
        cbotrangthai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbotrangthaiItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbogioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbochucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbogioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbochucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        txtfind.setBackground(new java.awt.Color(204, 204, 204));
        txtfind.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtfind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfindKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tìm kiếm theo Mã NV, Họ Tên, CCCD, Số ĐT, Email, Địa Chỉ:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtfind, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Chức vụ", "Họ tên", "Giới tính", "CCCD", "Số ĐT", "Email", "Địa chỉ", "Trạng thái"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đang làm việc", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (check()) {
            int index = table.getSelectedRow();
            Model_NhanVien nv = readForm();
            if (index >= 0) {
                int choice;
                choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (ser.selectById(nv.getMaNhanVien()) != null) {
                        JOptionPane.showMessageDialog(this, "Mã trùng");
                    } else {
                        try {
                            ser.insert(nv);
                            JOptionPane.showMessageDialog(this, "Thêm thành công");
                            SendMail();
                            fillTable(ser.selectALll());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Thêm thất bại");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int index = table.getSelectedRow();
        this.showData(index);
    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int index = table.getSelectedRow();
        Model_NhanVien nv = readForm();
        if (index >= 0) {
            int choice;
            choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    ser.update(nv);
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    fillTable(ser.selectALll());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int index = table.getSelectedRow();
        String MaNhanVien = table.getValueAt(index, 1).toString();
        if (index >= 0) {
            int choice;
            choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    ser.delete(MaNhanVien);
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    fillTable(ser.selectALll());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.reset();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtfindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfindKeyReleased
        // TODO add your handling code here:
         mol = (DefaultTableModel) table.getModel();
        mol.setRowCount(0);
        int counter = 1;
        String keyword = txtfind.getText();
        List<Model_NhanVien> listNV = ser.Find(keyword, keyword, true, keyword, true, keyword, keyword, keyword, keyword, true);
        for (Model_NhanVien nv : listNV) {
            Object[] rows = {counter, nv.getMaNhanVien(), nv.getMatKhau(), nv.isChuVu() ? "Nhân viên" : "Quản lí", nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getCCCD(), nv.getSDT(), nv.getEmail(), nv.getDiaChi(), nv.isTrangThai() ? "Đang làm việc" : "Nghỉ làm"};
            mol.addRow(rows);
            counter++;
        }
    }//GEN-LAST:event_txtfindKeyReleased

    private void cbotrangthaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbotrangthaiItemStateChanged
        // TODO add your handling code here:
        String selectedStatus = cbotrangthai.getSelectedItem().toString();

        if (selectedStatus.equals("Đang làm việc") || selectedStatus.equals("Nghỉ làm")) {
            fillTableByTrangThai();

        }
        if (selectedStatus.equals("Tất cả")) {
            fillTable(ser.selectALll());
        }
    }//GEN-LAST:event_cbotrangthaiItemStateChanged

    private void cbogioitinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbogioitinhItemStateChanged
        // TODO add your handling code here:
        String selectedStatus = cbogioitinh.getSelectedItem().toString();

        if (selectedStatus.equals("Nam") || selectedStatus.equals("Nữ")) {
            fillTableByGioiTinh();

        }
        if (selectedStatus.equals("Tất cả")) {
            fillTable(ser.selectALll());
        }
    }//GEN-LAST:event_cbogioitinhItemStateChanged

    private void cbochucvuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbochucvuItemStateChanged
        // TODO add your handling code here:
        String selectedStatus = cbochucvu.getSelectedItem().toString();

        if (selectedStatus.equals("Nhân viên") || selectedStatus.equals("Quản lí")) {
            fillTableByChucVu();

        }
        if (selectedStatus.equals("Tất cả")) {
            fillTable(ser.selectALll());
        }
    }//GEN-LAST:event_cbochucvuItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
closeWebcam();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        openWebCam();
    }//GEN-LAST:event_jButton6ActionPerformed
    private void reset() {
        txtmanv.setText("");
        txtten.setText("");
        txtsodt.setText("");
        txtcccd.setText("");
        txtdiachi.setText("");
        txtemail.setText("");
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel WebcamPN;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbochucvu;
    private javax.swing.JComboBox<String> cbogioitinh;
    private javax.swing.JComboBox<String> cbotrangthai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPasswordField pwfmk;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JRadioButton rdonv;
    private javax.swing.JRadioButton rdoql;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtcccd;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfind;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtsodt;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
