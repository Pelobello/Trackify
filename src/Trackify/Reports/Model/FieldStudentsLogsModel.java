/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Reports.Model;

import java.io.InputStream;
import java.time.LocalTime;

/**
 *
 * @author USER
 */
public class FieldStudentsLogsModel {

    /**
     * @return the afternoontimein
     */
    public LocalTime getAfternoontimein() {
        return afternoontimein;
    }

    /**
     * @param afternoontimein the afternoontimein to set
     */
    public void setAfternoontimein(LocalTime afternoontimein) {
        this.afternoontimein = afternoontimein;
    }

    /**
     * @return the studentid
     */
    public int getStudentid() {
        return studentid;
    }

    /**
     * @param studentid the studentid to set
     */
    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    /**
     * @return the studentfullname
     */
    public String getStudentfullname() {
        return studentfullname;
    }

    /**
     * @param studentfullname the studentfullname to set
     */
    public void setStudentfullname(String studentfullname) {
        this.studentfullname = studentfullname;
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * @return the yearlevel
     */
    public String getYearlevel() {
        return yearlevel;
    }

    /**
     * @param yearlevel the yearlevel to set
     */
    public void setYearlevel(String yearlevel) {
        this.yearlevel = yearlevel;
    }

    /**
     * @return the morningtimein
     */
    public LocalTime getMorningtimein() {
        return morningtimein;
    }

    /**
     * @param morningtimein the morningtimein to set
     */
    public void setMorningtimein(LocalTime morningtimein) {
        this.morningtimein = morningtimein;
    }

    /**
     * @return the morningtimeout
     */
    public LocalTime getMorningtimeout() {
        return morningtimeout;
    }

    /**
     * @param morningtimeout the morningtimeout to set
     */
    public void setMorningtimeout(LocalTime morningtimeout) {
        this.morningtimeout = morningtimeout;
    }

    /**
     * @return the morningsignature
     */
    public InputStream getMorningsignature() {
        return morningsignature;
    }

    /**
     * @param morningsignature the morningsignature to set
     */
    public void setMorningsignature(InputStream morningsignature) {
        this.morningsignature = morningsignature;
    }

    /**
     * @return the afternoontimeon
     */


    /**
     * @return the afternoontimeout
     */
    public LocalTime getAfternoontimeout() {
        return afternoontimeout;
    }

    /**
     * @param afternoontimeout the afternoontimeout to set
     */
    public void setAfternoontimeout(LocalTime afternoontimeout) {
        this.afternoontimeout = afternoontimeout;
    }

    /**
     * @return the afternoonsignature
     */
    public InputStream getAfternoonsignature() {
        return afternoonsignature;
    }

    /**
     * @param afternoonsignature the afternoonsignature to set
     */
    public void setAfternoonsignature(InputStream afternoonsignature) {
        this.afternoonsignature = afternoonsignature;
    }

    public FieldStudentsLogsModel(int studentid, String studentfullname, String course, String yearlevel, LocalTime morningtimein, LocalTime morningtimeout, InputStream morningsignature, LocalTime afternoontimein, LocalTime afternoontimeout, InputStream afternoonsignature) {
        this.studentid = studentid;
        this.studentfullname = studentfullname;
        this.course = course;
        this.yearlevel = yearlevel;
        this.morningtimein = morningtimein;
        this.morningtimeout = morningtimeout;
        this.morningsignature = morningsignature;
        this.afternoontimein = afternoontimein;
        this.afternoontimeout = afternoontimeout;
        this.afternoonsignature = afternoonsignature;
    }

   

  

    
    public FieldStudentsLogsModel() {
    }
    
    private int studentid;
    private String studentfullname;
    private String course;
    private String yearlevel;
    private LocalTime  morningtimein;
    private LocalTime  morningtimeout;
    private InputStream morningsignature;
    private LocalTime  afternoontimein;
    private LocalTime  afternoontimeout;
     private InputStream afternoonsignature;
}
