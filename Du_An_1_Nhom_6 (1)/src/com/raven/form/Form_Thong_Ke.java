package com.raven.form;

import Utils.MsgBox;
import com.raven.Service.DoanhThu_Service;
import com.raven.Service.ThongKe_Service;
import com.raven.chart.ModelChart;
import com.raven.model.Model_Card;
import com.raven.model.Model_ThongKe;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Form_Thong_Ke extends javax.swing.JPanel {

    private int selectedTimeUnit = Calendar.DAY_OF_MONTH;
    private ThongKe_Service service = new ThongKe_Service();

    public Form_Thong_Ke() {
        initComponents();
        spaceTime();
        setTimeNgay();
        setTimeThang();
        setTimeNam();
        setDataToChartToDay(pnlDTTKCot);
        filltableThongKe();
    }

    private void setTimeNgay() {
        rdoNgay.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedTimeUnit = Calendar.DAY_OF_MONTH;
                    // Cập nhật biểu đồ khi chọn ngày
                    updateChart();
                }
            }
        });
    }

    private void setTimeThang() {
        rdoThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedTimeUnit = Calendar.MONTH;
                    // Cập nhật biểu đồ khi chọn tháng
                    updateChart();
                }
            }
        });
    }

    private void setTimeNam() {
        rdoNam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedTimeUnit = Calendar.YEAR;
                    // Cập nhật biểu đồ khi chọn năm
                    updateChart();
                }
            }
        });
    }

    private void updateChart() {
        setDataToChart1(pnlDTTKCot, selectedTimeUnit);
    }

