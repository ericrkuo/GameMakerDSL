package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * A wall has a list of coordinates to be created in the game.
 */
public class Wall extends Node {
    private final Integer id;
    private final Integer height;
    private final Integer width;
    private final List<Coordinate> coordinates = new ArrayList<>();

    public Wall(Integer id, Integer height, Integer width) {
        this.id = id;
        this.height = height;
        this.width = width;
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
}
