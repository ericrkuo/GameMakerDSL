package parser;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticCheck {
    public List<Integer> levelIds;
    public Set<Integer> subStageIdsInLevel;
    public List<Integer> substages;
    public Set<Integer> wallIdsInStage;
    public Set<Integer> fireballIdsInStage;

    public List<Integer> walls;
    public List<Integer> fireballs;

    public StaticCheck() {levelIds = new ArrayList<>();
        substages = new ArrayList<>();

        subStageIdsInLevel = new HashSet<>();
        wallIdsInStage = new HashSet<>();
        fireballIdsInStage = new HashSet<>();

        walls = new ArrayList<>();
        fireballs = new ArrayList<>();
    }

    public void hashAdd(Set<Integer> set, Integer target, String parentName, String objectName) {
        if (set.contains(target)) {
            throw new ParseCancellationException(String.format("Duplicate use of %s in the %s",
                    objectName, parentName));
        }
        set.add(target);
    }

    public void duplicateDeclare(List<Integer> preIds, String objectName) {
        Set<Integer> idSet = new HashSet<>();
        for (Integer id: preIds) {
            if (idSet.contains(id)) {
                throw new ParseCancellationException(
                        String.format("Duplicate declaration is not allowed (Object: %s, Id: %d)",
                        objectName, id));
            }
            idSet.add(id);
        }
    }

    public void unMatchCreatedWithUsed(Set<Integer> used, List<Integer> created, String objectName) {
        Set<Integer> createdSet = new HashSet<>(created);
        for (Integer id: used) {
            if (!createdSet.contains(id)) {
                throw new ParseCancellationException(String.format("Used object is not created (Object: %s, Id: %d)",
                        objectName, id));
            }
        }
    }

    public void levelIdsIncreasingByOne() {
        for (Integer i = 1; i <= levelIds.size(); i++) {
            if (!levelIds.contains(i)) {
                throw new ParseCancellationException("level id should strictly increase by 1");
            }
        }
    }
}
