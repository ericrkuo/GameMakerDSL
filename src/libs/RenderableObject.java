package libs;

import ast.Node;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

//considering ditching this whole class to just use java.awt Image class
public abstract class RenderableObject extends Node implements Renderable {
    public int x;
    public int y;
    public BufferedImage img;
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
