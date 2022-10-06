package libs;

import java.awt.*;
import java.awt.geom.AffineTransform;

//considering to ditch this whole class to just use java.awt Image class
public abstract class RenderableObject {
    public int x;
    public int y;
    public Image img;
    public AffineTransform transform;
    public void render(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        if (transform != null)
            g2D.drawImage(img, transform, null);
        else
            g.drawImage(img, x, y, null);
    }

    public void update(final int speed) {
        x -= speed;
    }
}
