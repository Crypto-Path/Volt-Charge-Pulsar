import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBindings {

    private String scene;
    private KeyListener bindings;

    public KeyBindings(String scene) {
      this.scene = scene;
        switch(scene) {
            case "menu":
                this.bindings = menuBindings;
            break;
            default:
                this.bindings = menuBindings;
            break;
        }
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
            System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
        }

        @Override
            public void keyReleased(KeyEvent e) {
        }
    };
}
