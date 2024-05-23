import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {
    private Player player;
    private KeyHandler keyH; // přidáme referenci na KeyHandler
    private Timer timer;
    private final int DELAY = 10;
    private Image backgroundImage; // Přidáme proměnnou pro obrázek pozadí

    public Game() {
        keyH = new KeyHandler(); // inicializujeme KeyHandler
        player = new Player(keyH);
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(keyH); // přidáme KeyListener do JPanelu
        timer = new Timer(DELAY, this);
        timer.start();

        // Načtení obrázku pozadí
        backgroundImage = new ImageIcon(getClass().getResource("/imgs/background.jpg")).getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vykreslení obrázku pozadí
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        player.draw((Graphics2D) g); // Nakreslí hráče nad obrázkem pozadí
        Toolkit.getDefaultToolkit().sync();
    }

    public int getTileSize() {
        // Define the tile size based on your game's requirements
        return 48; // Example tile size
    }
}
