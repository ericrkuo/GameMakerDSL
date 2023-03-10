package ui;

import static constants.Constant.GAME_HEIGHT;

public class Goal extends Obstacle {
    public boolean isSubstage;

    public Goal(final int x, final boolean isSubstage) {
        this.x = x;
        this.y = 0;
        this.width = 10;
        this.height = GAME_HEIGHT;
        this.img = Util.loadImage("assets/goal.png");
        this.isSubstage = isSubstage;
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
