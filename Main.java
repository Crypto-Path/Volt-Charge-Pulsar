import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.*;

public class Main {
    static JFrame frame = new JFrame("Volt Charge Pulsar");

    public static void main(String[] args) {
        // Needed for user input
        JPanel panel = new JPanel();
        frame.add(panel);

        SceneManager sceneManager = new SceneManager(frame);
        sceneManager.LoadMenu();

        frame.repaint();
    }
}
