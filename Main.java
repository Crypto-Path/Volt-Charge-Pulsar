import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {
        Main app = new Main();
        app.createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create a JFrame (window)
        JFrame frame = new JFrame("Volt Charge Pulsar");
        frame.setSize(800, 600); // Set the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed

        frame.getContentPane().setBackground(Color.BLACK);

        createButton(frame, "Solo", frame.getWidth(), 50, 130, 50);
        createButton(frame, "Multi", frame.getWidth(), 120, 130, 50);
        createButton(frame, "Editor", frame.getWidth(), 190, 130, 50);
        createButton(frame, "Settings", frame.getWidth(), 260, 130, 50);
        createButton(frame, "Exit", frame.getWidth(), 330, 130, 50);

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        animateButtons(frame);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not needed for this example
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key pressed: "+ e);
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

    private void createButton(JFrame frame, String text, int x, int y, int w,int h) {

        Button button = new Button(text);
        frame.add(button);
        button.setLocation(x, y);
        button.setSize(w, h);
    }

    private void toggleFullscreen(JFrame frame) {
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
            createAndShowGUI(); // Reinitialize the frame
        }
    }

    private void animateButtons(JFrame frame) {
        Timer timer = new Timer(20, new ActionListener() {
            private int deltaX = -200; // adjust this value to control the speed of the animation

            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : frame.getContentPane().getComponents()) {
                    if (component instanceof Button) {
                        Button button = (Button) component;
                        int newX = (int) (button.getX() / 1.1185f);
                        deltaX += 1;
                        if (deltaX > 0) {
                            button.setLocation(52, button.getY());
                            return;
                        }
                        if (newX > 50) { // adjust this value to control the endpoint of the animation
                            button.setLocation(newX, button.getY());
                        }
                    }
                }
            }
        });
        timer.start();
    }
}
