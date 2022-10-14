package ast;

import enums.TriggerFlavour;
import ui.CollisionVisitor;
import ui.Obstacle;
import ui.Util;

import java.awt.image.BufferedImage;

import static constants.Constant.*;

/**
 * A fireball can either continually reoccur at a certain distance interval, or occur a single time
 * once the user passes a certain x coordinate.
 */
public class Fireball extends Obstacle {
    private final Integer id;
    private final Speed speed;
    private final Integer y_coordinate;
    private final Trigger trigger;
    private Integer counter;
    private static final BufferedImage image = Util.loadImage("assets/fireball.png");

    public Fireball(Integer id, Speed speed, Integer y_coordinate, Trigger trigger) {
        this.id = id;
        this.speed = speed;
        this.y_coordinate = y_coordinate * GAME_UNIT;
        this.trigger = trigger;
        this.counter = trigger.getTriggerFlavour() == TriggerFlavour.Static ? trigger.getValue() * GAME_UNIT :
                trigger.getValue();

        // parent properties
        this.x = trigger.getTriggerFlavour() == TriggerFlavour.Static ? trigger.getValue() * GAME_UNIT : GAME_WIDTH;
        this.y = y_coordinate;
        this.img = image;
        this.height = img.getHeight();
        this.width = 10;
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

    public Integer getCounter() {
        return counter;
    }

    @Override
    public void update(final Integer speed) {
        if (trigger.getTriggerFlavour() == TriggerFlavour.Loop) {
            this.counter--;
        }
        x -= speed + this.speed.getValue();
    }

    @Override
    public <C, T> T accept(C context, CollisionVisitor<C, T> v) {
        return v.visit(context, this);
    }

    public Fireball copy() {
        return new Fireball(this.id, this.speed, this.y_coordinate, this.trigger);
    }
}
