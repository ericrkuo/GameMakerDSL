package ast;

import static constants.Constant.GAME_UNIT;
import static constants.Constant.REWARD_DISTANCE;

/**
 * Base reward for our game which specifies how much to reward per a specified distance the user travels
 */
public class Reward extends Node {
    private final Integer value;
    private final Integer distance;
    private final Integer factor;
    private int counter;

    public Reward(Integer value, Integer distance) {
        this.value = value;
        this.distance = distance * GAME_UNIT;
        // we want to update reward every 5 pixels travelled. So need to scale distance and value accordingly
        // e.g. if value=100 and distance=50pixels, this is equivalent to rewarding 10 every 5 units
        factor = this.distance / REWARD_DISTANCE;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getValueNormalized() {
        return value / factor;
    }

    public Integer getDistance() {
        return distance;
    }

    public int getCounter() { return counter; }

    /**
     * Always update reward every 5 PIXELS travelled
     */
    public void update(){
        if(counter == REWARD_DISTANCE){
            counter = 0;
        }
        counter++;
    }
}
