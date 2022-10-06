package ui;

import libs.RenderableObject;

import java.awt.*;

public class Block extends RenderableObject {
    public static final Image image = Util.loadImage("assets/wall.png"); // need to change later
    public Block(int xCor, int yCor) {
        x = xCor;
        y = yCor;
        img = image;
    }

    public void update(final int speed) {
        x -= speed;
    }
}
