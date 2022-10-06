package ast;

import ui.Block;
import ui.CollisionVisitor;
import ui.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall extends Obstacle {

    private int height;
    public int width;
    public int x;
    private int y;
    private String orientation; // dont need to be parsed, can be figured out from y position;
    public List<Block> blocks;


    public Wall(Integer posX, Integer posY, Integer wallHeight, Integer wallWidth) {
        x = posX;
        y = posY;
        height = wallHeight;
        width = wallWidth;
        blocks = new ArrayList<>();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                blocks.add(new Block(x+(50*i), y+(50*j)));
            }
        }
    }

    public void update(int speed) {
        blocks.forEach(b -> b.update(speed));
    }


    @Override
    public void render(Graphics g) {
        blocks.forEach(b -> b.render(g));
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return null;
    }
}
