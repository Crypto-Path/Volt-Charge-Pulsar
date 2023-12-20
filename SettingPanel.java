import javax.swing.*;

import Components.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPanel extends JButton {
    
    private JFrame frame;
    private Button settingButton[];
    private int margin = 100;

    public SettingPanel(JFrame frame) {
        this.frame = frame;
        setContentAreaFilled(false);
        setBorderPainted(false);
    
        Button tempButtons[] = {
            createButton(frame, "Settings", frame.getWidth(), 0, 150, 30),
            createButton(frame, "Exit", frame.getWidth(), 1, 150, 30)
        };

        this.settingButton = tempButtons;

        for (Button button : settingButton) {
            button.setLocation(10, (button.location().y * 50 + 10));
        }

        settingButton[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("A");
            }
        });
    }

    private Button createButton(JFrame frame, String text, int x, int y, int w, int h) {
        Button button = new Button(text);
        frame.add(button);
        button.setLocation(x, y);
        button.setSize(w, h);
        button.setRadius(h);
        return button;
    }    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(new Color(127, 127, 127));
        g2.fillRoundRect(margin, margin, frame.getWidth() - margin * 2 - 20, frame.getHeight() - margin * 2 - 40, 20, 20);
        g2.setColor(getBackground());

        setBounds(0, 0, frame.getWidth(), frame.getHeight());

        super.paintComponent(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Setting");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setLayout(null);

                SettingPanel settingPanel = new SettingPanel(frame);
                frame.add(settingPanel);
                settingPanel.setPreferredSize(new Dimension(50, 50));
                settingPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

                frame.setVisible(true);
                
            }
        });
    }


} 
