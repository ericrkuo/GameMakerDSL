package ast;

import ui.Block;
import ui.CollisionVisitor;
import ui.Obstacle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static constants.Constant.GAME_UNIT;

/**
 * A wall has a list of coordinates to be created in the game.
 */
public class Wall extends Obstacle {
    private final Integer id;
    private final Integer height;
    private final Integer width;
    private final List<Coordinate> coordinates;
    private final List<Block> blocks = new ArrayList<>();

    /**
     * Note: we don't set x and y pos of wall because we use the x and y pos of block
     */
    public Wall(Integer id, Integer height, Integer width, List<Coordinate> coordinates) {
        this.id = id;
        this.height = height * GAME_UNIT;
        this.width = width * GAME_UNIT;
        this.coordinates = coordinates;
        this.initializeBlocks();
    }

    public Integer getId() {
        return id;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    private void initializeBlocks() {
        for (Coordinate c : coordinates) {
            for (int i = 0; i < width / GAME_UNIT; i++) {
                for (int j = 0; j < height / GAME_UNIT; j++) {
                    blocks.add(new Block(c.getX() + (GAME_UNIT * i), c.getY() + (GAME_UNIT * j)));
                }
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

    public Wall copy() {
        return new Wall(this.id, this.height / GAME_UNIT, this.width / GAME_UNIT, this.coordinates);
    }
}
