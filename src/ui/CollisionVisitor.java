package ui;

import ast.fireball.Fireball;
import ast.Portal;
import ast.Wall;

public interface CollisionVisitor<C,T> {
    T visit(C context, Portal p);
    T visit(C context, Wall w);
    T visit(C context, Fireball f);
    T visit(C context, Block b);
}
