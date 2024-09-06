/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Swing;

import Trackify.Component.TableCellSignature;
import Trackify.Models.StudentModel;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class SignatureTableRenderer implements TableCellRenderer {
    private final TableCellRenderer oldCellRenderer;

    public SignatureTableRenderer(JTable table) {
        oldCellRenderer = table.getDefaultRenderer(Object.class);
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = oldCellRenderer.getTableCellRendererComponent(jtable, o, isSelected, hasFocus, row, column);

        if (o instanceof StudentModel) {
            TableCellSignature cell = new TableCellSignature((StudentModel) o);
            cell.setBackground(com.getBackground());
            return cell;
        } else if (o instanceof String) {
            JLabel label = new JLabel((String) o);
            label.setBackground(com.getBackground());
            return label;
        } else if (o instanceof Icon) {
            JLabel label = new JLabel((Icon) o);
            label.setBackground(com.getBackground());
            return label;
        } else {
            return com;
        }
    }
}

