package temp.fireball;

import libs.RenderableObject;

import java.util.ArrayList;
import java.util.List;

public class FireballSchedule{
    public List<RecurringFireball> fireballSchedule;


    public FireballSchedule(){
        fireballSchedule = new ArrayList<>();
    }
    ;
    public void addFireballToSchedule(RecurringFireball fireball){
        RecurringFireball fireballCopy = new RecurringFireball(fireball.initialX, fireball.y, fireball.fireballSpeed, fireball.interval);
        fireballSchedule.add(fireballCopy);
    }



    public void updateScheduleFireballToRenderable(List<RenderableObject> ls) {
        List<RecurringFireball> toAdd = new ArrayList<>();
        List<RecurringFireball> toRemove = new ArrayList<>();
        for (RecurringFireball fireball : fireballSchedule) {
            if(fireball.counter==0){
                toAdd.add(new RecurringFireball(fireball.initialX, fireball.y, fireball.fireballSpeed, fireball.interval));
            }
            if(fireball.x< -fireball.width && fireball.counter <=0){
                toRemove.add(fireball);
            }


            }
        fireballSchedule.addAll(toAdd);
        fireballSchedule.removeAll(toRemove);
        ls.removeAll(toRemove);
        ls.addAll(toAdd);


    }


}
