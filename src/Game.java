import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {
    private Player player;
    private KeyHandler keyH;
    private Timer timer;
    private final int DELAY = 10;
    private Image backgroundImage;

    public Game() {
        keyH = new KeyHandler();
        player = new Player(keyH);
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(keyH);
        timer = new Timer(DELAY, this);
        timer.start();

        backgroundImage = new ImageIcon(getClass().getResource("/imgs/background.jpg")).getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(getWidth(), getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        player.draw((Graphics2D) g);
        Toolkit.getDefaultToolkit().sync();
    }

    public int getTileSize() {
        return 48;
    }
}
