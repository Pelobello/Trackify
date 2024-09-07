/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Models.UserDaoController;


public class ModelAdminData {

  
    public char[] getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(char[] newpassword) {
        this.newpassword = newpassword;
    }

    public String getNewusername() {
        return newusername;
    }

    public void setNewusername(String newusername) {
        this.newusername = newusername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public ModelAdminData(char[] newpassword, String newusername, String username, char[] password) {
        this.newpassword = newpassword;
        this.newusername = newusername;
        this.username = username;
        this.password = password;
    }

    
    
    public ModelAdminData() {
    }
    
     private char[]newpassword;
    private String newusername;
    private String username;
    private char[]password;
}
