package ui;

import ast.Coordinate;
import ast.Substage;
import java.awt.*;
import static constants.Constant.PORTAL_SIZE;

public class Portal extends Obstacle {
    public Substage substageDestination;
    public boolean used;
    public static final Image image = Util.loadImage("assets/portal.png");

    public Portal(Coordinate coordinate, Substage substageDestination) {
        this.substageDestination = substageDestination;
        used = false;
        x = coordinate.getX();
        y = coordinate.getY();
        img = image;
        height = PORTAL_SIZE;
        width = PORTAL_SIZE;
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
