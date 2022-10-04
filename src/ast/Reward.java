package ast;

/**
 * Base reward for our game which specifies how much to reward per a specified distance the user travels
 */
public class Reward extends Node {
    private final Integer value;
    private final Integer distance;

    public Reward(Integer value, Integer distance) {
        this.value = value;
        this.distance = distance;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getDistance() {
        return distance;
    }
}
