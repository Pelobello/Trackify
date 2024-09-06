/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Swing;

import java.awt.Dimension;
import javax.swing.JPanel;
import raven.modal.demo.layout.ResponsiveLayout;


public class ResponsivePanelItem extends JPanel{

    public ResponsivePanelItem() {
        
        setLayout(new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT,new Dimension(-1,-1),2,2));
      
    }

}
