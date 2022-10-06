package ast;

import ui.CollisionVisitor;

public class Portal extends Obstacle {

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
