
package Trackify.Swing;

import Trackify.Event.EventMenuSelected;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.SwingUtilities;


public class MenuItem extends javax.swing.JPanel {

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

   
    public boolean isSelected() {
        return selected;
    }

  
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
   private final List<EventMenuSelected>events = new ArrayList<>();
   private int index;
   private boolean selected;
   private boolean mouseOver;
    public MenuItem(Icon icon,String name,int index) {
     
        initComponents();
        setOpaque(false);
        this.index = index;
        lbIcon.setIcon(icon);
        lbMenuName.setText(name);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               mouseOver =true;
               Component component = e.getComponent(); // Get the component that the mouse entered
        component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
             mouseOver = false;
              Component component = e.getComponent();
    component.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (mouseOver) {
                        setSelected(true);
                        repaint();
                        runEvent();
                    }
                }
            }
        
        
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(22,29,111));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.fillRect(0, 0, getWidth(), getHeight());
             g2.setComposite(AlphaComposite.SrcOver);
              g2.setColor(new Color(245,245,245));
              g2.fillRect(0, 0, 2,getHeight());
        }
        super.paintComponent(g); 
    }

    private void runEvent(){
        for (EventMenuSelected event : events) {
            event.selected(getIndex());
        }
    }
    public void addEvent(EventMenuSelected event){
      events.add(event);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbMenuName = new javax.swing.JLabel();

        lbMenuName.setBackground(new java.awt.Color(255, 255, 255));
        lbMenuName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbMenuName.setForeground(new java.awt.Color(102, 102, 102));
        lbMenuName.setText("MenuName");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbMenuName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbMenuName, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbMenuName;
    // End of variables declaration//GEN-END:variables
}
