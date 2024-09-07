/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Trackify.Forms;

import Trackify.Models.UserDaoController.ModelAdminData;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.Arrays;

/**
 *
 * @author USER
 */
public class AdminForms extends javax.swing.JPanel {

    
  
   
    public AdminForms() {
        initComponents();
        init();
    }

   private void init(){
       confirmUserName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
       confirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
       newUserName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "New UserName");
       newPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "New Password");
       confirmNewPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirm New Password");
   }
     public ModelAdminData getData() {
        return new ModelAdminData(newPassword.getPassword(), newUserName.getText(),confirmUserName.getText() , confirmPassword.getPassword());
    }

   private ModelAdminData data;
   
   public boolean isEmptyFields(){
       if (confirmUserName.getText().isEmpty()||confirmPassword.getPassword().equals("")||newUserName.getText().isEmpty()
               ||newPassword.getPassword().equals("")||confirmNewPassword.getPassword().equals("")) {
           
           return true;
       }else{
           return false;
       }
   }
   public boolean isMismatched(){
       if (!Arrays.equals(newPassword.getPassword(), confirmNewPassword.getPassword())) { 
           return true;
       }else{
           return false;
       }
   }
   public void setFieldstoEmpty(){
       confirmUserName.setText("");
       confirmPassword.setText("");
       newUserName.setText("");
       newPassword.setText("");
       confirmNewPassword.setText("");
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        confirmUserName = new javax.swing.JTextField();
        confirmPassword = new javax.swing.JPasswordField();
        newUserName = new javax.swing.JTextField();
        newPassword = new javax.swing.JPasswordField();
        confirmNewPassword = new javax.swing.JPasswordField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmUserName)
                    .addComponent(confirmPassword)
                    .addComponent(newUserName)
                    .addComponent(newPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(confirmNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(confirmUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(newUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmNewPassword;
    private javax.swing.JPasswordField confirmPassword;
    private javax.swing.JTextField confirmUserName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JTextField newUserName;
    // End of variables declaration//GEN-END:variables
}
