package parser;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticCheck {
    public Set<Integer> levelIds;
    public Set<Integer> subStageIdsInLevel;
    public Set<Integer> substages;
    public Set<Integer> wallIdsInStage;
    public Set<Integer> fireballIdsInStage;

    public Set<Integer> walls;
    public Set<Integer> fireballs;

    public StaticCheck() {
        levelIds = new HashSet<>();
        substages = new HashSet<>();
        wallIdsInStage = new HashSet<>();
        fireballIdsInStage = new HashSet<>();

        walls = new HashSet<>();
        fireballs = new HashSet<>();
    }

    public void duplicateDeclare(Set<Integer> preIds, Integer id, String objectName) {
        if (preIds.contains(id)) {
            throw new ParseCancellationException(String.format("Duplicate declaration is not allowed (Object: %s, Id: %d",
                    objectName, id));
        }
    }

    public void unMatchCreatedWithUsed(Set<Integer> used, Set<Integer> created, String objectName) {
        if (!used.equals(created)) {
            throw new ParseCancellationException(String.format("Unmatched between created and used object (Object %s)",
             objectName));
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
