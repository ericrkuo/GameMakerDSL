package ast.fireball;

import java.util.List;

public class RecurringFireball extends Fireball {
    int interval;
    int counter;
    public RecurringFireball(int posX, int posY, int speed, int recurTimer){
        super(posX, posY, speed);
        interval = recurTimer;
        counter = recurTimer;
    }


    @Override
    public void update(int speed) {
        counter --;
        x -= speed + fireballSpeed;

    }
}
