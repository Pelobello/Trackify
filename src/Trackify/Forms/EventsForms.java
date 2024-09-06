/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Trackify.Forms;


import Trackify.Controller.EventsController;
import Trackify.Event.EventItem;
import Trackify.FormsPopup.EventsForm;
import Trackify.Items.EventItems;
import Trackify.Models.EventsModel;
import Trackify.Models.other.EventImageModel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.demo.simple.SimpleMessageModal;
import raven.modal.listener.ModalCallback;
import raven.modal.listener.ModalController;
import raven.modal.option.Location;
import raven.modal.option.Option;

/**
 *
 * @author USER
 */
public class EventsForms extends javax.swing.JPanel {

 

   private EventItem event;
    public EventsForms() throws ParseException, SQLException {
        initComponents();
        setOpaque(false);
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search event");
        TestData();
          searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("Trackify/Icons/search.svg"));
    }

    public void AddEvents(EventsModel data){
        EventItems eventItems = new EventItems();
        eventItems.setData(data);
        eventItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    event.itemClick(eventItems, data);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
              eventItems.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              eventItems.setToolTipText("Click To update or delete");
            }

            @Override
            public void mouseExited(MouseEvent e) {
               eventItems.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
 
        });
        
        responsivePanelItem.add(eventItems);
        repaint();
        revalidate();
        
        
    }
    public void setSelected(Component item){
        for (Component com : responsivePanelItem.getComponents()) {
            EventItems i = (EventItems)com;
            if (i.isSelected()) {
                i.setSelected(true);
                
            }
        }
        ((EventItems)item).setSelected(true);
    }
   
    public EventItem getEvent() {
        return event;
    }

  
    public void setEvent(EventItem event) {
        this.event = event;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        responsivePanelItem = new Trackify.Swing.ResponsivePanelItem();
        searchField = new javax.swing.JTextField();

        jButton1.setText("ADD EVENTS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        responsivePanelItem.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(responsivePanelItem);

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchField)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      AddEvent();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
       try {
           SearchEvent(searchField.getText());
       } catch (ParseException ex) {
           Logger.getLogger(EventsForms.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(EventsForms.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_searchFieldKeyReleased
  private void AddEvent() {
       
        EventsForm eventsForm = new EventsForm();
        EventsController eventsController = new EventsController();
        
        Option option = ModalDialog.createOption();
        
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.RIGHT, Location.TOP)
                .setAnimateDistance(0.7f, 0);
       
          SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("Add Event", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("Cancel", SimpleModalBorder.CANCEL_OPTION)};
        ModalDialog.showModal(this, new SimpleModalBorder(
                eventsForm, "Create", options,
                (controller, action) -> {
                    if (action==SimpleModalBorder.YES_OPTION) {
                        if (eventsForm.noDataInput()) {
                              Toast.show(this, Toast.Type.WARNING, "Please fill out all fields!");
                              controller.consume();
                        }else{
                              try {
                            eventsController.AddEvents(eventsForm.getEventModel());
                            TestData();
                        } catch (Exception e) {
                          e.printStackTrace();
                        }
                             Toast.show(this, Toast.Type.SUCCESS, "Event has been Added");
                        }
                      
                       
                        
//                         controller.consume();
                    }else{
                        controller.close();
                    }
        
                }), option);
    }
  private void Update_Delete_Events(EventsModel data) {
       
        EventsForm eventsForm = new EventsForm();
        eventsForm.setData(data);
        EventsController eventsController = new EventsController();
        
        Option option = ModalDialog.createOption();
        Option option2 = ModalDialog.createOption();
         option2.getLayoutOption().setLocation(Location.CENTER, Location.CENTER);
        
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.RIGHT, Location.TOP)
                .setAnimateDistance(0.7f, 0);
       
          SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("UPDATE", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("DELETE", SimpleModalBorder.CANCEL_OPTION)};
        ModalDialog.showModal(this, new SimpleModalBorder(
                eventsForm, "Create", options,
                (controller, action) -> {
                    if (action==SimpleModalBorder.YES_OPTION) {
                        if (eventsForm.noDataInput()) {
                            Toast.show(this, Toast.Type.WARNING, "Please fill out all fields!");
                            controller.consume();
                            
                        }else{
                            try {
                            eventsController.UpdateEvents(eventsForm.getEventModel());
                            Toast.show(this, Toast.Type.SUCCESS, "Event has been Updated");
                            TestData();
                        } catch (IOException ex) {
                            Logger.getLogger(EventsForms.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(EventsForms.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(EventsForms.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                        }
         
                    }else if (action==SimpleModalBorder.CANCEL_OPTION) {
                        controller.close();
                         ModalDialog.showModal(this, new SimpleMessageModal(SimpleMessageModal.Type.WARNING, "Are you sure you want to Delete this Event information?", 
                         "Delete Info", SimpleModalBorder.YES_NO_OPTION, new ModalCallback() {
                             
                             @Override
                             public void action(ModalController mc, int i) {
                                 if (i == SimpleModalBorder.YES_OPTION) {
                                      try {
                            eventsController.DeleteEvents(data);
//                             Toast.show(null, Toast.Type.SUCCESS, "Event Succesfully Deleted");
                                       
                            mc.close();
                           JOptionPane.showMessageDialog(null, "Succesfully Deleted");
                        
                            TestData();
                        } catch (ParseException ex) {
                            Logger.getLogger(EventsForms.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(EventsForms.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                                 }
                            
                             }
                         }),option2
                        
                         );           
                        
                    }
{
     
                    }
        
                }), option);
    }
   
  
        private void TestData() throws ParseException, SQLException{
            this.setEvent(new EventItem() {
                @Override
                public void itemClick(Component com, EventsModel eventsModel) {
                    Update_Delete_Events(eventsModel);
                    EventsController eventsController = new EventsController();
                    
                    
                } 
            });
          responsivePanelItem.removeAll();
            EventsController controller = new EventsController();
          
            List<EventsModel> eventsList = controller.PopulateEvents();
    
    
              for (EventsModel event : eventsList) {
                  AddEvents(event);
                 
                }
               repaint();
                  revalidate();
            
        }
        
        
           private void SearchEvent(String search) throws ParseException, SQLException{
            this.setEvent(new EventItem() {
                @Override
                public void itemClick(Component com, EventsModel eventsModel) {
                    Update_Delete_Events(eventsModel);
                    EventsController eventsController = new EventsController();
                    
                    
                } 
            });
          responsivePanelItem.removeAll();
            EventsController controller = new EventsController();
          
            List<EventsModel> eventsList = controller.SearcheEvents(search);
    
    
              for (EventsModel event : eventsList) {
                  AddEvents(event);
                 
                }
               repaint();
                  revalidate();
            
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private Trackify.Swing.ResponsivePanelItem responsivePanelItem;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
