/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Trackify.Main.others;

import Trackify.Controller.EventsController;
import Trackify.Controller.StudentController;
import Trackify.Controller.StudentLogsController;
import Trackify.FormsPopup.LoginForms;
import Trackify.FormsPopup.StudentInfoFormPopup;
import Trackify.Main.Main;
import Trackify.Models.EventsModel;
import Trackify.Models.StudentLogsModel;
import Trackify.Models.StudentModel;
import Trackify.Models.other.EventImageModel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;

/**
 *
 * @author USER
 */
public class MainFrame extends javax.swing.JFrame {
    private EventsController eventsController;
    private StudentController studentController;
    public MainFrame() {
        initComponents();
        studentController = new StudentController();
        eventsController = new EventsController();
setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 35, 35));
           List<EventsModel> list = eventsController.PopulateToMainFrame();
    
    // Create a list to hold the titles
    List<String> titles = new ArrayList<>();
    
    // Extract titles from the list of EventsModel
    for (EventsModel eventsModel : list) {
        titles.add(eventsModel.getTitle());
    }
    
    // Create a DefaultComboBoxModel with the list of titles
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(titles.toArray(new String[0]));
    
    // Set the model to the JComboBox
    eventcb.setModel(model);
        

  
        init();
         setupKeyBindings();
    }
