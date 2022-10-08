package ast.fireball;

import java.util.List;

public class RecurringFireball extends Fireball {

    public RecurringFireball(int posX, int posY, int speed, int recurTimer){
        initialX = posX;
        x = posX;
        y = posY;
        interval = recurTimer;
        counter = recurTimer;
        setSpeed(speed);
        img = image;
        height = 30;
        width = 60;
    }


    @Override
    public void update(int speed) {
        counter --;
        x -= speed + fireballSpeed;

    }
}
