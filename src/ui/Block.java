package ui;

import java.awt.image.BufferedImage;

import static constants.Constant.GAME_UNIT;

public class Block extends Obstacle {
    public static final BufferedImage image = Util.loadImage("assets/wall.png"); // need to change later

    public Block(int xCor, int yCor) {
        x = xCor;
        y = yCor;
        img = image;
        height = GAME_UNIT;
        width = GAME_UNIT;
    }

    public void update(final int speed) {
        x -= speed;
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
