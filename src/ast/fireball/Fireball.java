package ast.fireball;

import ast.Obstacle;
import ui.CollisionVisitor;
import ui.Util;

import java.awt.*;

public abstract class Fireball extends Obstacle {
    int fireballSpeed;
    public static final Image image = Util.loadImage("assets/fireball.png"); // need t
    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return null;
    }

    public int getFireballSpeed() {
        return fireballSpeed;
    }

    public void setSpeed(int speed) {
        this.fireballSpeed = speed;
    }


}
