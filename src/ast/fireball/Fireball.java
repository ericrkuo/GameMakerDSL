package ast.fireball;

import ast.Obstacle;
import ui.CollisionVisitor;
import ui.Util;

import java.awt.*;

// I was wondering if i even need this class
public abstract class Fireball extends Obstacle {
    Integer counter;
    Integer interval;
    int fireballSpeed;
    int initialX;
    public static final Image image = Util.loadImage("assets/fireball.png"); // need t
    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }

    public int getFireballSpeed() {
        return fireballSpeed;
    }

    public void setSpeed(int speed) {
        this.fireballSpeed = speed;
    }


}
