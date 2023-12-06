import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBindings {

    private String scene;
    private KeyListener bindings;

    // Scenes
    private Menu menu;

    public KeyBindings(Menu menu) {
        this.scene = "Menu";
        this.bindings = menuBindings;

        this.menu = menu;
    }

    public KeyListener getBindings() {
        return this.bindings;
    }

    private KeyListener menuBindings = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F11:
                    menu.toggleFullscreen();
                    break;
            
                default:
                    break;
            }
            System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
}
