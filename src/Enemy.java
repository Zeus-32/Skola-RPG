import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Enemy extends Entity {
    private BufferedImage enemyImage;
    private Player player;
    private int detectRadius = 200; // Radius within which the enemy detects the player
    private Random random;
    private int moveCounter;
    private int moveDuration; // Duration for each movement direction
    private int currentDirection;
    private Game game;
    private boolean alive;

    public Enemy(int x, int y, Player player, Game game) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.game = game;
        this.speed = 2;
        this.random = new Random();
        this.moveCounter = 0;
        this.moveDuration = 100; // Initial duration for each movement direction
        this.currentDirection = random.nextInt(4); // Initialize with a random direction
        this.alive = true; // Enemy is alive when created
        loadEnemyImage();
    }

    private void loadEnemyImage() {
        try {
            enemyImage = ImageIO.read(getClass().getResourceAsStream("/imgs/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!alive) return; // Do nothing if enemy is dead

        moveCounter++;
        if (moveCounter >= moveDuration) {
            moveCounter = 0;
            moveDuration = random.nextInt(100) + 100; // Randomize duration between 100 to 200
            currentDirection = random.nextInt(4); // Change direction after duration
        }

        // Perform movement based on current direction
        switch (currentDirection) {
            case 0 -> x += speed;
            case 1 -> x -= speed;
            case 2 -> y += speed;
            case 3 -> y -= speed;
        }

        double distanceToPlayer = Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y, 2));

        if (distanceToPlayer < detectRadius) {
            moveToPlayer();
        }

        if (distanceToPlayer < 50 && alive) { // Trigger fight if close to player
            game.startFight(this);
        }
    }

    private void moveToPlayer() {
        if (player.x < x) {
            x -= speed;
        } else if (player.x > x) {
            x += speed;
        }

        if (player.y < y) {
            y -= speed;
        } else if (player.y > y) {
            y += speed;
        }
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.drawImage(enemyImage, x, y, 48, 48, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 48, 48);
    }

    public boolean isAlive() {
        return alive;
    }

    public void delete() {
        game.removeEnemy(this);
    }
}
