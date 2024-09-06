/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Models;

import Trackify.Models.other.EventImageModel;
import java.util.Date;
import javax.swing.Icon;


public class EventsModel {

    /**
     * @return the eventImage
     */
    public EventImageModel getEventImage() {
        return eventImage;
    }

    /**
     * @param eventImage the eventImage to set
     */
    public void setEventImage(EventImageModel eventImage) {
        this.eventImage = eventImage;
    }

  
    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

  
    public String getTitle() {
        return title;
    }

   
    public void setTitle(String title) {
        this.title = title;
    }

    
    public Date getEventSchedule() {
        return eventSchedule;
    }

    
    public void setEventSchedule(Date eventSchedule) {
        this.eventSchedule = eventSchedule;
    }

   
    public Date getEventDeadline() {
        return eventDeadline;
    }

    public void setEventDeadline(Date eventDeadline) {
        this.eventDeadline = eventDeadline;
    }

 
  

   
    public void setEventImage(Icon eventImage) {
        this.setEventImage(eventImage);
    }

    public EventsModel(String eventID, String title, Date eventSchedule, Date eventDeadline, EventImageModel eventImage) {
        this.eventID = eventID;
        this.title = title;
        this.eventSchedule = eventSchedule;
        this.eventDeadline = eventDeadline;
        this.eventImage = eventImage;
    }

    

    public EventsModel() {
    }
  private String eventID;
  private String title;
  private Date eventSchedule;
  private Date eventDeadline;
  private EventImageModel eventImage;
}
