package com.raven.swing;

import com.raven.model.StatusType;
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
        if (i1 != 4) {
            // Phần xử lý cho các cột khác
            Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
            com.setBackground(Color.WHITE);
            setBorder(noFocusBorder);
            if (selected) {
                com.setForeground(new Color(15, 89, 140));
            } else {
                com.setForeground(new Color(102, 102, 102));
            }
            return com;
        } else {
            // Phần xử lý cho cột có chỉ số 4
            if (o instanceof StatusType) {
                StatusType type = (StatusType) o;
                CellStatus cell = new CellStatus(type);
                return cell;
            } else if (o instanceof BigDecimal) {
                // Xử lý khi đối tượng là kiểu BigDecimal
                // Ví dụ: Tạo một thành phần JLabel để hiển thị giá trị
                JLabel label = new JLabel(o.toString());
                label.setHorizontalAlignment(JLabel.CENTER);
                if (selected) {
                    label.setForeground(new Color(15, 89, 140));
                } else {
                    label.setForeground(new Color(102, 102, 102));
                }
                return label;
            } else {
                // Xử lý trường hợp nếu đối tượng không phải là StatusType hoặc BigDecimal
                // Trả về một thành phần mặc định nếu cần
                return super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
            }
        }
    }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
