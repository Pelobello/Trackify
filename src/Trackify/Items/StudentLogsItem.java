/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Trackify.Items;

import Trackify.Models.StudentLogsModel;
import com.formdev.flatlaf.FlatClientProperties;
import java.time.format.DateTimeFormatter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class StudentLogsItem extends javax.swing.JPanel {

    
   
  public void setData(StudentLogsModel data) {
    this.data = data;
    String formattedSID = Integer.toString(data.getStudent_ID());
    idLbl.setText(formattedSID);
    fullNameLbl.setText(data.getStudentFullName());
    courseLbl.setText(data.getCourse());
    yearLevelLbl.setText(data.getYearLevel());
    EventTitleLbl.setText(data.getEventTitle());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); // 12-hour format with AM/PM

    // Check for null and format time if not null, otherwise set to a default value
    morningInLbl.setText(data.getMorning_Time_in() != null ? data.getMorning_Time_in().format(formatter) : "N/D");
    morningOutLbl.setText(data.getMorning_Time_out() != null ? data.getMorning_Time_out().format(formatter) : "N/D");
    afternoonInLbl.setText(data.getAfternoon_Time_in() != null ? data.getAfternoon_Time_in().format(formatter) : "N/D");
    afternoonOutLbl.setText(data.getAfternoon_Time_out() != null ? data.getAfternoon_Time_out().format(formatter) : "N/D");

    // Correctly set image icons
    if (data.getMorning_Signature() instanceof Icon) {
        morningSignatureLbl.setImage((Icon) data.getMorning_Signature());
    } else {
        // Handle the case where the signature is not an Icon, e.g., log an error or set a default icon
        morningSignatureLbl.setImage(new ImageIcon(getClass().getResource("/Trackify/Icons/EventsDefaultImage.jpg")));
    }

    if (data.getAfternoon_Signature() instanceof Icon) {
        afternoonSignatureLbl.setImage((Icon) data.getAfternoon_Signature());
    } else {
        // Handle the case where the signature is not an Icon, e.g., log an error or set a default icon
        afternoonSignatureLbl.setImage(new ImageIcon(getClass().getResource("/Trackify/Icons/EventsDefaultImage.jpg")));
    }
}

  
    public boolean isSelected() {
        return Selected;
    }

    /**
     * @param Selected the Selected to set
     */
    public void setSelected(boolean Selected) {
        this.Selected = Selected;
        repaint();
    }

     private boolean Selected;
     private StudentLogsModel data;
    public StudentLogsItem() {
        initComponents();
        setOpaque(false);
        init();
    }
private void init(){
        bgPanel.putClientProperty(FlatClientProperties.STYLE,"arc: 25");
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPanel = new javax.swing.JPanel();
        idLbl = new javax.swing.JLabel();
        fullNameLbl = new javax.swing.JLabel();
        courseLbl = new javax.swing.JLabel();
        yearLevelLbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        afternoonInLbl = new javax.swing.JLabel();
        afternoonOutLbl = new javax.swing.JLabel();
        afternoonSignatureLbl = new Trackify.Swing.PictureBox();
        morningInLbl = new javax.swing.JLabel();
        morningOutLbl = new javax.swing.JLabel();
        morningSignatureLbl = new Trackify.Swing.PictureBox();
        EventTitleLbl = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        idLbl.setText("#000000");

        fullNameLbl.setText("e.g Juan Dela Cruz");

        courseLbl.setText("BS-CPE");

        yearLevelLbl.setText("1ST YEAR");

        afternoonInLbl.setText("12:30 pm");

        afternoonOutLbl.setText("15:00 pm");

        morningInLbl.setText("8:30 am");

        morningOutLbl.setText("12:00 am");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(morningInLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(morningOutLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(morningSignatureLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(afternoonInLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(afternoonOutLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(afternoonSignatureLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(afternoonSignatureLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(morningInLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addComponent(morningSignatureLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(morningOutLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(afternoonInLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(afternoonOutLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        EventTitleLbl.setText("Title");

        javax.swing.GroupLayout bgPanelLayout = new javax.swing.GroupLayout(bgPanel);
        bgPanel.setLayout(bgPanelLayout);
        bgPanelLayout.setHorizontalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(idLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(fullNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(courseLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(yearLevelLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(EventTitleLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );
        bgPanelLayout.setVerticalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgPanelLayout.createSequentialGroup()
                .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yearLevelLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(courseLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fullNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(EventTitleLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EventTitleLbl;
    private javax.swing.JLabel afternoonInLbl;
    private javax.swing.JLabel afternoonOutLbl;
    private Trackify.Swing.PictureBox afternoonSignatureLbl;
    private javax.swing.JPanel bgPanel;
    private javax.swing.JLabel courseLbl;
    private javax.swing.JLabel fullNameLbl;
    private javax.swing.JLabel idLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel morningInLbl;
    private javax.swing.JLabel morningOutLbl;
    private Trackify.Swing.PictureBox morningSignatureLbl;
    private javax.swing.JLabel yearLevelLbl;
    // End of variables declaration//GEN-END:variables
}
