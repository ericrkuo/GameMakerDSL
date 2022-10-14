package ast;

import static constants.Constant.GAME_UNIT;

/**
 * The configuration of our game which includes the name, width of each level/substage, and the reward system.
 */
public class Game extends Node {
    private final String name;
    private final Integer width;
    private final Reward reward;

    public Game(String name, Integer width, Reward reward) {
        this.name = name;
        this.width = width * GAME_UNIT;
        this.reward = reward;
    }

    public Integer getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public Reward getReward() {
        return reward;
    }
}
