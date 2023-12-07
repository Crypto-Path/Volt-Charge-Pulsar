package Gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.jar.Attributes.Name;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NoteRenderer extends JButton {

    public JFrame frame;
    private NoteRenderer[] notes;

    public NoteRenderer(JFrame frame) {
        this.frame = frame;
        this.notes = notes;
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

        for (NoteRenderer note : notes) {
            int x = note.getX();
            int y = note.getY();
            String direction = note.getDirection();

            // Draw a rounded rectangle for each note
            g2.setColor(Color.BLUE); // Set the color as needed
            g2.fillRoundRect(x, y, 30, 30, 10, 10);

            // Draw an arrow or any indicator based on the note direction
            g2.setColor(Color.BLACK);
            switch (direction) {
                case "left":
                    g2.drawLine(x + 15, y + 15, x + 5, y + 15);
                    g2.drawLine(x + 5, y + 15, x + 10, y + 10);
                    g2.drawLine(x + 5, y + 15, x + 10, y + 20);
                    break;
                case "right":
                    g2.drawLine(x + 15, y + 15, x + 25, y + 15);
                    g2.drawLine(x + 25, y + 15, x + 20, y + 10);
                    g2.drawLine(x + 25, y + 15, x + 20, y + 20);
                    break;
                case "top":
                    g2.drawLine(x + 15, y + 15, x + 15, y + 5);
                    g2.drawLine(x + 15, y + 5, x + 10, y + 10);
                    g2.drawLine(x + 15, y + 5, x + 20, y + 10);
                    break;
                case "bottom":
                    g2.drawLine(x + 15, y + 15, x + 15, y + 25);
                    g2.drawLine(x + 15, y + 25, x + 10, y + 20);
                    g2.drawLine(x + 15, y + 25, x + 20, y + 20);
                    break;
                default:
                    // Handle other directions or provide a default indicator
            }
        }

        super.paintComponent(g);
    }

    private String getDirection() {
        return null;
    }

    public static void main(String[] args) {

    }
}
