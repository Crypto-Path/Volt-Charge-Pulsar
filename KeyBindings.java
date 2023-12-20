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
                case KeyEvent.VK_O:
                    menu.toggleSettings();
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

    private KeyListener gameBindings = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    menu.toggleFullscreen();
                    break;
                case KeyEvent.VK_A: 
                    menu.toggleFullscreen(); 
                    break; 
                case KeyEvent.VK_S: 
                    menu.toggleFullscreen();
                     break; 
                case KeyEvent.VK_D:
                    menu.toggleFullscreen(); 
                     break; 
                case KeyEvent.VK_UP:
                    menu.toggleFullscreen(); 
                    break; 
                case KeyEvent.VK_DOWN:
                    menu.toggleFullscreen(); 
                    break; 
                case KeyEvent.VK_LEFT:
                    menu.toggleFullscreen(); 
                    break; 
               case KeyEvent.VK_RIGHT:
                    menu.toggleFullscreen(); 
                    break;
               case KeyEvent.VK_SPACE: 
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
