package ast.fireball;

import ast.Obstacle;
import ui.CollisionVisitor;

public abstract class Fireball extends Obstacle {
    int initY;
    int speed;
    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return null;
    }
}
