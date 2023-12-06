package Components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class Notes extends JButton {

    private int x; // X-coordinate
    private int y; // Y-coordinate
    private String direction; // Direction of movement (left, right, top, bottom)

    // Method to move the Note based on its direction
    public void move() {
        switch (direction.toLowerCase()) {
            case "left":
                x--;
                break;
            case "right":
                x++;
                break;
            case "top":
                y--;
                break;
            case "bottom":
                y++;
                break;
            default:
                System.out.println("Invalid direction");
        }
    }

    // Method to rotate the Note with the hit object
    public void rotateWithHitObject(String hitObjectDirection) {
        // Assuming hitObjectDirection can be "left", "right", "top", or "bottom"
        direction = hitObjectDirection;
    }

    // Method to judge the performance based on the hit accuracy
    public String judgePerformance() {
        // Logic to determine the judgment based on hit accuracy
        // Example: You can use distance or timing to determine "Perfect," "Good," or
        // "Bad"
        // For simplicity, returning a random judgment here
        String[] judgments = { "Perfect", "Good", "Bad" };
        return judgments[(int) (Math.random() * judgments.length)];
    }

    // Getter methods for x, y, and direction
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public static void main(String[] args) {

    }

}
