import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH;

    Player(KeyHandler keyH) {
        this.keyH = keyH;
        Defaults();
        GetPlayerImg();
    }

    public void Defaults() {
        x = 100;
        y = 100;
        speed = 2;
        direction = "Down";
    }

    public void GetPlayerImg() {
        try {
            Up1 = ImageIO.read(getClass().getResourceAsStream("/imgs/Up1.png"));
            Up2 = ImageIO.read(getClass().getResourceAsStream("/imgs/Up2.png"));
            UpIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/UpIdle.png"));
            Down1 = ImageIO.read(getClass().getResourceAsStream("/imgs/Down1.png"));
            Down2 = ImageIO.read(getClass().getResourceAsStream("/imgs/Down2.png"));
            DownIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/DownIdle.png"));
            Left1 = ImageIO.read(getClass().getResourceAsStream("/imgs/Left1.png"));
            Left2 = ImageIO.read(getClass().getResourceAsStream("/imgs/Left2.png"));
            LeftIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/LeftIdle.png"));
            Right1 = ImageIO.read(getClass().getResourceAsStream("/imgs/Right1.png"));
            Right2 = ImageIO.read(getClass().getResourceAsStream("/imgs/Right2.png"));
            RightIdle = ImageIO.read(getClass().getResourceAsStream("/imgs/RightIdle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int panelWidth, int panelHeight) {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed && y > 0) {
                direction = "Up";
                y -= speed;
            }
            if (keyH.leftPressed && x > 0) {
                direction = "Left";
                x -= speed;
            }
            if (keyH.downPressed && y < panelHeight - 48) { // Adjust 48 to your tile size
                direction = "Down";
                y += speed;
            }
            if (keyH.rightPressed && x < panelWidth - 48) { // Adjust 48 to your tile size
                direction = "Right";
                x += speed;
            }

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
        g2.drawImage(image, x, y, 48, 48, null); // Adjust 48 to your tile size
    }
}
