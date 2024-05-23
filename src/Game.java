import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Game extends JPanel implements ActionListener {
    private Player player;
    private KeyHandler keyH;
    private Timer timer;
    private final int DELAY = 10;
    private Image backgroundImage;
    private ArrayList<Obstacle> obstacles;

    public Game() {
        keyH = new KeyHandler();
        player = new Player(keyH);
        obstacles = new ArrayList<>();
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(keyH);
        timer = new Timer(DELAY, this);
        timer.start();

        backgroundImage = new ImageIcon(getClass().getResource("/imgs/background.jpg")).getImage();

        // Přidání překážek do seznamu
        addObstacle(200, 200, 48, 48, "/imgs/obstacle.png"); // Příklad přidání překážky
    }

    private void addObstacle(int x, int y, int width, int height, String imagePath) {
        BufferedImage obstacleImage = null;
        try {
            obstacleImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        obstacles.add(new Obstacle(x, y, width, height, obstacleImage));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(getWidth(), getHeight(), obstacles);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        player.draw((Graphics2D) g);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw((Graphics2D) g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public int getTileSize() {
        return 48;
    }
}
