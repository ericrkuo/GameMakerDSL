package ast.fireball;

import ast.Obstacle;
import ui.CollisionVisitor;
import ui.Util;

import java.awt.*;

// I was wondering if i even need this class
public class Fireball extends Obstacle {
    int fireballSpeed;
    int initialX;
    public static final Image image = Util.loadImage("assets/fireball.png"); // need t
    public Fireball(int posX, int posY, int speed){
        initialX = posX;
        x = posX;
        y = posY;
        setSpeed(speed);
        img = image;
        height = 30;
        width = 60;
    }

    @Override
    public void update(int speed) {
        x -= speed + fireballSpeed;
    }

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
