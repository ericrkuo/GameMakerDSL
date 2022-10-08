package ast.fireball;

public class DefaultFireball extends Fireball {
    public DefaultFireball(int posX, int posY, int speed){
        x = posX;
        y = posY;
        setSpeed(speed);
        img = image;
        height = 30;
        width = 60;
    }

    @Override
    public void update(final Integer speed) {
        x -= speed + fireballSpeed;
    }
}
