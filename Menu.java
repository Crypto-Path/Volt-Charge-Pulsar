import javax.swing.*;

import Components.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
  private JFrame frame;
  private SceneManager manager;

  private Button menuButtons[];

  public Menu(JFrame frame, SceneManager manager) {
    this.frame = frame;
    this.manager = manager;
  }

  public void initMenu() {
        // Create a JFrame (window)
        this.frame.setSize(800, 600); // Set the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed

        frame.getContentPane().setBackground(Color.BLACK);

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //frame.addKeyListener(keyBindings);
    }

    public void initGUI() {
        Button tempButtons[] = {
            createButton(frame, "Solo", frame.getWidth(), 5, 150, 30),
            createButton(frame, "Multi", frame.getWidth(), 4, 150, 30),
            createButton(frame, "Editor", frame.getWidth(), 3, 150, 30),
            createButton(frame, "Settings", frame.getWidth(), 2, 150, 30),
            createButton(frame, "Exit", frame.getWidth(), 1, 150, 30)
        };

        this.menuButtons = tempButtons;

        for (Button button : menuButtons) {
            button.setLocation(button.location().x, frame.getHeight() - (button.location().y * 50 + 50));
        }

        // Solo Button
        menuButtons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Solo");
                manager.LoadGame();
                manager.UnloadMenu();
            }
        });

        // Exit Button
        menuButtons[4].setColorOver(new Color(250, 160, 160));
        menuButtons[4].setColorClick(new Color(190, 140,140));
        menuButtons[4].setBorderColor(new Color(140, 40,40));
        menuButtons[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Exit");
                System.exit(0);
            }
        });

        animateButtons(frame, menuButtons);
    }

    private Button createButton(JFrame frame, String text, int x, int y, int w, int h) {
        Button button = new Button(text);
        frame.add(button);
        button.setLocation(x, y);
        button.setSize(w, h);
        button.setRadius(h);
        return button;
    }

    public void unload() {
        timer.stop();
        timer = null;
        for (Button button : menuButtons) {
            frame.remove(button);
            button = null;
        }
        frame.getContentPane().setBackground(Color.WHITE);
    }

    public void toggleFullscreen() {
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
            initMenu(); // Reinitialize the frame 
        }

            
    }

    Timer timer;
    private void animateButtons(JFrame frame, Button buttons[]) {
        timer = new Timer(20, new ActionListener() {
            private float deltaX = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean end = false;
                deltaX += 0.01f;
                for (Button component : buttons) {
                    Button button = (Button) component;
                    int newX = (int) (Math.pow(200 * (deltaX - 0.1),2)) - 40;
                    button.setLocation(-newX, button.getY());
                    if (deltaX > 0.115f) {
                        button.setLocation(30, button.getY());
                        end = true;
                    }
                }
                if (end) {
                    timer.stop();
                }
            }
            
        });
        timer.start();
    }
}
