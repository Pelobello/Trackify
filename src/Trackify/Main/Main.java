/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Trackify.Main;

import Trackify.Component.Header;
import Trackify.Component.Menu;
import Trackify.Event.EventMenuSelected;
import Trackify.Forms.EventsForms;
import Trackify.Forms.StudentLogsDataForm;
import Trackify.Forms.StudentInfoForm;
import Trackify.Main.others.MainFrame;
import Trackify.Model.ModelMenu;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class Main extends javax.swing.JFrame {

    private Menu menu = new Menu();
    private JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
   
    public Main() throws ParseException, SQLException {
        initComponents();
        setBackground(Color.WHITE);
        init();
    }

   private void init() throws ParseException, SQLException{
       layout = new MigLayout("fill","0[]0[]0","0[fill]0");
       body.setLayout(layout);
       main.setOpaque(false);
     
       main.setLayout(new BorderLayout());
       menu.addEventLogout(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("Logout");
               MainFrame mainFrame = new MainFrame();
               mainFrame.setVisible(true);
               dispose();
           }
       });
       menu.addEventMenu(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (!animator.isRunning()) {
                   animator.start();
               }
           }
       });
      menu.setEvent(new EventMenuSelected() {
           @Override
           public void selected(int index) {
               if (index==0) {
                   try {
                       showForms(new EventsForms());
                   } catch (ParseException ex) {
                       Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }else if (index==1) {
                   try {
                       showForms(new StudentLogsDataForm());
                   } catch (SQLException ex) {
                       Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }else if (index==2) {
                   showForms(new StudentInfoForm());
               }
           }
      });
       menu.addMenu(new ModelMenu("Event", new ImageIcon(getClass().getResource("/Trackify/Icons/event.png"))));
       menu.addMenu(new ModelMenu("Log Records", new ImageIcon(getClass().getResource("/Trackify/Icons/records.png"))));
       menu.addMenu(new ModelMenu("Student", new ImageIcon(getClass().getResource("/Trackify/Icons/student.png"))));
       
       body.add(menu,"w 40!");
       body.add(main,"w 100%");
       TimingTarget target = new TimingTargetAdapter(){
           @Override
           public void timingEvent(float fraction) {
            double width;
               if (menuShow) {
                   width = 40 +(150*(1f-fraction));
                   menu.setAlpha(1f-fraction);
                   
               }else{
                    width = 40 +(150*fraction);
                    menu.setAlpha(fraction);
               }
               layout.setComponentConstraints(menu, "w " + width + "!");
               body.revalidate();
           }

           @Override
           public void end() {
             menuShow = !menuShow;
           }
           
       };
              
       animator = new Animator(400,target);
       animator.setResolution(0);
       animator.setAcceleration(0.5f);
       animator.setDeceleration(0.5f);
       showForms(new EventsForms());
   }
   private void showForms(Component com){
       main.removeAll();
       main.add(com);
       main.repaint();
       main.revalidate();
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        body.setBackground(new java.awt.Color(249, 249, 249));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         FlatIntelliJLaf.registerCustomDefaultsSource("Trackify/Themes");
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
