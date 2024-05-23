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

    //přidání stromů jako blok//
        addObstacle(200, 200, 48, 48, "/imgs/obstacle.png");
        int j;
        addObstacle(-10, -32, 100, 100, "/imgs/strom1.png");

        j = 0;
        for (int i = 0; i < 26; i++){
            addObstacle(j, -30, 100, 100, "/imgs/strom1.png");
            j += 75;
        }
        j = 0;
        for (int i = 0; i < 21; i++){
            addObstacle(-10, j, 100, 100, "/imgs/strom1.png");
            j += 50;
        }
        j = 0;
        for (int i = 0; i < 21; i++){
            addObstacle(1830, j, 100, 100, "/imgs/strom1.png");
            j += 50;
        }
        j = 0;
        for (int i = 0; i < 26; i++){
            addObstacle(j, 1000, 100, 100, "/imgs/strom1.png");
            j += 75;
        }
    //konec stromů//
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
