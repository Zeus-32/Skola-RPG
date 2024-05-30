import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Enemy extends Entity {
    private BufferedImage enemyImage;
    private Player player;
    private int detectRadius = 200;
    private Random random;
    private int moveCounter;
    private int moveDuration;
    private int currentDirection;
    private Game game;
    private boolean alive;
    private int width = 48;
    private int height = 48;

    public Enemy(int x, int y, Player player, Game game) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.game = game;
        this.speed = 2;
        this.random = new Random();
        this.moveCounter = 0;
        this.moveDuration = 100;
        this.currentDirection = random.nextInt(4);
        this.alive = true;
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
        if (!alive) return;

        moveCounter++;
        if (moveCounter >= moveDuration) {
            moveCounter = 0;
            moveDuration = random.nextInt(100) + 100;
            currentDirection = random.nextInt(4);
        }

        int newX = x, newY = y;
        switch (currentDirection) {
            case 0:
                newX += speed;
                break;
            case 1:
                newX -= speed;
                break;
            case 2:
                newY += speed;
                break;
            case 3:
                newY -= speed;
                break;
        }

        Rectangle newBounds = new Rectangle(newX, newY, width, height);
        for (Obstacle obstacle : game.getObstacles()) {
            if (newBounds.intersects(obstacle.getBounds())) {
                return;
            }
        }
        x = newX;
        y = newY;

        double distanceToPlayer = Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y, 2));

        if (distanceToPlayer < detectRadius) {
            moveToPlayer();
        }

        if (distanceToPlayer < 50 && alive) {
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
