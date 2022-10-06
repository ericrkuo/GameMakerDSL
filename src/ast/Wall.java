package ast;

import ui.CollisionVisitor;

public class Wall extends Obstacle {

    private Integer height;
    private Integer length;
    private String orientation; // dont need to be parsed, can be figured out from y position;
    private static final String image = "some path"; // need to change later


    public Wall(Integer posX, Integer posY, Integer height, Integer length) {

    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return null;
    }
}
