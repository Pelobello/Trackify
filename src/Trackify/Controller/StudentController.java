
package Trackify.Controller;

import Trackify.Database.DatabaseConnection;
import Trackify.Forms.StudentInfoForm;
import Trackify.Models.StudentModel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.modal.Toast;


public class StudentController {
    PreparedStatement ps;
    ResultSet rs;
    public StudentController() {
        
    }
    
    public void RegisterStudent(StudentModel data){
        try {
            StudentInfoForm studentInfo = new StudentInfoForm();
            String sql="INSERT INTO student_information(Student_ID,FirstName,LastName,Course,YearLevel,ESignature)VALUES(?,?,?,?,?,?)";
            ps = prepareStatement(sql);
              BufferedImage bufferedImage = convertImageIconToBufferedImage((ImageIcon) data.geteSignature());

            // Convert to base64 string
            String base64Image = encodeImageToBase64(bufferedImage);
            
          
            ps.setInt(1, data.getStudentID());
            ps.setString(2, data.getFirstName());
            ps.setString(3, data.getLastName());
            ps.setString(4, data.getCourse());
            ps.setString(5, data.getYearLevel());
            ps.setString(6, base64Image);
                
              ps.executeUpdate();  
   
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }
    public boolean StudentInfo(StudentModel data){
        try {
            String sql = "SELECT * FROM student_information WHERE Student_ID = ? AND DateDeleted is null";
            ps = prepareStatement(sql);
            
            ps.setInt(1, data.getStudentID());
            rs = ps.executeQuery();
            while (rs.next()) {
               
               data.setStudentID(rs.getInt("Student_ID"));
               data.setFirstName(rs.getString("FirstName"));
               data.setLastName(rs.getString("LastName"));
               data.setCourse(rs.getString("Course"));
               data.setYearLevel(rs.getString("YearLevel"));
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
           
        }
         return false;
    }
  public boolean existingStudent(StudentModel data) { 
    String sql = "SELECT Student_ID FROM student_information WHERE Student_ID = ? and DateDeleted IS  NULL";
    try (PreparedStatement ps = prepareStatement(sql)) {
        ps.setInt(1, data.getStudentID());
        try (ResultSet rs = ps.executeQuery()) {
            // Check if the result set has any rows
            if (rs.next()) {
                return true; // Student exists
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Student does not exist
}
      public void UpdateStudentInfo(StudentModel data){
        try {
            String sql="UPDATE student_information SET firstName = ?,lastName = ?,Course = ?,YearLevel = ? WHERE Student_ID = ?";
         ps =   prepareStatement(sql);
              BufferedImage bufferedImage = convertImageIconToBufferedImage((ImageIcon) data.geteSignature());

            // Convert to base64 string
            
            String base64Image = encodeImageToBase64(bufferedImage);
            
            ps.setString(1, data.getFirstName());
            ps.setString(2,data.getLastName());
            ps.setString(3, data.getCourse());
            ps.setString(4, data.getYearLevel());
            ps.setInt(5, data.getStudentID());
            
            ps.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
        public void DeleteStudentInfo(StudentModel data){
        try {
            String sql="UPDATE  student_information SET DateDeleted = CURRENT_DATE WHERE Student_ID = ?";
           ps= prepareStatement(sql);
            ps.setInt(1, data.getStudentID());
            ps.executeUpdate();;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

         public void PopulateStudentInfo(JTable table){
            try {
                String sql="Select Student_ID,FirstName,LastName,Course,YearLevel,ESignature FROM student_information WHERE DateDeleted IS NULL ORDER BY Course ASC, YearLevel ASC, LastName ASC";
                ps = prepareStatement(sql);
                rs = ps.executeQuery();
                 DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                while (rs.next()) {                    
                      Vector<Object> v = new Vector<>();
               String base64Image = rs.getString("ESignature");

                 ImageIcon imageicon = base64ToImageIcon(base64Image, 250, 70);
                 
                 
                  v.add(rs.getInt("Student_ID"));
                  v.add(rs.getString("LastName"));
                  v.add(rs.getString("FirstName"));
                  v.add(rs.getString("Course"));
                  v.add(rs.getString("YearLevel"));
                  v.add(imageicon);
                  
                  model.addRow(v);
                
                }
                
                
               table.getColumnModel().getColumn(5).setCellRenderer(new ImageIconCellRenderer());
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
             public void SearchStudentInfo(JTable table,String search){
                 String searchStudentInfo = "%"+search+"%";
            try {
                String sql="Select Student_ID,FirstName,LastName,Course,YearLevel,ESignature FROM student_information WHERE DateDeleted IS NULL AND (Student_ID LIKE ? OR LastName LIKE ?) ORDER BY Course ASC, YearLevel ASC, LastName ASC";
                ps = prepareStatement(sql);
                ps.setString(1, searchStudentInfo);
                ps.setString(2, searchStudentInfo);
                
                rs = ps.executeQuery();
                 DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                while (rs.next()) {                    
                      Vector<Object> v = new Vector<>();
               String base64Image = rs.getString("ESignature");

                 ImageIcon imageicon = base64ToImageIcon(base64Image, 250, 70);

                  v.add(rs.getInt("Student_ID"));
                  v.add(rs.getString("LastName"));
                  v.add(rs.getString("FirstName"));
                  v.add(rs.getString("Course"));
                  v.add(rs.getString("YearLevel"));
                  v.add(imageicon);
                  
                  model.addRow(v);
                
                }
                
                
               table.getColumnModel().getColumn(5).setCellRenderer(new ImageIconCellRenderer());
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
     private Connection getConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            return databaseConnection.getCConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PreparedStatement prepareStatement(String sql) {
        try {
            Connection con = getConnection();
            if (con != null) {
                return con.prepareStatement(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
      private ImageIcon base64ToImageIcon(String base64String, int width, int height) {
    if (base64String == null || base64String.isEmpty()) {
        System.err.println("Base64 string is null or empty");
        return null;
    }

    try {
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        
        if (bufferedImage == null) {
            System.err.println("BufferedImage is null after reading the image bytes");
            return null;
        }

        Image scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    } catch (IllegalArgumentException e) {
        System.err.println("Base64 decoding failed: " + e.getMessage());
    } catch (IOException e) {
        System.err.println("Image reading failed: " + e.getMessage());
    }
    return null;
}
    private BufferedImage convertImageIconToBufferedImage(ImageIcon imageIcon) {
    Image image = imageIcon.getImage();
    int originalWidth = image.getWidth(null);
    int originalHeight = image.getHeight(null);

    // Set new dimensions, maintaining the aspect ratio
    int newWidth = 500;
    int newHeight = (newWidth * originalHeight) / originalWidth;

    // Create a buffered image with the new dimensions
    BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

    // Draw the scaled image
    Graphics2D g2d = bufferedImage.createGraphics();
    g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
    g2d.dispose();

    return bufferedImage;
}

 private BufferedImage convertToRGB(BufferedImage image) {
    BufferedImage rgbImage = new BufferedImage(
            image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = rgbImage.createGraphics();
    g2d.drawImage(image, 0, 0, null);
    g2d.dispose();
    return rgbImage;
}
    private String encodeImageToBase64(BufferedImage bufferedImage) throws IOException {
    // Convert ARGB image to RGB
    BufferedImage rgbImage = convertToRGB(bufferedImage);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
    ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
    writer.setOutput(ios);

    ImageWriteParam param = writer.getDefaultWriteParam();
    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    param.setCompressionQuality(0.7f); // Adjust quality from 0.0 to 1.0

    writer.write(null, new IIOImage(rgbImage, null, null), param);
    writer.dispose();

    byte[] imageInByte = baos.toByteArray();
    return Base64.getEncoder().encodeToString(imageInByte);
}
      class ImageIconCellRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
            setText("");
        } else {
            setText((value == null) ? "" : value.toString());
            setIcon(null);
        }
        setHorizontalAlignment(SwingConstants.CENTER); // Center horizontally
        setVerticalAlignment(SwingConstants.CENTER); // Center vertically
    }
}
}
