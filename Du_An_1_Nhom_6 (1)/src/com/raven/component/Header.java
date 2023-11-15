package com.raven.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Header extends javax.swing.JPanel {
    private Timer timer;
    private int posX;
    private JLabel label;
    
    public Header() {
        initComponents();
        setOpaque(false);
        runTieuDe();
    }
 

    private void runTieuDe() {
         label = new JLabel("Phần Mềm Bán Áo Phông Bee-Shirt");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        posX = getWidth();
        timer = new Timer(1000, new ActionListener() { // Mỗi giây
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTextColor();
                posX--;
                label.setLocation(posX, label.getY());
                if (posX + label.getWidth() <= 0) {
                    posX = getWidth() - label.getWidth();
                }
            }
        });
        setLayout(null);
        add(label);
        timer.start();
    }
     private void changeTextColor() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ss"); // Lấy giây
        int second = Integer.parseInt(sdf.format(now));
        float hue = (float) second / 60.0f; // Chia cho 60 để có giá trị nằm trong khoảng [0, 1]
        Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
        label.setForeground(color);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new com.raven.swing.ImageAvatar();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lable = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/8.png"))); // NOI18N

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("ADMIN");

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("VuongPh43947");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lable.setBackground(new java.awt.Color(102, 102, 102));
        lable.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lable.setForeground(new java.awt.Color(102, 102, 102));
        lable.setText("Phần Mềm Bán Áo Phông Bee-Shirt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lable, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(35, 35, 35))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(24, 24, 24)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel4))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lable, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, 25, getHeight());
        g2.fillRect(getWidth() - 25, getHeight() - 25, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lable;
    private com.raven.swing.ImageAvatar pic;
    // End of variables declaration//GEN-END:variables
}
