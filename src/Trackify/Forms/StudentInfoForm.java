
package Trackify.Forms;

import Trackify.Controller.StudentController;
import Trackify.FormsPopup.StudentInfoFormPopup;

import Trackify.Models.StudentModel;
import Trackify.Swing.SignatureTableRenderer;
import Trackify.Swing.TableHeaderAlignment;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;

/**
 *
 * @author USER
 */
public class StudentInfoForm extends javax.swing.JPanel {

   private StudentController studentController;
    public StudentInfoForm() {
        initComponents();

        studentController = new StudentController();
        init();
        
     
          
       studentController.PopulateStudentInfo(studentInfoTable);
              
        setOpaque(false);
    }
    private void init(){
 
          studentInfoTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        studentInfoTable.putClientProperty(FlatClientProperties.STYLE, ""
                 + "rowHeight:60;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Student");
        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("Trackify/Icons/search.svg"));
        studentInfoTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(studentInfoTable));
         studentInfoTable.getColumnModel().getColumn(5).setCellRenderer(new SignatureTableRenderer(studentInfoTable));
      
    }


    private StudentModel TableRowInfo(){
        int row = studentInfoTable.getSelectedRow();
        String studentID = studentInfoTable.getModel().getValueAt(row, 0).toString();
        String lastName = studentInfoTable.getModel().getValueAt(row, 1).toString();
        String firstName = studentInfoTable.getModel().getValueAt(row, 2).toString();
        String course = studentInfoTable.getModel().getValueAt(row, 3).toString();
        String yearLevel = studentInfoTable.getModel().getValueAt(row, 4).toString();
        int studentIDNum = Integer.parseInt(studentID);
        return new StudentModel(studentIDNum, firstName, lastName, course, yearLevel, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentInfoTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();

        jLabel1.setText("Student Information");

        jButton1.setText("Add Student");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        studentInfoTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        studentInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID #", "Last Name", "First Name", "Course", "Year Level", "E  signature"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentInfoTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentInfoTable.setDragEnabled(true);
        studentInfoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentInfoTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studentInfoTable);
        if (studentInfoTable.getColumnModel().getColumnCount() > 0) {
            studentInfoTable.getColumnModel().getColumn(0).setMinWidth(75);
            studentInfoTable.getColumnModel().getColumn(0).setPreferredWidth(75);
            studentInfoTable.getColumnModel().getColumn(0).setMaxWidth(75);
        }

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(647, 647, 647)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(searchField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      RegisterStudentInfoForms();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void studentInfoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentInfoTableMouseClicked
     Update_Delete_StudentInfoForms();
    }//GEN-LAST:event_studentInfoTableMouseClicked

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
      studentController.SearchStudentInfo(studentInfoTable, searchField.getText());
    }//GEN-LAST:event_searchFieldKeyReleased
private void RegisterStudentInfoForms() {
       StudentInfoFormPopup studentInfoForm = new StudentInfoFormPopup();
        Option option = ModalDialog.createOption();
    
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
                                 studentController.PopulateStudentInfo(studentInfoTable);
                           Toast.show(this, Toast.Type.SUCCESS, "Succesfully Register.");
                           
                            }

                        }else{
                             Toast.show(this, Toast.Type.WARNING, "Please fill out all fields."); 
                              controller.consume();
                        }
                       
                    }else{
                        controller.close();
                    }        
                }), option);
    }

private void Update_Delete_StudentInfoForms() {
       StudentInfoFormPopup studentInfoForm = new StudentInfoFormPopup();
       studentInfoForm.EnabledIdField();
       studentInfoForm.setData(TableRowInfo());
        Option option = ModalDialog.createOption();
    
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.RIGHT, Location.TOP)
                .setAnimateDistance(0.7f, 0);
       
          SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("DELETE", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("UPDATE", SimpleModalBorder.CANCEL_OPTION)};
        ModalDialog.showModal(this, new SimpleModalBorder(
                studentInfoForm, "Create", options,
                (controller, action) -> {
                    studentInfoForm.EnabledIdField();
                    if (action==SimpleModalBorder.YES_OPTION) {
                        if (!studentInfoForm.noInputData()) {
                         studentController.DeleteStudentInfo(studentInfoForm.getData());
                             Toast.show(this, Toast.Type.SUCCESS, "Succesfully Deleted."); 
                             studentController.PopulateStudentInfo(studentInfoTable);
                        
                            

                        }else{
                             Toast.show(this, Toast.Type.WARNING, "Please fill out all fields."); 
                              controller.consume();
                        }
         
                       
                    }else if(action==SimpleModalBorder.CANCEL_OPTION){
                       studentController.UpdateStudentInfo(studentInfoForm.getData());
                                 studentController.PopulateStudentInfo(studentInfoTable);
                           Toast.show(this, Toast.Type.SUCCESS, "Succesfully Updated.");
     
                    }else{
                         controller.close();
                    }
                  
                  
                  
                }), option);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private javax.swing.JTable studentInfoTable;
    // End of variables declaration//GEN-END:variables
}
class IconRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Icon) {
            setIcon((Icon) value);
            setText("");
        } else {
            setIcon(null);
            setText((value == null) ? "" : value.toString());
        }
        return this;
    }
}
