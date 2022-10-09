package service;

import ast.Fireball;
import libs.RenderableObject;

import java.util.ArrayList;
import java.util.List;

public class FireballSchedulerService {
    public List<Fireball> fireballSchedule;

    public FireballSchedulerService() {
        fireballSchedule = new ArrayList<>();
    }

    public void addFireballToSchedule(Fireball fireball) {
        fireballSchedule.add(fireball.copy());
    }

    public void updateFireballSchedules(List<RenderableObject> ls) {
        List<Fireball> toAdd = new ArrayList<>();
        List<Fireball> toRemove = new ArrayList<>();

        for (Fireball fireball : fireballSchedule) {
            if (fireball.getCounter() == 0) {
                toAdd.add(fireball.copy());
            }
            if (fireball.x < -fireball.width && fireball.getCounter() <= 0) {
                toRemove.add(fireball);
            }
        }

        fireballSchedule.addAll(toAdd);
        fireballSchedule.removeAll(toRemove);
        ls.removeAll(toRemove);
        ls.addAll(toAdd);
    }
}
