import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    static JFrame frame = new JFrame("Volt Charge Pulsar");

    public static void main(String[] args) {
        // Needed for user input
        JPanel panel = new JPanel();
        frame.add(panel);

        // Load main menu
        Menu menu = new Menu(frame);
        menu.initMenu();
        menu.initGUI();

        // Load user input
        KeyBindings keyBindings = new KeyBindings(menu);
        frame.addKeyListener(keyBindings.getBindings());
    }
}
