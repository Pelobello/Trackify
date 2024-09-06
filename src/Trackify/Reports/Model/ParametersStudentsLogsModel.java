/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Reports.Model;

import java.util.List;

/**
 *
 * @author USER
 */
public class ParametersStudentsLogsModel {

 
    public String getEventTitle() {
        return EventTitle;
    }

  
    public void setEventTitle(String EventTitle) {
        this.EventTitle = EventTitle;
    }

   
    public List<FieldStudentsLogsModel> getFields() {
        return fields;
    }


    public void setFields(List<FieldStudentsLogsModel> fields) {
        this.fields = fields;
    }

    public ParametersStudentsLogsModel(String EventTitle, List<FieldStudentsLogsModel> fields) {
        this.EventTitle = EventTitle;
        this.fields = fields;
    }

    public ParametersStudentsLogsModel() {
    }
     private String EventTitle;
     private List<FieldStudentsLogsModel>fields;
}
