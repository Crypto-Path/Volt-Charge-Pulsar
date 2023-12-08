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
    private boolean active1 = false;
    private boolean active2 = false;
    private boolean active3 = false;
    private boolean active4 = false;

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

        g2.setColor(Color.BLUE);
        for (int laneIndex = 0; laneIndex < notes.length; laneIndex++) {
            int[][] lane = notes[laneIndex];
            for (int noteIndex = 0; noteIndex < lane.length; noteIndex++) {
                int[] note = lane[noteIndex];
                int position = note[0];
                int longness = note[1];
                DrawNote(g2, position, longness, laneIndex);
            }
        }

        Color inactive = new Color(255, 0, 0, 127);
        Color active = new Color(255, 0, 0, 127);

        int size = Math.min(frame.getHeight(), frame.getWidth()) / 15;
        g2.setColor((active1) ? active : inactive);
        g2.fillRoundRect(frame.getWidth() / 2 - size / 2, frame.getHeight() / 2 - size / 2 - size - 20, size, size, size / 3, size / 3); // Up
        g2.setColor((active2) ? active : inactive);
        g2.fillRoundRect(frame.getWidth() / 2 - size / 2 - size, frame.getHeight() / 2 - size / 2 - 20, size, size, size / 3, size / 3); // Left
        g2.setColor((active3) ? active : inactive);
        g2.fillRoundRect(frame.getWidth() / 2 - size / 2, frame.getHeight() / 2 - size / 2 + size - 20, size, size, size / 3, size / 3); // Down
        g2.setColor((active4) ? active : inactive);
        g2.fillRoundRect(frame.getWidth() / 2 - size / 2 + size, frame.getHeight() / 2 - size / 2 - 20, size, size, size / 3, size / 3); // Right

        super.paintComponent(g);
    }

    private void DrawNote(Graphics2D g2, float position, int length, int direction) {
        int size = Math.min(frame.getHeight(), frame.getWidth()) / 15;
        int scrollSpeed = size;

        int sizeX = size;
        int sizeY = size;
        int sizeOffsetX = sizeX/2;
        int sizeOffsetY = sizeY/2 + 20;

        int x = 0;
        int y = 0;

        int drawPosition = (int)((position - songPosition) * scrollSpeed);
        if (drawPosition < -size * length) return;
        int centerPositionX = frame.getWidth() / 2 - sizeOffsetX;
        int centerPositionY = frame.getHeight() / 2 - sizeOffsetY;

        switch (direction) {
            case 0: // Up
                x = centerPositionX;
                y = centerPositionY - drawPosition - size;
                sizeY *= length;
                y -= size * length;
                sizeY += Math.min(0, ((position + 1) - songPosition) * size);
                break;
            case 1: // Down
                x = centerPositionX + size;
                y = centerPositionY + drawPosition + size * (length + 2);
                sizeY *= length;
                sizeY += Math.min(0, ((position + 1) - songPosition) * size);
                break;
            case 2: // Left
                x = centerPositionX - drawPosition - size;
                y = centerPositionY;
                sizeX *= length;
                x -= size * length;
                sizeX += Math.min(0, ((position + 1) - songPosition) * size);
                break;
            case 3: // Right
                x = centerPositionX + drawPosition + size * (length + 2);
                y = centerPositionY + size;
                sizeX *= length;
                sizeX += Math.min(0, ((position + 1) - songPosition) * size);
                break;
        
            default:
                break;
        }
        if (direction == 1 || direction == 3) {
            fillRoundRectBR(g2, x, y, sizeX, sizeY, size / 3, size / 3);
            return;
        }
        g2.fillRoundRect(x, y, sizeX, sizeY, size / 3, size / 3);
    }

    private void fillRoundRectBR(Graphics g, int x, int y, int width, int height, int arcWidth, int arcHeight) {
        // Adjust the coordinates to draw from the bottom right
        int adjustedX = x - width;
        int adjustedY = y - height;

        g.fillRoundRect(adjustedX, adjustedY, width, height, arcWidth, arcHeight);
    }

    public static void setSongPosition(float songPosition) {
      NoteRenderer.songPosition = songPosition;
    }

    public void setActive(int direction, boolean active) {
        switch (direction) {
            case 0:
                active1 = active;
                break;
            case 1:
                active2 = active;
                break;
            case 2:
                active3 = active;
                break;
            case 3:
                active4 = active;
                break;
        
            default:
                break;
        }
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
                    /* Lane 1 Up */ {{5, 2}},
                    /* Lane 2 Down */ {{5, 2}},
                    /* Lane 3 Left */ {{5, 3}},
                    /* Lane 4 Right */ {{5, 3}}
                });
                frame.add(noteRenderer);
                noteRenderer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
                System.out.println("Finalizing Renderer");

                frame.setVisible(true);

                // Emulates audio progress playing
                Timer timer = new Timer(20, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        noteRenderer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
                        songPosition += 0.05;
                        frame.repaint();
                    }
                }); 
                timer.start();
            }
        });
    }
}
