package libs;

import java.awt.*;
import java.awt.geom.AffineTransform;

//considering to ditch this whole class to just use java.awt Image class
public abstract class RenderableObject implements Renderable {
    public int x;
    public int y;
    public Image img;
    public AffineTransform transform;

    @Override
    public void render(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        if (transform != null)
            g2D.drawImage(img, transform, null);
        else
            g.drawImage(img, x, y, null);
    }

    @Override
    public void update(final Integer speed) {
        x -= speed;
    }
}