@Override
public void setSize(int width, int height) {
    super.setSize(width, height);
    setShape(new RoundRectangle2D.Double(0, 0, width, height, 40, 40));  // Ensure the shape is updated when resizing
}
    private void init(){
          setBackground(Color.WHITE);
         
         searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Student");
        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("Trackify/Icons/search.svg"));
        mainPanel.putClientProperty(FlatClientProperties.STYLE,"arc: 35");
      
      
        jPanel1.putClientProperty(FlatClientProperties.STYLE,"arc: 40");
    }
    
    private void RegisterStudentInfoForms() {
       StudentInfoFormPopup studentInfoForm = new StudentInfoFormPopup();
        Option option = ModalDialog.createOption();
        option.setCloseOnPressedEscape(false);
        option.setBackgroundClickType(Option.BackgroundClickType.BLOCK);
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.RIGHT, Location.TOP)
                .setAnimateDistance(0.7f, 0);
        
          SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("REGISTER", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("CANCEL", SimpleModalBorder.CANCEL_OPTION)};
        ModalDialog.showModal(this, new SimpleModalBorder(
                studentInfoForm, "Create", options,
                (controller, action) -> {
                    if (action==SimpleModalBorder.YES_OPTION) {
                        if (!studentInfoForm.noInputData()) {
                            if (studentController.existingStudent(studentInfoForm.getData())) {
                                        Toast.show(this, Toast.Type.WARNING, "You are already Registered!");
                                        controller.consume();
                                          
                        }else {
                                  studentController.RegisterStudent(studentInfoForm.getData());
                                
                           Toast.show(this, Toast.Type.SUCCESS, "Succesfully Register.");
                             onPopupClosed();
                            }

                        }else{
                             Toast.show(this, Toast.Type.WARNING, "Please fill out all fields."); 
                              controller.consume();
                              onPopupClosed();
                        }
                       
                    }else{
                        controller.close();
                         onPopupClosed();
                     
                    } 
                   
                }), option);
      
    }
     private void Login() {
       StudentInfoFormPopup studentInfoForm = new StudentInfoFormPopup();
        Option option = ModalDialog.createOption();
        option.setCloseOnPressedEscape(false);
        option.setBackgroundClickType(Option.BackgroundClickType.BLOCK);
        option.getLayoutOption().setSize(280, 240)
                .setLocation(Location.CENTER, Location.CENTER)
                .setAnimateDistance(0.7f, 0);
        
          SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("LOGIN", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("CANCEL", SimpleModalBorder.CANCEL_OPTION)};
        ModalDialog.showModal(this, new SimpleModalBorder(
                new LoginForms(), "ADMIN", options,
                (controller, action) -> {
               onPopupClosed();
                    if (action==SimpleModalBorder.YES_OPTION) {              
        try {
            Main main = new Main();
             main.setVisible(true);
           this.setVisible(false);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
               
                    }
                }), option);
      
    }
    private boolean setTextToDefault(){
        studentID.setText("#000000");
        studentName.setText("e.g Juan Dela Cruz");
        studentCourse.setText("BS-CPE");
        studentYearLevel.setText("1st Year");
        return true;
    }
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        eventcb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        studentID = new javax.swing.JLabel();
        studentName = new javax.swing.JLabel();
        studentCourse = new javax.swing.JLabel();
        studentYearLevel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        e_Signature = new Trackify.Swing.DrawSignatureLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Student ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Full Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Course");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Year Level");

        studentID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        studentID.setForeground(new java.awt.Color(102, 102, 102));
        studentID.setText("#000000");

        studentName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        studentName.setForeground(new java.awt.Color(102, 102, 102));
        studentName.setText("e.g Juan Dela Cruz");

        studentCourse.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        studentCourse.setForeground(new java.awt.Color(102, 102, 102));
        studentCourse.setText("BS-CPE");

        studentYearLevel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        studentYearLevel.setForeground(new java.awt.Color(102, 102, 102));
        studentYearLevel.setText("1st Year");

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Prev");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(e_Signature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(e_Signature, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("E-Signature");

        jButton4.setText("Time out");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Time in");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("X");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Events");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(jButton5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                            .addGap(115, 115, 115)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchField)
                            .addComponent(studentID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(studentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(studentCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(studentYearLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(eventcb, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventcb, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(studentID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentYearLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       e_Signature.resetDrawing();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         e_Signature.undo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       e_Signature.redo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
     
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
     char c = evt.getKeyChar();
        if (!(Character.isDigit(c))) {
            evt.consume();
        }
    }//GEN-LAST:event_searchFieldKeyTyped

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
    System.exit(0);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
      String input = searchField.getText().trim(); // Trim any leading or trailing whitespace
if (input.isEmpty()) {
    Toast.show(this, Toast.Type.WARNING, "Please enter a Student ID!");
    setTextToDefault();
} else {
    try {
        int search = Integer.parseInt(input);
        StudentModel data = new StudentModel();
        data.setStudentID(search);

        if (studentController.StudentInfo(data)) {
            studentID.setText(String.valueOf(data.getStudentID()));
            studentName.setText(data.getLastName() + ", " + data.getFirstName());
            studentCourse.setText(data.getCourse());
            studentYearLevel.setText(data.getYearLevel());
        } else {
            Toast.show(this, Toast.Type.INFO,input+ " Not Registered!");
            setTextToDefault();
        }
    } catch (NumberFormatException e) {
        Toast.show(this, Toast.Type.INFO, "Invalid Student ID! Please enter a valid number.");
        setTextToDefault();
    }
}
        
    }//GEN-LAST:event_searchFieldActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (e_Signature.isDrawingEmpty()||studentID.getText().equals("#000000")||studentName.getText().equals("e.g Juan Dela Cruz")||eventcb.getSelectedItem().equals("")) {
            
             Toast.show(this, Toast.Type.WARNING, "DATA NOT FOUND!");
        }else{
              LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = currentTime.format(dtf);
                        LocalTime formattedLocalTime = LocalTime.parse(formattedTime, dtf);

            
            StudentLogsController logsController = new StudentLogsController();
            int sID = Integer.parseInt(studentID.getText());
            ImageIcon e_signature = e_Signature.getDrawingAsIcon(230, 230);
            StudentLogsModel logsModel = new StudentLogsModel((String) eventcb.getSelectedItem(), sID, studentName.getText(), studentCourse.getText(), studentYearLevel.getText(), 
                    formattedLocalTime,formattedLocalTime, e_signature, formattedLocalTime, formattedLocalTime,e_signature);
            
            logsController.AfternoonTimeIn(logsModel,this);
            
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     if (e_Signature.isDrawingEmpty()||studentID.getText().equals("#000000")||studentName.getText().equals("e.g Juan Dela Cruz")) {
            System.out.println("signature is empty");
        }else{
              LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = currentTime.format(dtf);
                        LocalTime formattedLocalTime = LocalTime.parse(formattedTime, dtf);
   
            StudentLogsController logsController = new StudentLogsController();
            int sID = Integer.parseInt(studentID.getText());
            ImageIcon e_signature = e_Signature.getDrawingAsIcon(230, 230);
            StudentLogsModel logsModel = new StudentLogsModel((String) eventcb.getSelectedItem(), sID, studentName.getText(), studentCourse.getText(), studentYearLevel.getText(), 
                    formattedLocalTime,formattedLocalTime, e_signature, formattedLocalTime, formattedLocalTime,e_signature);
            
            logsController.AfternoonTimeOut(logsModel,this);
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed
private boolean isPopupVisible = false;
  private void onPopupClosed() {
    isPopupVisible = false; // Reset the flag when popup is closed
}
       private void setupKeyBindings() {
        // Get the root pane's input map and action map
       
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        // Define the key strokes for Alt + F1 and Alt + F2
        KeyStroke altF1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.ALT_DOWN_MASK);
        KeyStroke altF2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_DOWN_MASK);
         KeyStroke altF3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.ALT_DOWN_MASK);

        // Map the key strokes to action keys
        inputMap.put(altF1, "altF1");
        inputMap.put(altF2, "altF2");
        inputMap.put(altF3, "altF3");

        // Define the actions
        actionMap.put("altF1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                if (!isPopupVisible) {
                    isPopupVisible = true;
                RegisterStudentInfoForms();
                
                }
                
            
                
            }
            
        });
       
        actionMap.put("altF3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
        
            }
        });
        actionMap.put("altF2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        
              if (!isPopupVisible) {
                    isPopupVisible = true;
              Login();
                
                }
                
            }
        });
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Trackify.Swing.DrawSignatureLabel e_Signature;
    private javax.swing.JComboBox<String> eventcb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel studentCourse;
    private javax.swing.JLabel studentID;
    private javax.swing.JLabel studentName;
    private javax.swing.JLabel studentYearLevel;
    // End of variables declaration//GEN-END:variables
}
