import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        // Create a JFrame (window)
        JFrame frame = new JFrame("Simple Window Example");
        frame.setSize(400, 300); // Set the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed

        // Create a JPanel (container for components)
        JPanel panel = new JPanel();

        // Create a JLabel (text label)
        JLabel label = new JLabel("Hello, Java Swing!");

        // Add the label to the panel
        panel.add(label);

        // Add the panel to the frame
        frame.add(panel);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Make the window visible
        frame.setVisible(true);
    }

    private void addListeners(JFrame frame) {
      frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not needed for this example
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F11:
                        toggleFullscreen(frame);
                    break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not needed for this example
            }
        });
    }

    private static void toggleFullscreen(JFrame frame) {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.getFullScreenWindow() == null) {
            // Enter fullscreen mode
            frame.dispose();
            frame.setUndecorated(true);
            device.setFullScreenWindow(frame);
            frame.setVisible(true);
        } else {
            // Exit fullscreen mode
            device.setFullScreenWindow(null);
            frame.dispose();
            frame.setUndecorated(false);
            frame.setSize(400, 300); // Set the size to your preferred non-fullscreen size
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
