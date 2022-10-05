package ast;

/**
 * A fireball can either continually reoccur at a certain distance interval, or occur a single time
 * once the user passes a certain x coordinate.
 */
public class Fireball extends Node {
    private final Integer id;
    private final Speed speed;
    private final Integer y_coordinate;
    private final Trigger trigger;

    public Fireball(Integer id, Speed speed, Integer y_coordinate, Trigger trigger) {
        this.id = id;
        this.speed = speed;
        this.y_coordinate = y_coordinate;
        this.trigger = trigger;
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

    public Trigger getTrigger() {
        return trigger;
    }
}
