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
    private boolean isPaused;
    private JPanel pausePanel;
    private JButton resumeButton;
    private JButton exitButton;

    public Game(String characterType) {
        keyH = new KeyHandler(this);
        player = new Player(keyH, characterType);
        obstacles = new ArrayList<>();
        setLayout(null);
        setFocusable(true);
        addKeyListener(keyH);
        timer = new Timer(DELAY, this);
        timer.start();

        backgroundImage = new ImageIcon(getClass().getResource("/imgs/background.jpg")).getImage();

        //přidání stromů jako blok//
        int j;
        addObstacle(-10, -32, 100, 100, "/imgs/strom1.png");

        j = 0;
        for (int i = 0; i < 26; i++) {
            addObstacle(j, -30, 100, 100, "/imgs/strom1.png");
            j += 75;
        }
        j = 0;
        for (int i = 0; i < 21; i++) {
            addObstacle(-10, j, 100, 100, "/imgs/strom1.png");
            j += 50;
        }
        j = 0;
        for (int i = 0; i < 21; i++) {
            addObstacle(1830, j, 100, 100, "/imgs/strom1.png");
            j += 50;
        }
        j = 0;
        for (int i = 0; i < 26; i++) {
            addObstacle(j, 1000, 100, 100, "/imgs/strom1.png");
            j += 75;
        }
        //konec stromů//

        //Pause
        pausePanel = new JPanel();
        pausePanel.setLayout(new GridBagLayout());
        pausePanel.setBounds(0, 0, 1920, 1080);
        pausePanel.setBackground(new Color(0, 0, 0, 150));
        pausePanel.setVisible(false);
        add(pausePanel);

        resumeButton = new JButton("Zpět do hry");
        resumeButton.addActionListener(e -> resumeGame());
        exitButton = new JButton("Odejít na plochu");
        exitButton.addActionListener(e -> exitGame());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pausePanel.add(resumeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pausePanel.add(exitButton, gbc);
        //konec
    }

    public void pauseGame() {
        isPaused = true;
        timer.stop();
        pausePanel.setVisible(true);
    }

    public void resumeGame() {
        isPaused = false;
        timer.start();
        pausePanel.setVisible(false);
    }

    public boolean isPaused() {
        return isPaused;
    }

    private void addObstacle(int x, int y, int width, int height, String imagePath) {
        BufferedImage obstacleImage = null;
        try {
            obstacleImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        obstacles.add(new Obstacle(x, y, width, height, obstacleImage)); // Updated line
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused) {
            player.update(getWidth(), getHeight(), obstacles);
            repaint();
        }
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

    public void exitGame() {
        System.exit(0);
    }

    public int getTileSize() {
        return 48;
    }
}
