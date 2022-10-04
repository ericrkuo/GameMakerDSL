package ast;

/**
 * Specifies the speed of a level/substage/fireball. Separated into its own class so in the future,
 * if we want to extend the behaviour of speed, we can do so in this class.
 */
public class Speed extends Node {
    private final Integer value;
    public static final Speed DEFAULT_SPEED = new Speed(1);

    public Speed(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
