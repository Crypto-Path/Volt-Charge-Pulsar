package Components;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Button extends JButton {

    /**
     * @return boolean return the over
     */
    public boolean isOver() {
        return over;
    }

    /**
     * @param over the over to set
     */
    public void setOver(boolean over) {
        this.over = over;
    }

    /**
     * @return Color return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    /**
     * @return Color return the colorOver
     */
    public Color getColorOver() {
        return colorOver;
    }

    /**
     * @param colorOver the colorOver to set
     */
    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    /**
     * @return Color return the colorClick
     */
    public Color getColorClick() {
        return colorClick;
    }

    /**
     * @param colorClick the colorClick to set
     */
    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    /**
     * @return Color return the borderColor
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * @param borderColor the borderColor to set
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return int return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Button( String text ) {
        this.text = text;
        // Init Color
        setColor(new Color(255, 255, 255));
        setColorOver(new Color(160, 250, 160));
        setColorClick(new Color(140, 190,140));
        setBorderColor(new Color(40, 140,40));
        setContentAreaFilled(false);
        setBorderPainted(false);
        // Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                over = true;
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                setCursor(Cursor.getDefaultCursor());
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);

            } 

            @Override
            public void mouseReleased(MouseEvent e) {
                if(over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            } 
        });
    }

    private String text;
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 50;

    @Override
    protected void paintComponent(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(this.text);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

        Color textColor = new Color(20, 20, 20);
        g2.setColor(textColor);
        int x = (getWidth() - textWidth) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.drawString(this.text, x, y);

        super.paintComponent(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Cool Button");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Button button = new Button("Sample Text");
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