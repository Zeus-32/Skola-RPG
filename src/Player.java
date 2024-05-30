import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player extends Entity {
    KeyHandler keyH;
    public String characterType;

    Player(KeyHandler keyH, String characterType) {
        this.keyH = keyH;
        this.characterType = characterType;
        Defaults();
        GetPlayerImg();
    }

    public void Defaults() {
        x = 936;
        y = 512;
        speed = 3;
        direction = "Down";
    }

    public void GetPlayerImg() {
        try {
            if (characterType.equals("valecnik")) {
                Up1 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Up1.png"));
                Up2 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Up2.png"));
                UpIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/UpIdle.png"));
                Down1 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Down1.png"));
                Down2 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Down2.png"));
                DownIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/DownIdle.png"));
                Left1 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Left1.png"));
                Left2 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Left2.png"));
                LeftIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/LeftIdle.png"));
                Right1 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Right1.png"));
                Right2 = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/Right2.png"));
                RightIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/valecnik/RightIdle.png"));
            } else if (characterType.equals("kouzelnik")) {
                Up1 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Up1.png"));
                Up2 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Up2.png"));
                UpIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/UpIdle.png"));
                Down1 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Down1.png"));
                Down2 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Down2.png"));
                DownIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/DownIdle.png"));
                Left1 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Left1.png"));
                Left2 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Left2.png"));
                LeftIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/LeftIdle.png"));
                Right1 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Right1.png"));
                Right2 = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/Right2.png"));
                RightIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/kouzelnik/RightIdle.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int panelWidth, int panelHeight, ArrayList<Obstacle> obstacles) {
        boolean moved = false;
        int nextX = x;
        int nextY = y;

        int horizontalSpeed = 0;
        int verticalSpeed = 0;

        if (keyH.upPressed) {
            direction = "Up";
            verticalSpeed = -speed;
        }
        if (keyH.downPressed) {
            direction = "Down";
            verticalSpeed = speed;
        }
        if (keyH.leftPressed) {
            direction = "Left";
            horizontalSpeed = -speed;
        }
        if (keyH.rightPressed) {
            direction = "Right";
            horizontalSpeed = speed;
        }

        boolean blockedVertically = false;
        boolean blockedHorizontally = false;

        if (horizontalSpeed != 0) {
            nextX = x + horizontalSpeed;
            Rectangle nextBounds = new Rectangle(nextX, y, 48, 48);
            for (Obstacle obstacle : obstacles) {
                if (nextBounds.intersects(obstacle.getBounds())) {
                    blockedHorizontally = true;
                    break;
                }
            }
        }

        if (verticalSpeed != 0) {
            nextY = y + verticalSpeed;
            Rectangle nextBounds = new Rectangle(x, nextY, 48, 48);
            for (Obstacle obstacle : obstacles) {
                if (nextBounds.intersects(obstacle.getBounds())) {
                    blockedVertically = true;
                    break;
                }
            }
        }

        if (blockedHorizontally != false || blockedVertically != false) {
            if (horizontalSpeed != 0 && verticalSpeed != 0) {
                horizontalSpeed /= 2;
                verticalSpeed /= 2;
            }
        }

        if (!blockedHorizontally) {
            x += horizontalSpeed;
        } else if (horizontalSpeed != 0 && !keyH.leftPressed && !keyH.rightPressed) {
            x += horizontalSpeed / 2;
        }

        if (!blockedVertically) {
            y += verticalSpeed;
        } else if (verticalSpeed != 0 && !keyH.upPressed && !keyH.downPressed) {
            y += verticalSpeed / 2;
        }

        if (x < 0) x = 0;
        if (x > panelWidth - 48) x = panelWidth - 48;
        if (y < 0) y = 0;
        if (y > panelHeight - 48) y = panelHeight - 48;

        if (horizontalSpeed != 0 || verticalSpeed != 0) {
            moved = true;
        }

        if (moved) {
            SpriteCouter++;
            if (SpriteCouter > 10) {
                SpriteNum = (SpriteNum == 1) ? 2 : 1;
                SpriteCouter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            switch (direction) {
                case "Up" -> image = (SpriteNum == 1) ? Up1 : Up2;
                case "Down" -> image = (SpriteNum == 1) ? Down1 : Down2;
                case "Left" -> image = (SpriteNum == 1) ? Left1 : Left2;
                case "Right" -> image = (SpriteNum == 1) ? Right1 : Right2;
            }
        } else {
            image = switch (direction) {
                case "Up" -> UpIdle;
                case "Down" -> DownIdle;
                case "Left" -> LeftIdle;
                case "Right" -> RightIdle;
                default -> image;
            };
        }
        g2.drawImage(image, x, y, 48, 48, null);
    }
}
