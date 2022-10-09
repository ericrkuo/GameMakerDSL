package ui;

import ast.Coordinate;
import ast.Substage;
import java.awt.image.BufferedImage;

public class Portal extends Obstacle {
    public Substage substageDestination;
    public boolean used;
    public static final BufferedImage image = Util.loadImage("assets/portal.png");

    public Portal(Coordinate coordinate, Substage substageDestination) {
        this.substageDestination = substageDestination;
        used = false;
        x = coordinate.getX();
        y = coordinate.getY();
        img = image;
        height = img.getHeight();
        width = img.getWidth();
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
