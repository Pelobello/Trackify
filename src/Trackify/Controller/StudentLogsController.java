
package Trackify.Controller;

import Trackify.Database.DatabaseConnection;
import Trackify.Models.EventsModel;
import Trackify.Models.StudentLogsModel;
import Trackify.Models.StudentModel;
import Trackify.Models.other.EventImageModel;
import Trackify.Reports.Model.FieldStudentsLogsModel;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import net.coobird.thumbnailator.Thumbnails;
import raven.modal.Toast;


public class StudentLogsController {
    private PreparedStatement ps;
    private ResultSet rs;
    public StudentLogsController() {
        
    }
public List<StudentLogsModel> PopulateStudentsLogs() throws SQLException {
    List<StudentLogsModel> list = new ArrayList<>();
    String sql = "SELECT * FROM student_logs_information WHERE DateDeleted IS NULL";

    try (PreparedStatement ps = prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String morningSignatureBase64 = rs.getString("Morning_Signature");
            String afternoonSignatureBase64 = rs.getString("Afternoon_Signature");

        
            StudentLogsModel logsModel = new StudentLogsModel();
            // Set the morning icon
            if (morningSignatureBase64 != null) {
                byte[] morningBytes = decodeBase64ToBytes(morningSignatureBase64);
               
                logsModel.setMorning_Signature(new ImageIcon(morningBytes));
            } else {
                logsModel.setMorning_Signature(new ImageIcon(getClass().getResource("/Trackify/Icons/DefaultSignature.png")));
            }

            // Set the afternoon icon
            if (afternoonSignatureBase64 != null) {
                byte[] afternoonBytes = decodeBase64ToBytes(afternoonSignatureBase64);
               
                 logsModel.setAfternoon_Signature(new ImageIcon(afternoonBytes));
            } else {
                 logsModel.setAfternoon_Signature(new ImageIcon(getClass().getResource("/Trackify/Icons/DefaultSignature.png")));
            }

            // Convert java.sql.Time to java.time.LocalTime
            LocalTime morningTimeIn = rs.getTime("Morning_Time_in") != null ? rs.getTime("Morning_Time_in").toLocalTime() : null;
            LocalTime morningTimeOut = rs.getTime("Morning_Time_out") != null ? rs.getTime("Morning_Time_out").toLocalTime() : null;
            LocalTime afternoonTimeIn = rs.getTime("Afternoon_Time_in") != null ? rs.getTime("Afternoon_Time_in").toLocalTime() : null;
            LocalTime afternoonTimeOut = rs.getTime("Afternoon_Time_out") != null ? rs.getTime("Afternoon_Time_out").toLocalTime() : null;

            // Create StudentLogsModel and add it to the list
            StudentLogsModel data = new StudentLogsModel(
                rs.getString("Event_Title"),
                rs.getInt("Student_ID"),
                rs.getString("StudentFullName"),
                rs.getString("Course"),
                rs.getString("YearLevel"),
                morningTimeIn,
                morningTimeOut,
                logsModel.getMorning_Signature(),
                afternoonTimeIn,
                afternoonTimeOut,
                logsModel.getAfternoon_Signature()
            );

            list.add(data);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}


private InputStream convertBase64ToInputStream(String base64Image) throws IOException {
    // Step 1: Decode Base64 to Byte Array
    byte[] imageBytes = decodeBase64ToBytes(base64Image);

    // Step 2: Convert Byte Array to InputStream
    return new ByteArrayInputStream(imageBytes);
}

public List<FieldStudentsLogsModel> GetStudentsLogs(String search, String course, String events) throws SQLException, IOException {
    List<FieldStudentsLogsModel> list = new ArrayList<>();
    String searchStudentInfo = "%" + search + "%";
    
    // SQL query with conditional logic for Course filtering
    String sql = "SELECT *,\n" +
"       FORMAT(Morning_Time_in, 'hh:mm tt') AS Formatted_Morning_Time_in,\n" +
"       FORMAT(Morning_Time_out, 'hh:mm tt') AS Formatted_Morning_Time_out,\n" +
"       FORMAT(Afternoon_Time_in, 'hh:mm tt') AS Formatted_Afternoon_Time_in,\n" +
"       FORMAT(Afternoon_Time_out, 'hh:mm tt') AS Formatted_Afternoon_Time_out\n" +
"FROM trackify_db.student_logs_information\n" +
"WHERE DateDeleted IS NULL \n" +
"  AND (Student_ID LIKE ? OR StudentFullName LIKE ?)\n" +
"  AND Event_Title = ? \n" +
"  AND (? = 'ALL COURSE' OR Course = ?)  \n" +
"ORDER BY StudentFullName ASC;";

    try (PreparedStatement ps = prepareStatement(sql)) {
        // Set parameters
        ps.setString(1, searchStudentInfo);  // Search by ID or name
        ps.setString(2, searchStudentInfo);
        ps.setString(3, events);             // Filter by EventTitle
        ps.setString(4, course);             // Check if course is "ALL COURSE"
        ps.setString(5, course);             // Filter by course if not "ALL COURSE"

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
              String morningSignatureBase64 = rs.getString("Morning_Signature");
            String afternoonSignatureBase64 = rs.getString("Afternoon_Signature");

            InputStream morningInputStream = null;
            InputStream afternoonInputStream = null;

            // Set the morning icon
            if (morningSignatureBase64 != null) {
                morningInputStream = convertBase64ToInputStream(morningSignatureBase64);
            } else {
                morningInputStream = getClass().getResourceAsStream("/Trackify/Icons/DefaultSignature.png");
            }

            // Set the afternoon icon
            if (afternoonSignatureBase64 != null) {
                afternoonInputStream = convertBase64ToInputStream(afternoonSignatureBase64);
            } else {
                afternoonInputStream = getClass().getResourceAsStream("/Trackify/Icons/DefaultSignature.png");
            }
                // Convert java.sql.Time to java.time.LocalTime
               LocalTime morningTimeIn = rs.getTime("Morning_Time_in") != null ? rs.getTime("Morning_Time_in").toLocalTime() : null;
LocalTime morningTimeOut = rs.getTime("Morning_Time_out") != null ? rs.getTime("Morning_Time_out").toLocalTime() : null;
LocalTime afternoonTimeIn = rs.getTime("Afternoon_Time_in") != null ? rs.getTime("Afternoon_Time_in").toLocalTime() : null;
LocalTime afternoonTimeOut = rs.getTime("Afternoon_Time_out") != null ? rs.getTime("Afternoon_Time_out").toLocalTime() : null;

// Format LocalTime to HH:mm a
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
String formattedMorningTimeIn = morningTimeIn != null ? morningTimeIn.format(formatter) : null;
String formattedMorningTimeOut = morningTimeOut != null ? morningTimeOut.format(formatter) : null;
String formattedAfternoonTimeIn = afternoonTimeIn != null ? afternoonTimeIn.format(formatter) : null;
String formattedAfternoonTimeOut = afternoonTimeOut != null ? afternoonTimeOut.format(formatter) : null;

// Create StudentLogsModel and add it to the list
FieldStudentsLogsModel data = new FieldStudentsLogsModel(
    rs.getInt("Student_ID"),
    rs.getString("StudentFullName"),
    rs.getString("Course"),
    rs.getString("YearLevel"),
    formattedMorningTimeIn,
    formattedMorningTimeOut,
    morningInputStream,
    formattedAfternoonTimeIn,
    formattedAfternoonTimeOut,
    afternoonInputStream
);

                list.add(data);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}
public List<StudentLogsModel> SearchStudentsLogs(String search, String course, String events) throws SQLException {
    List<StudentLogsModel> list = new ArrayList<>();
    String searchStudentInfo = "%" + search + "%";
    
    // SQL query with conditional logic for Course filtering
    String sql = "SELECT *,\n" +
"       DATE_FORMAT(Morning_Time_in, '%r') AS Formatted_Morning_Time_in,\n" +
"       DATE_FORMAT(Morning_Time_out, '%r') AS Formatted_Morning_Time_out,\n" +
"       DATE_FORMAT(Afternoon_Time_in, '%r') AS Formatted_Afternoon_Time_in,\n" +
"       DATE_FORMAT(Afternoon_Time_out, '%r') AS Formatted_Afternoon_Time_out\n" +
"FROM trackify_db.student_logs_information\n" +
"WHERE DateDeleted IS NULL \n" +
"  AND (Student_ID LIKE ? OR StudentFullName LIKE ?)\n" +
"  AND Event_Title = ? \n" +
"  AND (? = 'ALL COURSE' OR Course = ?)  \n" +
"ORDER BY StudentFullName ASC";

    try (PreparedStatement ps = prepareStatement(sql)) {
        // Set parameters
        ps.setString(1, searchStudentInfo);  // Search by ID or name
        ps.setString(2, searchStudentInfo);
        ps.setString(3, events);             // Filter by EventTitle
        ps.setString(4, course);             // Check if course is "ALL COURSE"
        ps.setString(5, course);             // Filter by course if not "ALL COURSE"

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String morningSignatureBase64 = rs.getString("Morning_Signature");
                String afternoonSignatureBase64 = rs.getString("Afternoon_Signature");

                StudentLogsModel logsModel = new StudentLogsModel();
                
                // Set the morning icon
                if (morningSignatureBase64 != null) {
                    byte[] morningBytes = decodeBase64ToBytes(morningSignatureBase64);
                    logsModel.setMorning_Signature(new ImageIcon(morningBytes));
                } else {
                    logsModel.setMorning_Signature(new ImageIcon(getClass().getResource("/Trackify/Icons/DefaultSignature.png")));
                }

                // Set the afternoon icon
                if (afternoonSignatureBase64 != null) {
                    byte[] afternoonBytes = decodeBase64ToBytes(afternoonSignatureBase64);
                    logsModel.setAfternoon_Signature(new ImageIcon(afternoonBytes));
                } else {
                    logsModel.setAfternoon_Signature(new ImageIcon(getClass().getResource("/Trackify/Icons/DefaultSignature.png")));
                }

                // Convert java.sql.Time to java.time.LocalTime
                LocalTime morningTimeIn = rs.getTime("Morning_Time_in") != null ? rs.getTime("Morning_Time_in").toLocalTime() : null;
                LocalTime morningTimeOut = rs.getTime("Morning_Time_out") != null ? rs.getTime("Morning_Time_out").toLocalTime() : null;
                LocalTime afternoonTimeIn = rs.getTime("Afternoon_Time_in") != null ? rs.getTime("Afternoon_Time_in").toLocalTime() : null;
                LocalTime afternoonTimeOut = rs.getTime("Afternoon_Time_out") != null ? rs.getTime("Afternoon_Time_out").toLocalTime() : null;

                // Create StudentLogsModel and add it to the list
                StudentLogsModel data = new StudentLogsModel(
                    rs.getString("Event_Title"),
                    rs.getInt("Student_ID"),
                    rs.getString("StudentFullName"),
                    rs.getString("Course"),
                    rs.getString("YearLevel"),
                    morningTimeIn,
                    morningTimeOut,
                    logsModel.getMorning_Signature(),
                    afternoonTimeIn,
                    afternoonTimeOut,
                    logsModel.getAfternoon_Signature()
                );

                list.add(data);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}


  public void MorningTimeIn(StudentLogsModel data, Component com) throws IOException {
    try {
        // Check if the student has already timed in for the event today
        if (CheckNoTimeInLogRecords(data)) {
            String sql = "INSERT INTO student_logs_information (Event_Title,Student_ID,StudentFullName,Course,YearLevel,Morning_Time_in)VALUES(?,?,?,?,?,?)";
            ps = prepareStatement(sql);
            
            ps.setString(1, data.getEventTitle());
            ps.setInt(2, data.getStudent_ID());
            ps.setString(3, data.getStudentFullName());
            ps.setString(4, data.getCourse());
            ps.setString(5, data.getYearLevel());
            
            // Set Morning_Time_in
            LocalTime localTime = data.getMorning_Time_in();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
            ps.setTime(6, sqlTime);
            
            // Convert Morning_Signature to a base64 string
         
            
            // Execute the query
            ps.execute();
            
            // Show success toast
            Toast.show(com, Toast.Type.SUCCESS, "Successfully Timed In");
        } else {
            // Show info toast if already timed in
            Toast.show(com, Toast.Type.INFO, "Already Timed In");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public boolean CheckNoTimeInLogRecords(StudentLogsModel data) {
    try {
        String sql = "SELECT * FROM student_logs_information WHERE Student_ID = ? AND DATE(DateCreated) = CURDATE() AND Morning_Time_in IS NOT NULL AND Event_Title = ?";
        ps = prepareStatement(sql);
        ps.setInt(1, data.getStudent_ID());
        ps.setString(2, data.getEventTitle());
        rs = ps.executeQuery();
        
        return !rs.next(); // Return true if no record found, false otherwise
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
     public void MorningTimeOut(StudentLogsModel data,Component com){
        try {

              if (CheckNoTimeOutLogRecords(data)) {
                  BufferedImage bufferedImage = convertImageIconToBufferedImage((ImageIcon) data.getMorning_Signature());
            String base64Image = encodeImageToBase64(bufferedImage);
            String sql = "UPDATE student_logs_information SET Morning_Time_out = ?,Morning_Signature = ? WHERE Student_ID = ? AND DATE(DateCreated) = CURDATE() AND Morning_Time_out IS NULL AND Morning_Time_in IS NOT NULL";
            ps = prepareStatement(sql);
            ps = prepareStatement(sql);

            // Convert LocalTime to sql.Time
            LocalTime localTime = data.getMorning_Time_out();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
            
            ps.setTime(1, sqlTime);
               
            ps.setString(2, base64Image);
            ps.setInt(3, data.getStudent_ID());
            

            // Execute the update
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                Toast.show(com, Toast.Type.SUCCESS, "Successfully Timed Out");
            } else {
                Toast.show(com, Toast.Type.INFO, "No morning time-in recorded, or you have already timed out.!");
            }
        } else {
            Toast.show(com, Toast.Type.INFO, "Already Timed Out or No Time-In Record Found!");
        }
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
  public boolean CheckNoTimeOutLogRecords(StudentLogsModel data) {
    try {
        String sql = "SELECT * FROM student_logs_information WHERE Student_ID = ? AND DATE(DateCreated) = CURDATE() AND Morning_Time_out IS NULL AND Morning_Time_in IS NOT NULL AND Event_Title = ?";
        ps = prepareStatement(sql);
        ps.setInt(1, data.getStudent_ID());
        ps.setString(2, data.getEventTitle());
        rs = ps.executeQuery();

        return rs.next();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
     }
      
     
     
     
     
      public void AfternoonTimeIn(StudentLogsModel data, Component com) {
    try {
        if (CheckIfAlreadyTimeIn(data)) {
            Toast.show(com, Toast.Type.INFO, "You already Time in!");
            return;
        }
        
        if (!CheckExistingRecord(data)) {  // Check if there's an existing record for today
            // Insert new record if no existing record
            String sql = "INSERT INTO student_logs_information (Event_Title, Student_ID, StudentFullName, Course, YearLevel, Afternoon_Time_in) VALUES (?,?,?,?,?,?)";
            ps = prepareStatement(sql);
            ps.setString(1, data.getEventTitle());
            ps.setInt(2, data.getStudent_ID());
            ps.setString(3, data.getStudentFullName());
            ps.setString(4, data.getCourse());
            ps.setString(5, data.getYearLevel());
            LocalTime localTime = data.getAfternoon_Time_in();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
            ps.setTime(6, sqlTime);
            ps.executeUpdate();
             Toast.show(com, Toast.Type.SUCCESS, "SuccesFully Time in!");
        } else {
            // Update the existing record's Afternoon_Time_in if it is NULL
            UpdateAfternoonTimeIn(data);
             Toast.show(com, Toast.Type.SUCCESS, "SuccesFully Time in!");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public boolean CheckExistingRecord(StudentLogsModel data) {
    try {
        String sql = "SELECT * FROM student_logs_information WHERE Student_ID = ? AND Event_Title = ? AND DATE(DateCreated) = CURDATE()";
        ps = prepareStatement(sql);
        ps.setInt(1, data.getStudent_ID());
        ps.setString(2, data.getEventTitle());
        rs = ps.executeQuery();
        
        return rs.next();  // Return true if a record exists
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

public boolean CheckIfAlreadyTimeIn(StudentLogsModel data) {
    try {
        String sql = "SELECT * FROM student_logs_information WHERE Student_ID = ? AND Event_Title = ? AND DATE(DateCreated) = CURDATE() AND Afternoon_Time_in IS NOT NULL";
        ps = prepareStatement(sql);
        ps.setInt(1, data.getStudent_ID());
        ps.setString(2, data.getEventTitle());
        rs = ps.executeQuery();
        
        return rs.next();  // Return true if the student has already timed in
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

public void UpdateAfternoonTimeIn(StudentLogsModel data) {
    try {
        String sql = "UPDATE student_logs_information SET Afternoon_Time_in = ? WHERE Student_ID = ? AND Event_Title = ? AND DATE(DateCreated) = CURDATE() AND Afternoon_Time_in IS NULL";
        ps = prepareStatement(sql);
        LocalTime localTime = data.getAfternoon_Time_in();
        java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
        ps.setTime(1, sqlTime);
        ps.setInt(2, data.getStudent_ID());
        ps.setString(3, data.getEventTitle());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

       
     public void AfternoonTimeOut(StudentLogsModel data,Component com){
        try {
            if (CheckIfAfternoonIsTimeIn(data)) {
            BufferedImage bufferedImage = convertImageIconToBufferedImage((ImageIcon) data.getMorning_Signature());
            String base64Image = encodeImageToBase64(bufferedImage);
            LocalTime localTime = data.getAfternoon_Time_out();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
            String sql= "UPDATE student_logs_information SET Afternoon_Time_out = ?,Afternoon_Signature = ? WHERE Student_ID = ? AND Event_Title = ? AND DATE(DateCreated) = CURDATE()";
            ps = prepareStatement(sql);
            ps.setTime(1, sqlTime);
            ps.setString(2, base64Image);
            ps.setInt(3, data.getStudent_ID());
            ps.setString(4, data.getEventTitle());
            ps.executeUpdate();
             Toast.show(com, Toast.Type.SUCCESS, "SuccesFully Time out!");
            }else{
                Toast.show(com, Toast.Type.INFO, "Already Time out or No Afternoon Time in!");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     public boolean CheckIfAfternoonIsTimeIn(StudentLogsModel data){
       try {
           
        String sql = "SELECT * FROM student_logs_information WHERE Student_ID = ? AND Event_Title = ? AND Afternoon_Time_in IS NOT NULL AND DATE(DateCreated) = CURDATE() AND Afternoon_Time_out IS NULL";
        ps = prepareStatement(sql);
        ps.setInt(1, data.getStudent_ID());
        ps.setString(2, data.getEventTitle());
        rs = ps.executeQuery();
        
        return rs.next();  // Return true if a record exists
    } catch (Exception e) {
        e.printStackTrace();
        return false;
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
   
public byte[] decodeBase64ToBytes(String base64String) {
    return Base64.getDecoder().decode(base64String);
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
   public String encodeImageToBase64(BufferedImage bufferedImage) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", baos);
    byte[] imageBytes = baos.toByteArray();
    return Base64.getEncoder().encodeToString(imageBytes);
}
  
    
}

