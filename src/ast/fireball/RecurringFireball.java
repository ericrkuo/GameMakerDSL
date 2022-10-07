package ast.fireball;

public class RecurringFireball extends Fireball {
    int interval;
    int counter;
    int initialX;

    public RecurringFireball(int posX, int posY, int speed, int recurTimer){
        initialX = posX;
        x = initialX;
        y = posY;
        counter = recurTimer;
        interval = recurTimer;
        setSpeed(speed);
        img = image;
        height = 30;
        width = 60;
    }

    @Override
    public void update(int speed) {
        if(counter<0){
            x = initialX;
            counter = interval;
        }
        counter --;
        x -= speed + fireballSpeed;
    }
}
