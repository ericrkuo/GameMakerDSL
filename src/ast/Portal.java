package ast;

import ui.CollisionVisitor;
import ui.Util;

import java.awt.*;

public class Portal extends Obstacle {
    public static final Image image = Util.loadImage("assets/portal.png");
    public Portal(int posX, int posY){
        x = posX;
        y = posY;
        img = image;

    }
    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return null;
    }

}
