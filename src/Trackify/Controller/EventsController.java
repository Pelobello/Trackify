/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Controller;

import Trackify.Database.DatabaseConnection;
import Trackify.Models.EventsModel;
import Trackify.Models.other.EventImageModel;
import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.protocol.Resultset;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import net.coobird.thumbnailator.Thumbnails;
import java.sql.ResultSet;

public class EventsController {
   private PreparedStatement ps;
   private ResultSet rs;
    public EventsController() {
        
    }
    public List<EventsModel> PopulateEvents() throws SQLException {
    List<EventsModel> list = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT * FROM events_information WHERE DateDeleted IS NULL ORDER BY ABS(DATEDIFF(EventSchedule, CURDATE()))";
        ps = prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) { 
            // Convert Blob to Image/Icon
            Blob blob = (Blob) rs.getBlob("EventImage");
            ImageIcon icon = null;
            
            if (blob != null) {
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                icon = new ImageIcon(imageBytes);
            }else{
                icon = new ImageIcon(getClass().getResource("/Trackify/Icons/DefaultImage.png"));
            }
            // Create EventImageModel with the icon
            EventImageModel eventImageModel = new EventImageModel();
            eventImageModel.setIcon(icon);

            // Create EventsModel object
            EventsModel data = new EventsModel(
                rs.getString("EventID"),
                rs.getString("EventTitle"),
                rs.getDate("EventSchedule"),
                rs.getDate("EventDeadline"),
                eventImageModel
            );

            // Add the EventsModel object to the list
            list.add(data);
        }
    } finally {
        // Close ResultSet and PreparedStatement if they are not null
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    return list;
}
    public void AddEvents(EventsModel data)throws IOException{
        try {
            String sql ="INSERT INTO events_information (EventID,EventTitle,EventSchedule,EventDeadline,EventImage)VALUES(?,?,?,?,?)";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedSched = dateFormat.format(data.getEventSchedule());
            String formattedDeadline = dateFormat.format(data.getEventSchedule());
            
            
            ps = prepareStatement(sql);
            ps.setString(1, data.getEventID().toUpperCase());
            ps.setString(2, data.getTitle().toUpperCase());
            ps.setDate(3, (Date) data.getEventSchedule());
            ps.setDate(4, (Date) data.getEventDeadline());
            if (data.getEventImage()!=null) {
                ps.setBytes(5, getByteImage(data.getEventImage().getPath()));
            }else{
                ps.setBytes(5, null);
            }
    
            ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void UpdateEvents(EventsModel data)throws IOException{
        try {
             // Correctly determine if we are editing the image
        boolean isEditEventsImage = data.getEventImage() != null;

        // Define SQL based on whether we are editing the image
        String sql = isEditEventsImage 
            ? "UPDATE events_information SET EventTitle = ?, EventSchedule = ?, EventDeadline = ?, EventImage = ?, DateUpdated = CURRENT_TIMESTAMP WHERE EventID = ?"
            : "UPDATE events_information SET EventTitle = ?, EventSchedule = ?, EventDeadline = ?, DateUpdated = CURRENT_TIMESTAMP WHERE EventID = ?";

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedSched = dateFormat.format(data.getEventSchedule());
            String formattedDeadline = dateFormat.format(data.getEventSchedule());
            
            
            ps = prepareStatement(sql);
          
            ps.setString(1, data.getTitle().toUpperCase());
            ps.setDate(2, (Date) data.getEventSchedule());
            ps.setDate(3, (Date) data.getEventDeadline());
            if (isEditEventsImage) {
                 if (data.getEventImage()!=null) {
                ps.setBytes(4, getByteImage((File) data.getEventImage().getPath()));
            }else{
                ps.setBytes(4, null);
            }
                    ps.setString(5, data.getEventID());
            }else{
                   ps.setString(4, data.getEventID());
            }
           

            ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DeleteEvents(EventsModel data){
        try {
            String sql = "UPDATE events_information SET DateDeleted = CURRENT_TIMESTAMP WHERE EventID = ?";
            ps = prepareStatement(sql);
            
            ps.setString(1, data.getEventID());
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public List<EventsModel> PopulateToMainFrame() {
    List<EventsModel> list = new ArrayList<>();
    try {
        String sql = "SELECT * \n" +
"FROM trackify_db.events_information\n" +
"WHERE DateDeleted IS NULL\n" +
"AND CURDATE() BETWEEN EventSchedule AND EventDeadline;";

        ps = prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {                
            EventsModel data = new EventsModel();
            data.setTitle(rs.getString("EventTitle"));
            data.setEventSchedule(rs.getTimestamp("EventSchedule"));
            data.setEventDeadline(rs.getTimestamp("EventDeadline"));
            list.add(data);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
public List<EventsModel> PopulateToStudentsLogs() {
    List<EventsModel> list = new ArrayList<>();
    try {
        String sql = "SELECT * \n" +
"FROM events_information \n" +
"ORDER BY EventSchedule = CURDATE() DESC, EventSchedule;";

        ps = prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {                
            EventsModel data = new EventsModel();
            data.setTitle(rs.getString("EventTitle"));
            data.setEventSchedule(rs.getTimestamp("EventSchedule"));
            data.setEventDeadline(rs.getTimestamp("EventDeadline"));
            list.add(data);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
    
     public List<EventsModel> SearcheEvents(String search) throws SQLException {
    List<EventsModel> list = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT * FROM events_information WHERE (EventTitle LIKE ? OR EventID LIKE ?)AND DateDeleted IS NULL ORDER BY ABS(DATEDIFF(EventSchedule, CURDATE()))";
        ps = prepareStatement(sql);
         ps.setString(1, "%" + search + "%");
        ps.setString(2, "%" + search + "%");
        rs = ps.executeQuery();
        
        while (rs.next()) { 
            // Convert Blob to Image/Icon
            Blob blob = (Blob) rs.getBlob("EventImage");
            ImageIcon icon = null;
            
            if (blob != null) {
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                icon = new ImageIcon(imageBytes);
            }else{
                icon = new ImageIcon(getClass().getResource("/Trackify/Icons/DefaultImage.png"));
            }
            // Create EventImageModel with the icon
            EventImageModel eventImageModel = new EventImageModel();
            eventImageModel.setIcon(icon);

            // Create EventsModel object
            EventsModel data = new EventsModel(
                rs.getString("EventID"),
                rs.getString("EventTitle"),
                rs.getDate("EventSchedule"),
                rs.getDate("EventDeadline"),
                eventImageModel
            );

            // Add the EventsModel object to the list
            list.add(data);
        }
    } finally {
        // Close ResultSet and PreparedStatement if they are not null
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    return list;
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
     private byte[] getByteImage(File file) throws IOException {
        BufferedImage image = Thumbnails.of(file)
                .width(500)
                .outputQuality(0.7f)
                .asBufferedImage();
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", out);
            byte[] data = out.toByteArray();
            return data;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
  
}
