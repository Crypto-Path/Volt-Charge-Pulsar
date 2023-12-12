import javax.swing.*;

import Components.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPanel extends JButton {
    
private JFrame settings; 
private SceneManager manager; 
private Button settingButton[];

  public SettingPanel(JFrame settings, SceneManager manager) {
    this.settings = settings;
    this.manager = manager; 
    
  }

  public void SettingGUI() {
    Button tempButtons[] = {
        createButton(settings, "Settings", settings.getWidth(), 2, 150, 30),
        createButton(settings, "Exit", settings.getWidth(), 1, 150, 30)
    };

    this.settingButton = tempButtons;

    for (Button button : settingButton) {
        button.setLocation(button.location().x, settings.getHeight() - (button.location().y * 50 + 50));
    }

    // ExitButton
    settingButton[0].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Solo");
            manager.LoadGame();
            manager.UnloadMenu();
        }
    });

    // Setting Button
    settingButton[4].setColorOver(new Color(250, 160, 160));
    settingButton[4].setColorClick(new Color(190, 140,140));
    settingButton[4].setBorderColor(new Color(140, 40,40));
    settingButton[4].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Exit");
            System.exit(0);
        }
    }); }

    private Button createButton(JFrame frame, String text, int x, int y, int w, int h) {
        Button button = new Button(text);
        frame.add(button);
        button.setLocation(x, y);
        button.setSize(w, h);
        button.setRadius(h);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Setting");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Button button = new Button("Exit");
                frame.add(button);
                button.setPreferredSize(new Dimension(50, 50));
                frame.setLayout(null);
                button.setBounds(100, 50, 150, 50);

                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
            }
        });
    }


} 
