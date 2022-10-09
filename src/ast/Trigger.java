package ast;

import enums.TriggerFlavour;

public class Trigger extends Node {
    /**
     * Value depends on the flavour.
     * If trigger is loop, value represents distance interval for fireball to continually reoccur.
     * If trigger is static, fireball occurs a single time once the user passes a certain x coordinate.
     */
    private final Integer value;
    private final TriggerFlavour triggerFlavour;

    public Trigger(Integer value, TriggerFlavour triggerFlavour) {
        this.value = value;
        this.triggerFlavour = triggerFlavour;
    }

    public Integer getValue() {
        return value;
    }

    public TriggerFlavour getTriggerFlavour() {
        return triggerFlavour;
    }
}


