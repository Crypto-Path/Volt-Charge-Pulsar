package Gameplay;

import java.awt.Cursor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class NoteRenderer extends JButton {

    public JFrame frame;
    private int[][][] notes;
    private static float songPosition = 0;

    public NoteRenderer(JFrame frame, int[][][] notes) {
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.frame = frame;
        System.out.println("Initiating Renderer: Retrieved frame");
        this.notes = notes;
        System.out.println("Initiating Renderer: Retrieved notes");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int laneIndex = 0; laneIndex < notes.length; laneIndex++) {
            int[][] lane = notes[laneIndex];
            for (int noteIndex = 0; noteIndex < lane.length; noteIndex++) {
                int[] note = lane[noteIndex];
                int position = note[0];
                int longness = note[1];
                DrawNote(g2, position, longness, laneIndex);
            }
        }

        super.paintComponent(g);
    }

    private void DrawNote(Graphics2D g2, float position, int length, int direction) {
        int scrollSpeed = 10;

        int sizeX = 30;
        int sizeY = 30;
        int sizeOffsetX = sizeX/2;
        int sizeOffsetY = sizeY/2;

        int x = 0;
        int y = 0;

        int drawPosition = (int)((position - songPosition) * scrollSpeed);
        if (drawPosition < -30 * length) return;
        int centerPositionX = frame.getWidth() / 2 - sizeOffsetX;
        int centerPositionY = frame.getHeight() / 2 - sizeOffsetY;

        switch (direction) {
            case 0: // Up
                x = centerPositionX;
                y = centerPositionY - drawPosition - 30;
                sizeY *= length;
                if (drawPosition < 30 * (length - 1) && length > 1) sizeY += drawPosition - 30 * (length - 1);
                break;
            case 1: // Down
                x = centerPositionX;
                y = centerPositionY + drawPosition - 30 * (length - 2);
                sizeY *= length;
                drawPosition -= 30 * (length - 1);
                if (drawPosition < 0 && length > 1) {
                    sizeY += drawPosition;
                    y -= drawPosition;
                }
                break;
            case 2: // Left
                x = centerPositionX - drawPosition - 30;
                y = centerPositionY;
                sizeX *= length;
                if (drawPosition < 30 * (length - 1) && length > 1) sizeX += drawPosition - 30 * (length - 1);
                break;
            case 3: // Right
                x = centerPositionX + drawPosition - 30 * (length - 2);
                y = centerPositionY;
                sizeX *= length;
                drawPosition -= 30 * (length - 1);
                if (drawPosition < 0 && length > 1) {
                    sizeX += drawPosition;
                    x -= drawPosition;
                }
                break;
        
            default:
                break;
        }

        g2.fillRoundRect(x, y, sizeX, sizeY, 10, 10);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Initiating Frame");
                JFrame frame = new JFrame("Note renderer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setLayout(null);
                System.out.println("Finalizing Frame");

                System.out.println("Initiating Renderer");
                NoteRenderer noteRenderer = new NoteRenderer(frame, new int[][][] {
                    /* Lane 3 */ {{14, 1}, {15, 1}, {20, 2}, {24, 2}},
                    /* Lane 1 */ {{20, 2}},
                    /* Lane 2 */ {{20, 2}},
                    /* Lane 4 */ {{20, 2}}
                });
                frame.add(noteRenderer);
                noteRenderer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
                System.out.println("Finalizing Renderer");

                frame.setVisible(true);

                // Emulates audio progress playing
                Timer timer = new Timer(20, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        songPosition += 0.16;
                        frame.repaint();
                    }
                }); 
                timer.start();
            }
        });
    }
}
