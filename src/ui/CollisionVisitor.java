package ui;

import ast.Fireball;
import ast.Wall;

public interface CollisionVisitor<C, T> {
    T visit(C context, Portal p);
    T visit(C context, Wall w);
    T visit(C context, Fireball f);
    T visit(C context, Block b);
    T visit(C context, Goal g);
}
