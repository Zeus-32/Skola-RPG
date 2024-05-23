import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle {
    private int x, y, width, height;
    private BufferedImage image;

    public Obstacle(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
