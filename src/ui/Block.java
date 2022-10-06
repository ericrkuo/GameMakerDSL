package ui;

import ast.Obstacle;
import libs.RenderableObject;

import java.awt.*;

public class Block extends Obstacle {
    public static final Image image = Util.loadImage("assets/wall.png"); // need to change later
    public static final int height = 50;
    public static final int width = 50;
    public Block(int xCor, int yCor) {
        x = xCor;
        y = yCor;
        img = image;
    }

    public void update(final int speed) {
        x -= speed;
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
