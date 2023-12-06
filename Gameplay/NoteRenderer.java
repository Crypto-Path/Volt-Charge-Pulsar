package Gameplay;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NoteRenderer extends JButton {

    public JFrame frame;

    public NoteRenderer(JFrame frame) {
        this.frame = frame;
    }
    
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw note
        /*
         * Get note data
         * Offset note position by audio progress
         * Get first 3 notes in each direction
         * Draw rounded square at note position
         */

        super.paintComponent(g);
    }

    public static void main(String[] args) {

    }
}
