/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Trackify.Forms;

import Trackify.Controller.EventsController;
import Trackify.Controller.StudentLogsController;
import Trackify.Event.EventLogsItem;
import Trackify.Items.StudentLogsItem;
import Trackify.Models.EventsModel;
import Trackify.Models.StudentLogsModel;
import Trackify.Reports.Model.FieldStudentsLogsModel;
import Trackify.Reports.Model.ParametersStudentsLogsModel;
import Trackify.Reports.ReportManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import static java.nio.file.Files.list;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import raven.modal.demo.layout.ResponsiveLayout;

/**
 *
 * @author USER
 */
public class StudentLogsDataForm extends javax.swing.JPanel {

    private EventLogsItem event;
    public StudentLogsDataForm() throws SQLException {
        initComponents();
         setOpaque(false);
         init();
       
         populateEventsToCb();
          SearchStudentsLogs();
    }
    public void addStudentLogs(StudentLogsModel data){
        StudentLogsItem logsItem = new StudentLogsItem();
        logsItem.setData(data);
        logsItem.addMouseListener(new MouseAdapter() {
          @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    event.itemClick(logsItem, data);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
              logsItem.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                logsItem.setToolTipText("ID, NAME, COURSE, YEAR LEVEL, MORNING IN-OUT-SIGNATURE, AFTERNOON IN-OUT-SIGNATURE AND EVENT TITLE");
              
            }

            @Override
            public void mouseExited(MouseEvent e) {
               logsItem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        
        });
        
        responsiveItem.add(logsItem);
        repaint();
        revalidate();
    }

   private void init(){
//       jPanel1.putClientProperty(FlatClientProperties.STYLE,"arc:25");
       searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Student#");
       searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("Trackify/Icons/search.svg"));
  
   }
    public EventLogsItem getEvent() {
        return event;
    }

   
    public void setEvent(EventLogsItem event) {
        this.event = event;
    }

    private void testReport(){
    try {
        ReportManager.getInstance().compileReport();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        responsiveItem = new Trackify.Swing.ResponsivePanelItem();
        searchField = new javax.swing.JTextField();
        courseCb = new javax.swing.JComboBox<>();
        EventsCb = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Students Logs Data");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        responsiveItem.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(responsiveItem);

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        courseCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL COURSE", "BS-IT", "BS-CPE", "BS-CRIM", "BS-BA", "BS-MA", "BS-ED", "BS-TVTED" }));
        courseCb.setToolTipText("Courses");
        courseCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseCbActionPerformed(evt);
            }
        });

        EventsCb.setToolTipText("Events");
        EventsCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EventsCbActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Trackify/Icons/icons8_print_30px.png"))); // NOI18N
        jButton1.setToolTipText("Print");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(1003, 1003, 1003))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(courseCb, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EventsCb, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchField)
                    .addComponent(courseCb)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(EventsCb, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
 
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        try {
            SearchStudentsLogs();
        } catch (SQLException ex) {
            Logger.getLogger(StudentLogsDataForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFieldActionPerformed

    private void courseCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseCbActionPerformed
        try {
            SearchStudentsLogs();
        } catch (SQLException ex) {
            Logger.getLogger(StudentLogsDataForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_courseCbActionPerformed

    private void EventsCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EventsCbActionPerformed
        try {
            SearchStudentsLogs();
        } catch (SQLException ex) {
            Logger.getLogger(StudentLogsDataForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EventsCbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try {
       
        StudentLogsController controller = new StudentLogsController();
        List<FieldStudentsLogsModel> fields = controller.GetStudentsLogs(searchField.getText(),(String) courseCb.getSelectedItem(), (String)EventsCb.getSelectedItem()); // Sample list with one student log entry
        ParametersStudentsLogsModel data = new ParametersStudentsLogsModel((String)EventsCb.getSelectedItem(), fields);
        
        // Ensure ReportManager instance is correctly initialized
        ReportManager reportManager = ReportManager.getInstance();
       
            reportManager.printStudentsLogs(data);
        

    } catch (Exception e) {
        e.printStackTrace(); // Check the console for detailed error information
    }
    }//GEN-LAST:event_jButton1ActionPerformed
   private InputStream convertImageIconToInputStream(ImageIcon icon) throws IOException {
    BufferedImage image = new BufferedImage(icon.getImage().getWidth(null), icon.getImage().getWidth(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();
    
    
    g2d.drawImage(icon.getImage(), 0, 0,null);
    g2d.dispose();
    BufferedImage bimg = image;
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(bimg, "jpg", baos);
    return new ByteArrayInputStream(baos.toByteArray());
}
    
    
    private void SearchStudentsLogs() throws SQLException{
        this.setEvent(new EventLogsItem() {
            @Override
            public void itemClick(Component com, StudentLogsModel data) {
                System.out.println("Item Clicked");
            }
        });
        StudentLogsController controller = new StudentLogsController();
        List<StudentLogsModel>list = controller.SearchStudentsLogs(searchField.getText(),(String) courseCb.getSelectedItem(), (String)EventsCb.getSelectedItem());
        responsiveItem.removeAll();
        
        //insert DATE here!
           for (StudentLogsModel event : list) {
                  addStudentLogs(event);
                 
                }
        
        repaint();
        revalidate();
    }
//To populate all StudentLogsData...
//    private void InsertStudentsLogs() throws SQLException{
//        this.setEvent(new EventLogsItem() {
//            @Override
//            public void itemClick(Component com, StudentLogsModel data) {
//                System.out.println("Item Clicked");
//            }
//        });
//        StudentLogsController controller = new StudentLogsController();
//        List<StudentLogsModel>list = controller.PopulateStudentsLogs();
//        responsiveItem.removeAll();
//        
//        //insert DATE here!
//           for (StudentLogsModel event : list) {
//                  addStudentLogs(event);
//                 
//                }
//        
//        repaint();
//        revalidate();
//    }
    private void populateEventsToCb(){
        EventsController eventsController = new EventsController();
          List<String> titles = new ArrayList<>();
     List<EventsModel> list = eventsController.PopulateToStudentsLogs();
    // Extract titles from the list of EventsModel
    for (EventsModel eventsModel : list) {
        titles.add(eventsModel.getTitle());
    }
       
    // Create a DefaultComboBoxModel with the list of titles
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(titles.toArray(new String[0]));
    
    // Set the model to the JComboBox
    EventsCb.setModel(model);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> EventsCb;
    private javax.swing.JComboBox<String> courseCb;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Trackify.Swing.ResponsivePanelItem responsiveItem;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
