package ast;

import java.util.Objects;

import static constants.Constant.GAME_UNIT;

/**
 * Represents an (x,y) coordinate pair. Used to specify locations of walls and substages.
 */
public class Coordinate extends Node {
    private final Integer x;
    private final Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x * GAME_UNIT;
        this.y = y * GAME_UNIT;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(getX(), that.getX()) && Objects.equals(getY(), that.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}
