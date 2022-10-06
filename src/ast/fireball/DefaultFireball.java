package ast.fireball;

public class DefaultFireball extends Fireball {
    public DefaultFireball(int posX, int posY, int speed){
        x = posX;
        y = posY;
        setSpeed(speed);
        img = image;
    }

    @Override
    public void update(int speed) {
        x -= speed + fireballSpeed;
    }
}
