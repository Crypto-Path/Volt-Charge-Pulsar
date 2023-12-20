import javax.swing.*;

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
