
package Trackify.Models.UserDaoController;

import java.awt.Component;

public class DaoController {
    AdminController Dao;
    public DaoController() {
        
     this.Dao = new AdminController();
    
    }
    
    public boolean registerAdmin(ModelAdminData data){
        String encryptedPassword = Dao.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return Dao.createAdmin(data);
    }
   public ModelAdminData loginAdmin(ModelAdminData data){
       String encryptedPassword = Dao.encryptPass(new String(data.getPassword()));
       data.setPassword(encryptedPassword.toCharArray());
       return Dao.loginAdmin(data);
   }
   public boolean changePassword(ModelAdminData data,Component com){
       String encryptedNewPassword = Dao.encryptPass(new String(data.getNewpassword()));
       data.setNewpassword(encryptedNewPassword.toCharArray());
       String encryptedPassword = Dao.encryptPass(new String(data.getPassword()));
       data.setPassword(encryptedPassword.toCharArray());
       return Dao.changeAdminPassword(data, com);
   }
    
 
}
