package ast;

/**
 * A fireball can either continually reoccur at a certain distance interval, or occur a single time
 * once the user passes a certain x coordinate.
 */
public class Fireball extends Node {
    private final Integer id;
    private final Speed speed;
    private final Integer y_coordinate;
    private final Integer x_coordinate;
    private final Integer loopDistance;

    public Fireball(Integer id, Speed speed, Integer y_coordinate, Integer x_coordinate, Integer loopDistance) {
        this.id = id;
        this.speed = speed;
        this.y_coordinate = y_coordinate;
        this.x_coordinate = x_coordinate;
        this.loopDistance = loopDistance;
    }

    public Integer getId() {
        return id;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Integer getY_coordinate() {
        return y_coordinate;
    }

    public Integer getX_coordinate() {
        return x_coordinate;
    }

    public Integer getLoopDistance() {
        return loopDistance;
    }
}
