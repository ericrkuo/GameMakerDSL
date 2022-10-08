package ast;

import ui.CollisionVisitor;
import ui.Util;

import java.awt.*;

public class Portal extends Obstacle {
    public Integer destStageIndex;
    public boolean used;
    public static final Image image = Util.loadImage("assets/portal.png");
    public Portal(int posX, int posY, int destStageIndex) {
        this.destStageIndex = destStageIndex;
        used = false;
        x = posX;
        y = posY;
        img = image;
        height = 50;
        width = 50;
    }
    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }

}
