/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Reports;

import Trackify.Reports.Model.ParametersStudentsLogsModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;




public class ReportManager {

      private static final Logger logger = Logger.getLogger(ReportManager.class);
private static ReportManager instance;
private JasperReport StudentsLogsReport;

public static ReportManager getInstance() throws IOException{
    if (instance == null) {
        instance = new ReportManager();
        
    }
        return instance;
    
}

  static {
        Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
    }
    private ReportManager() throws IOException {
         try {
            compileReport();
        } catch (JRException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        
    }
    public void compileReport()throws JRException,IOException{

        StudentsLogsReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Trackify/Reports/StudentLogsReport.jrxml"));
    }
    
    public void printStudentsLogs(ParametersStudentsLogsModel data) throws JRException{
         Map<String,Object> para = new HashMap<>();
         para.put("EventTitle", data.getEventTitle());
         JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
         
         JasperPrint print = JasperFillManager.fillReport(StudentsLogsReport, para,dataSource);
         viewReport(print);
    }
    
       private void viewReport(JasperPrint print)throws JRException {
        JasperViewer.viewReport(print,false);
        
    }
       public void printStudentsLogsToPDF(){
           
       }
}
