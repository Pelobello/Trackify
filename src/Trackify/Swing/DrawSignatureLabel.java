/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trackify.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class DrawSignatureLabel extends JLabel {
    private BufferedImage image;   // Current image to display
    private Graphics2D g2;         // Graphics2D object to draw on the image
    private int prevX, prevY;      // Previous mouse coordinates
    private Stack<BufferedImage> undoStack = new Stack<>(); // Stack to store undo history
    private Stack<BufferedImage> redoStack = new Stack<>(); // Stack to store redo history

    public DrawSignatureLabel() {
        // Initialize an empty BufferedImage with the size of the JLabel
        image = new BufferedImage(550, 200, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        setupGraphics2D(g2);

        // Add mouse listeners for drawing
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                saveToUndoStack(); // Save the current state for undo
                prevX = e.getX();
                prevY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                g2.drawLine(prevX, prevY, x, y); // Draw line from the previous point to the current point
                prevX = x;
                prevY = y;
                repaint(); // Repaint the JLabel to show the drawing
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // Draw the image onto the JLabel
    }

    private void saveToUndoStack() {
        // Save the current state of the image for undo
        BufferedImage savedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g2d = savedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        undoStack.push(savedImage);
        redoStack.clear(); // Clear redo stack after a new action
    }

    public void resetDrawing() {
        // Clear the drawing by filling the image with a transparent color
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        repaint();
        undoStack.clear(); // Clear undo history
        redoStack.clear(); // Clear redo history
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(copyImage(image)); // Save the current state for redo
            image = undoStack.pop(); // Restore the previous state
            g2 = image.createGraphics();
            setupGraphics2D(g2); // Restore the graphics settings
            repaint();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(copyImage(image)); // Save the current state for undo
            image = redoStack.pop(); // Restore the next state
            g2 = image.createGraphics();
            setupGraphics2D(g2); // Restore the graphics settings
            repaint();
        }
    }

    private void setupGraphics2D(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3)); // Set default stroke size
        g2.setColor(Color.BLACK); // Set default drawing color to black
    }

    private BufferedImage copyImage(BufferedImage source) {
        BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        return copy;
    }
   public ImageIcon getDrawingAsIcon(int width, int height) {
    // Resize the BufferedImage to the specified width and height
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
}
   public boolean isDrawingEmpty() {
    // Iterate over each pixel to check if it's all transparent
    for (int x = 0; x < image.getWidth(); x++) {
        for (int y = 0; y < image.getHeight(); y++) {
            if ((image.getRGB(x, y) & 0xFF000000) != 0x00000000) {
                // Found a non-transparent pixel
                return false;
            }
        }
    }
    // All pixels are transparent
    return true;
}
}



//DrawSignatureLabel
