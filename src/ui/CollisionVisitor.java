package ui;

import temp.fireball.Fireball;
import temp.Portal;
import temp.Wall;

public interface CollisionVisitor<C,T> {
    T visit(C context, Portal p);
    T visit(C context, Wall w);
    T visit(C context, Fireball f);
    T visit(C context, Block b);
    T visit(C context, Goal g);
}
