
package Trackify.FormsPopup;

import Trackify.Models.EventsModel;
import Trackify.Models.other.EventImageModel;
import java.awt.Color;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.sql.Date;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import jnafilechooser.api.JnaFileChooser;
import raven.datetime.component.date.DatePicker;

public class EventsForm extends javax.swing.JPanel {

   
    public void setData(EventsModel data) {
        this.data = data;
        eventImage.setImage(data.getEventImage().getIcon());
        eventTitle.setText(data.getTitle());
        id.setText(data.getEventID());
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String formattedScheduleDate = dateFormat.format(data.getEventSchedule());
    String formattedDeadlineDate = dateFormat.format(data.getEventDeadline());

   
    scheduleDate.setText(formattedScheduleDate);
    deadlineDate.setText(formattedDeadlineDate);
    }
    public boolean noDataInput(){
        if (eventTitle.getText().equals("")||scheduleDate.getText().equals("----------")||deadlineDate.getText().equals("----------")) {
            return true;
        }else{
            return false;
        }
       
    }

    private EventsModel data;
    DatePicker eventSchedDate = new DatePicker();
    DatePicker eventDeadlineDate = new DatePicker();
    
    public EventsForm() {
        
        initComponents();
//       id.setVisible(false);
      
        eventSchedDate.setEditor(scheduleDate);
        eventDeadlineDate.setEditor(deadlineDate);
         eventSchedDate.setCloseAfterSelected(true);
         eventDeadlineDate.setCloseAfterSelected(true);
        eventSchedDate.setDateFormat("yyyy-MM-dd");
         eventDeadlineDate.setDateFormat("yyyy-MM-dd");
         eventDeadlineDate.now();
         eventSchedDate.now();
         String Alphabet = "ABCDEFGHIJKLMNPQRSTUVWXYZ1234567890";
    String randomID = generateRandomID(Alphabet, 6);
    id.setText(randomID);
 
        
    }
    
 
public EventsModel getEventModel() {
 
    Date schedDate =eventSchedDate.isDateSelected()?Date.valueOf(eventSchedDate.getSelectedDate()):null;
     Date eventDeadlineDateData =eventDeadlineDate.isDateSelected()?Date.valueOf(eventDeadlineDate.getSelectedDate()):null;

    return new EventsModel(id.getText(), eventTitle.getText(), schedDate, eventDeadlineDateData, eventImageProfile);
}
    
    private static String generateRandomID(String candidateChar,int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChar.charAt(random.nextInt(candidateChar.length())));
            
        }
        return sb.toString();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eventTitle = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        eventImage = new Trackify.Swing.PictureBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        deadlineDate = new javax.swing.JFormattedTextField();
        scheduleDate = new javax.swing.JFormattedTextField();
        id = new javax.swing.JLabel();

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("TITLE");
        jLabel1.setToolTipText("");

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("EVENT SCHEDULE");
        jLabel2.setToolTipText("");

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("EVENT DEADLINE");
        jLabel3.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        eventImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/Trackify/Icons/DefaultImage.png"))); // NOI18N

        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Browse");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Delete");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(eventImage, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        scheduleDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleDateMouseClicked(evt);
            }
        });
        scheduleDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(scheduleDate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(deadlineDate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eventTitle, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deadlineDate, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scheduleDate))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scheduleDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleDateActionPerformed
   
    }//GEN-LAST:event_scheduleDateActionPerformed

    private void scheduleDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleDateMouseClicked
 
    }//GEN-LAST:event_scheduleDateMouseClicked
  private EventImageModel eventImageProfile;
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
     JnaFileChooser ch = new JnaFileChooser();
     ch.addFilter("image", "png","jpg");
     boolean act = ch.showOpenDialog(SwingUtilities.getWindowAncestor(this));
        if (act) {
            File file = ch.getSelectedFile();
            eventImage.setImage(new ImageIcon(file.getAbsolutePath()));
            eventImageProfile = new EventImageModel(file);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       eventImage.setImage(new ImageIcon(getClass().getResource("/Trackify/Icons/EventsDefaultImage.jpg")));
    }//GEN-LAST:event_jLabel5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField deadlineDate;
    private Trackify.Swing.PictureBox eventImage;
    private javax.swing.JTextField eventTitle;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField scheduleDate;
    // End of variables declaration//GEN-END:variables
}
