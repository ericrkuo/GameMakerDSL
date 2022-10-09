package ui;

import temp.Obstacle;

import java.awt.*;

public class Block extends Obstacle {
    public static final Image image = Util.loadImage("assets/wall.png"); // need to change later
    public Block(int xCor, int yCor) {
        x = xCor;
        y = yCor;
        img = image;
        height = 50;
        width = 50;
    }

    public void update(final int speed) {
        x -= speed;
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
