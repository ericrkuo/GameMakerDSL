package ast.fireball;

import libs.RenderableObject;

import java.util.ArrayList;
import java.util.List;

public class FireballSchedule extends Fireball{
    public List<Fireball> fireballSchedule;


    public FireballSchedule(){
        fireballSchedule = new ArrayList<>();
    }
    ;
    public void addFireballToSchedule(Fireball fireball){
        Fireball fireballCopy = new RecurringFireball(fireball.initialX, fireball.y, fireball.fireballSpeed, fireball.interval);
        fireballSchedule.add(fireballCopy);
    }



    public void updateScheduleFireballToRenderable(List<RenderableObject> ls) {
        List<Fireball> toAdd = new ArrayList<>();
        List<Fireball> toRemove = new ArrayList<>();
        for (Fireball fireball : fireballSchedule) {
            if(fireball.counter != null && fireball.counter==0){

                toAdd.add(new RecurringFireball(fireball.initialX, fireball.y, fireball.fireballSpeed, fireball.interval));
            }
            if(fireball.x< -fireball.width ){
                System.out.println("fireball removed");
                toRemove.add(fireball);
            }


            }
        fireballSchedule.addAll(toAdd);
        fireballSchedule.removeAll(toRemove);
        ls.removeAll(toRemove);
        ls.addAll(toAdd);


    }


}
