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

        Button menuButtons[] = {
            createButton(frame, "Solo", frame.getWidth(), 50, 130, 50),
            createButton(frame, "Multi", frame.getWidth(), 120, 130, 50),
            createButton(frame, "Editor", frame.getWidth(), 190, 130, 50),
            createButton(frame, "Settings", frame.getWidth(), 260, 130, 50),
            createButton(frame, "Exit", frame.getWidth(), 330, 130, 50)
        };

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        animateButtons(frame, menuButtons);

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

    private Button createButton(JFrame frame, String text, int x, int y, int w,int h) {
        Button button = new Button(text);
        frame.add(button);
        button.setLocation(x, y);
        button.setSize(w, h);
        return button;
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
            frame.setUndecorated(true);
            createAndShowGUI(); // Reinitialize the frame
        }
    }

    private void animateButtons(JFrame frame, Button buttons[]) {
        Timer timer = new Timer(20, new ActionListener() {
            private float deltaX = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean end = false;
                for (Button component : buttons) {
                    Button button = (Button) component;
                    deltaX += 0.001f;
                    int newX = (int) (Math.pow(200 * (deltaX - 0.1),2)) - 52;
                    button.setLocation(-newX, button.getY());
                    if (deltaX > 0.125f) {
                        button.setLocation(30, button.getY());
                        end = true;
                    }
                    System.out.println(deltaX);
                }
            }
            
        });
        timer.start();
    }
}
