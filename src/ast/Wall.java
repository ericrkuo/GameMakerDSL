package ast;

import ui.Block;
import ui.CollisionVisitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall extends Obstacle {

    public int x;
    private int y;
    private final static int GAME_UNIT = 50;
    private String orientation; // dont need to be parsed, can be figured out from y position;
    public List<Block> blocks;


    public Wall(Integer posX, Integer posY, Integer wallHeight, Integer wallWidth) {
        x = posX;
        y = posY;
        height = wallHeight * GAME_UNIT;
        width = wallWidth * GAME_UNIT;
        blocks = new ArrayList<>();
        for(int i=0; i<wallWidth; i++){
            for(int j=0; j<wallHeight; j++){
                blocks.add(new Block(x+(GAME_UNIT*i), y+(GAME_UNIT *j)));
            }
        }
    }

    @Override
    public void update(final Integer speed) {
        blocks.forEach(b -> b.update(speed));
    }


    @Override
    public void render(Graphics g) {
        blocks.forEach(b -> b.render(g));
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }
}
