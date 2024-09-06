
package Trackify.Models;

import Trackify.Models.other.EventImageModel;
import java.time.LocalTime;
import javax.swing.Icon;


public class StudentLogsModel {

    /**
     * @return the Afternoon_Signature
     */
    public Icon getAfternoon_Signature() {
        return Afternoon_Signature;
    }

    /**
     * @param Afternoon_Signature the Afternoon_Signature to set
     */
    public void setAfternoon_Signature(Icon Afternoon_Signature) {
        this.Afternoon_Signature = Afternoon_Signature;
    }

    /**
     * @return the Morning_Signature
     */
    public Icon getMorning_Signature() {
        return Morning_Signature;
    }

    /**
     * @param Morning_Signature the Morning_Signature to set
     */
    public void setMorning_Signature(Icon Morning_Signature) {
        this.Morning_Signature = Morning_Signature;
    }

   
    public String getEventTitle() {
        return EventTitle;
    }

    public void setEventTitle(String EventTitle) {
        this.EventTitle = EventTitle;
    }

    public int getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(int Student_ID) {
        this.Student_ID = Student_ID;
    }

    public String getStudentFullName() {
        return StudentFullName;
    }

    public void setStudentFullName(String StudentFullName) {
        this.StudentFullName = StudentFullName;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public String getYearLevel() {
        return YearLevel;
    }

    public void setYearLevel(String YearLevel) {
        this.YearLevel = YearLevel;
    }

    public LocalTime getMorning_Time_in() {
        return Morning_Time_in;
    }

  
    public void setMorning_Time_in(LocalTime Morning_Time_in) {
        this.Morning_Time_in = Morning_Time_in;
    }

   
    public LocalTime getMorning_Time_out() {
        return Morning_Time_out;
    }

   
    public void setMorning_Time_out(LocalTime Morning_Time_out) {
        this.Morning_Time_out = Morning_Time_out;
    }


    public LocalTime getAfternoon_Time_in() {
        return Afternoon_Time_in;
    }

    public void setAfternoon_Time_in(LocalTime Afternoon_Time_in) {
        this.Afternoon_Time_in = Afternoon_Time_in;
    }

    public LocalTime getAfternoon_Time_out() {
        return Afternoon_Time_out;
    }

    public void setAfternoon_Time_out(LocalTime Afternoon_Time_out) {
        this.Afternoon_Time_out = Afternoon_Time_out;
    }

    public StudentLogsModel(String EventTitle, int Student_ID, String StudentFullName, String Course, String YearLevel, LocalTime Morning_Time_in, LocalTime Morning_Time_out, Icon Morning_Signature, LocalTime Afternoon_Time_in, LocalTime Afternoon_Time_out, Icon Afternoon_Signature) {
        this.EventTitle = EventTitle;
        this.Student_ID = Student_ID;
        this.StudentFullName = StudentFullName;
        this.Course = Course;
        this.YearLevel = YearLevel;
        this.Morning_Time_in = Morning_Time_in;
        this.Morning_Time_out = Morning_Time_out;
        this.Morning_Signature = Morning_Signature;
        this.Afternoon_Time_in = Afternoon_Time_in;
        this.Afternoon_Time_out = Afternoon_Time_out;
        this.Afternoon_Signature = Afternoon_Signature;
    }


    public StudentLogsModel() {
    }
    
    private String EventTitle;
    private int Student_ID;
    private String StudentFullName;
    private String Course;
    private String YearLevel;
    private LocalTime Morning_Time_in;
    private LocalTime Morning_Time_out;
    private Icon Morning_Signature;
    private LocalTime Afternoon_Time_in;
    private LocalTime Afternoon_Time_out;
    private Icon Afternoon_Signature;
    
}