//    private void unit() {
//        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")), "Số Hóa Đơn", "25"));
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Doanh Thu", "$25000000"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Số Khách", "30"));
//        card4.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Số Đơn Hủy", "3"));
//
//        setBackground(new Color(250, 250, 250));
////        chart.addLegend("Doanh Thu", new Color(245, 189, 135));
//        pnlDTTKCot.addLegend("Doanh Thu", new Color(135, 189, 245));
////        chart.addLegend("Profit", new Color(189, 135, 245));
////        chart.addLegend("Cost", new Color(139, 229, 222));
//        pnlDTTKCot.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
//        pnlDTTKCot.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
//        pnlDTTKCot.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
//        pnlDTTKCot.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
//        pnlDTTKCot.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
//        pnlDTTKCot.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
//        pnlDTTKCot.addData(new ModelChart("July", new double[]{190, 280, 81, 200}));
//        pnlDTTKCot.addData(new ModelChart("August", new double[]{190, 280, 81, 200}));
//        pnlDTTKCot.addData(new ModelChart("September", new double[]{190, 280, 81, 200}));
//        pnlDTTKCot.addData(new ModelChart("October", new double[]{190, 280, 81, 200}));
//        pnlDTTKCot.addData(new ModelChart("November", new double[]{190, 280, 81, 200}));
//        pnlDTTKCot.addData(new ModelChart("December", new double[]{190, 280, 81, 200}));
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel = new javax.swing.JLayeredPane();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();
        card4 = new com.raven.component.Card();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        pnlDTTKCot = new com.raven.chart.Chart();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThongKe = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        search = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jmonth = new com.toedter.calendar.JMonthChooser();
        rdoThang = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNgay = new javax.swing.JRadioButton();
        jDateCuoi = new com.toedter.calendar.JDateChooser();
        jDateDau = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 204, 255));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        card4.setColor1(new java.awt.Color(218, 75, 192));
        card4.setColor2(new java.awt.Color(171, 48, 89));
        panel.add(card4);

        jPanel4.setBackground(new java.awt.Color(102, 204, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống Kê", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDTTKCot, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlDTTKCot, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("BIểu Đồ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tableThongKe.setBackground(new java.awt.Color(153, 204, 255));
        tableThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Hãng ", "Màu Sắc", "Size", "Số Lượng", "Đơn Giá"
            }
        ));
        jScrollPane1.setViewportView(tableThongKe);

        jButton1.setBackground(new java.awt.Color(51, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Xuất Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        search.setBackground(new java.awt.Color(204, 204, 204));
        search.setForeground(new java.awt.Color(51, 51, 51));
        search.setText("Tìm Kiếm Theo Mã,Tên Hóa Đơn......");
        search.setToolTipText("");
        search.setBorder(null);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(324, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addGap(73, 73, 73))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hàng Hóa Bán Được", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("TIÊU CHÍ"));

        jmonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmonthMouseClicked(evt);
            }
        });
        jmonth.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jmonthPropertyChange(evt);
            }
        });

        rdoThang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoThang);
        rdoThang.setText("THÁNG");
        rdoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThangActionPerformed(evt);
            }
        });

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setText("NĂM");
        rdoNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNamMouseClicked(evt);
            }
        });

        rdoNgay.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNgay);
        rdoNgay.setText("NGÀY");

        jDateCuoi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateCuoiPropertyChange(evt);
            }
        });

        jDateDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateDauPropertyChange(evt);
            }
        });

        jLabel1.setText("NGÀY  ĐẦU");

        jLabel12.setText("NGÀY CUỐI");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(rdoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoNam)
                .addGap(82, 82, 82)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(135, 135, 135)
                        .addComponent(jLabel12))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jDateDau, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoThang)
                    .addComponent(rdoNam)
                    .addComponent(rdoNgay))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    void filltableThongKe() {
        DefaultTableModel model = (DefaultTableModel) tableThongKe.getModel();
        model.setRowCount(0);
        try {
            List<Model_ThongKe> list = service.selectALllThongKe();
            int stt = 1;

            for (Model_ThongKe hd : list) {
                Object[] row = {
                    stt++,
                    hd.getMaSP(),
                    hd.getTenSP(),
                    hd.getHang(),
                    hd.getMauSac(),
                    hd.getSize(),
                    hd.getSoLuong(),
                    hd.getDonGia(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }
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

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked

    }//GEN-LAST:event_searchMouseClicked

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

    private void searchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchMouseReleased

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed

    }//GEN-LAST:event_searchKeyPressed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
//        DefaultTableModel mol = (DefaultTableModel) table.getModel();
//        mol.setRowCount(0);
//        int counter = 1;
//        String keyword = search.getText();
//        Date NgayTT = null;
//        BigDecimal tongTien = null;
//        List<Model_Hoa_Don> listKH = service.Find(keyword, keyword, keyword, keyword, keyword, tongTien, true, NgayTT, keyword, true);
//        for (Model_Hoa_Don kh : listKH) {
//            Object[] rows = {counter, kh.getMaHD(), kh.getMaNV(), kh.getTenKH(), kh.getSDT(), kh.getDiaChi(), kh.getTongTien(), kh.isLoaiHoaDon() ? "Bán Tại Quầy" : "Đặt Hàng", kh.getNgayThanhToan(), kh.getHTThanhToan(), kh.isTrangThai() ? "Còn hoạt động" : "Không hoạt động"};
//            mol.addRow(rows);
//            counter++;
//        }
    }//GEN-LAST:event_searchKeyReleased
    public void exportTable(JTable table) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            File saveFile = fileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Thống kê");
                Row headerRow = sheet.createRow(0);
                // Xuất tiêu đề cột
                for (int i = 0; i < table.getColumnCount(); i++) {
                    String columnName = table.getColumnName(i);
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columnName);

                }
                // Xuất dữ liệu từng dòng
                for (int i = 0; i < table.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        Object value = table.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
                FileOutputStream outputStream = new FileOutputStream(new File(saveFile.toString()));
                workbook.write(outputStream);
                workbook.close();
                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công");
            } else {
                //JOptionPane.showMessageDialog(this, "Xuất thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportTable(tableThongKe);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jmonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmonthMouseClicked

    }//GEN-LAST:event_jmonthMouseClicked

    private void spaceTime() {
        jDateCuoi.addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                updateChartFromDateChoosers();
            }
        });
    }

    private void updateChartFromDateChoosers() {
        Date startDate = jDateDau.getDate();
        Date endDate = jDateCuoi.getDate();

        if (startDate != null && endDate != null) {
            // Truyền các giá trị startDate và endDate cho phương thức cập nhật biểu đồ
            updateChart(startDate, endDate);
        }
    }

    private void updateChart(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        // Lấy thông tin ngày, tháng, năm từ startDate và endDate
        int startYear = startCalendar.get(Calendar.YEAR);
        int startMonth = startCalendar.get(Calendar.MONTH) + 1;
        int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);

        int endYear = endCalendar.get(Calendar.YEAR);
        int endMonth = endCalendar.get(Calendar.MONTH) + 1;
        int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

        // Gọi phương thức cập nhật biểu đồ với thông tin ngày bắt đầu và ngày kết thúc
        setDataToChart3(pnlDTTKCot, startYear, startMonth, startDay, endYear, endMonth, endDay);
    }

    public void setDataToChart3(JPanel jpnItem, int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
        List<DoanhThu_Service> listItem = tkservice.getListByDoanhThu2(startYear, startMonth, startDay, endYear, endMonth, endDay);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DoanhThu_Service item : listItem) {
                dataset.addValue(item.getTien(), "Doanh Thu", item.getNgay_dang_ky());
            }
        }

        JFreeChart barChart = createBarChart(dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }


    private void jmonthPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jmonthPropertyChange
        if ("month".equals(evt.getPropertyName())) {
            int selectedMonth = (int) evt.getNewValue() + 1; // Vì JMonthChooser trả về giá trị từ 0 đến 11
            updateChart1(selectedMonth);
        }
    }//GEN-LAST:event_jmonthPropertyChange
    private void updateChart1(int selectedMonth) {
        setDataToChart2(pnlDTTKCot, selectedMonth);
    }

    public void setDataToChart2(JPanel jpnItem, int selectedMonth) {
        List<DoanhThu_Service> listItem = tkservice.getListByDoanhThu1(selectedMonth);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DoanhThu_Service item : listItem) {
                dataset.addValue(item.getTien(), "Doanh Thu", item.getNgay_dang_ky());
            }
        }

        JFreeChart barChart = createBarChart(dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }


    private void rdoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThangActionPerformed

    }//GEN-LAST:event_rdoThangActionPerformed

  


    private void rdoNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNamMouseClicked

    }//GEN-LAST:event_rdoNamMouseClicked

    private void jDateCuoiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateCuoiPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            updateChartFromDateChoosers();
        }
    }//GEN-LAST:event_jDateCuoiPropertyChange


    private void jDateDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateDauPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            updateChartFromDateChoosers();
        }
    }//GEN-LAST:event_jDateDauPropertyChange

    private ThongKe_Service tkservice = new ThongKe_Service();

    public void setDataToChart1(JPanel jpnItem, int timeUnit) {
        List<DoanhThu_Service> listItem = tkservice.getListByDoanhThu(timeUnit);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DoanhThu_Service item : listItem) {
                dataset.addValue(item.getTien(), "Doanh Thu", item.getNgay_dang_ky());
            }
        }

        JFreeChart barChart = createBarChart(dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    private JFreeChart createBarChart(DefaultCategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu".toUpperCase(),
                "Thời gian", "Doanh Thu",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Tinh chỉnh màu sắc và chiều rộng của cột
        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesPaint(i, new Color(135, 189, 245)); // Đặt màu xanh
        }
        renderer.setMaximumBarWidth(0.05);
        // Tinh chỉnh font của nhãn trục x và trục y
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        return barChart;
    }
public void setDataToChartToDay(JPanel jpnItem) {
    // Lấy ngày hôm nay
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String todayString = today.format(formatter);

    List<DoanhThu_Service> listItem = tkservice.getListByDoanhThu3(todayString, todayString);

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    if (listItem != null) {
        for (DoanhThu_Service item : listItem) {
            dataset.addValue(item.getTien(), "Doanh Thu", item.getNgay_dang_ky());
        }
    }

    JFreeChart barChart = createBarChart(dataset);

    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

    jpnItem.removeAll();
    jpnItem.setLayout(new CardLayout());
    jpnItem.add(chartPanel);
    jpnItem.validate();
    jpnItem.repaint();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.raven.component.Card card1;
    private com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private com.raven.component.Card card4;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateCuoi;
    private com.toedter.calendar.JDateChooser jDateDau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JMonthChooser jmonth;
    private javax.swing.JLayeredPane panel;
    private com.raven.chart.Chart pnlDTTKCot;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNgay;
    private javax.swing.JRadioButton rdoThang;
    private javax.swing.JTextField search;
    private javax.swing.JTable tableThongKe;
    // End of variables declaration//GEN-END:variables
}
