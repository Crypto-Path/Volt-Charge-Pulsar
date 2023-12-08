import javax.swing.JFrame;

public class SceneManager {

  private JFrame frame;

  Menu menu;
  Game game;

  public SceneManager(JFrame frame) {
    this.frame = frame;
  }

  public void LoadMenu() {
    // Load main menu
    menu = new Menu(frame, this);
    menu.initMenu();
    menu.initGUI();

    // Load main menu user input
    KeyBindings keyBindings = new KeyBindings(menu);
    frame.addKeyListener(keyBindings.getBindings());
  }

  public void UnloadMenu() {
    // Unload main menu
    menu.unload();
    menu = null;
  }

  public void LoadGame() {
    // Load game
    game = new Game(frame);
    game.InitMenu();
    game.initGUI();
  }

  public void UnloadGame() {
    // Unload game
    game.unload();
    game = null;
  }
  // Managing transitions and activity of scene
}
