package ast.fireball;

public class DefaultFireball extends Fireball {
    public DefaultFireball(int posX, int posY, int speed){
        initialX = posX;
        x = posX;
        y = posY;
        setSpeed(speed);
        img = image;
        height = 30;
        width = 60;
        counter = null; // this is here to so that abstract type fireball can be used;
        interval = -1; // this is only hear so i dont get null pointer exception.
    }

    @Override
    public void update(int speed) {
        x -= speed + fireballSpeed;
    }
}
