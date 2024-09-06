/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Models;

import javax.swing.Icon;


public class StudentModel {

    /**
     * @return the studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the tudentID
     */


    /**
     * @return the eSignature
     */
    public Icon geteSignature() {
        return eSignature;
    }

    /**
     * @param eSignature the eSignature to set
     */
    public void seteSignature(Icon eSignature) {
        this.eSignature = eSignature;
    }

    public String getFirstName() {
        return firstName;
    }

    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

   
    public String getLastName() {
        return lastName;
    }

    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   
    public String getCourse() {
        return course;
    }

   
    public void setCourse(String course) {
        this.course = course;
    }

   
    public String getYearLevel() {
        return yearLevel;
    }

    
    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public StudentModel(int studentID, String firstName, String lastName, String course, String yearLevel, Icon eSignature) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.yearLevel = yearLevel;
        this.eSignature = eSignature;
    }


    public StudentModel() {
    }
private int studentID;
private String firstName;
private String lastName;
private String course;
private String yearLevel;
private Icon eSignature;

public Object[] toTableRow(int row){
    
    return new Object[]{studentID,lastName,firstName,course,yearLevel};
}

   

}
