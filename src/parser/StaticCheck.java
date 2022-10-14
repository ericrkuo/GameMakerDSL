package parser;

import ast.Program;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticCheck {
    List<Integer> levelIds = new ArrayList<>();
    List<Integer> subStages = new ArrayList<>();
    List<Integer> walls = new ArrayList<>();
    List<Integer> fireballs = new ArrayList<>();

    Set<Integer> subStageIdsInLevel = new HashSet<>();
    Set<Integer> wallIdsInStage = new HashSet<>();
    Set<Integer> fireballIdsInStage = new HashSet<>();

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
                throw new ParseCancellationException(String.format("Referenced object has not been created (Object: %s, Id: %d)",
                        objectName, id));
            }
        }
    }

    public void levelIdsIncreasingByOne(Set<Integer> levelIds) {
        for (int i = 1; i <= levelIds.size(); i++) {
            if (!levelIds.contains(i)) {
                throw new ParseCancellationException("Level id should strictly increase by 1");
            }
        }
    }

    public void check(Program program) {
        // check duplicate declaration
        this.duplicateDeclare(levelIds, "Level");
        this.duplicateDeclare(subStages, "SubStage");
        this.duplicateDeclare(walls, "Wall");
        this.duplicateDeclare(fireballs, "Fireball");

        // check if level id’s to be strictly increasing by 1
        this.levelIdsIncreasingByOne(program.getLevels().keySet());

        // check if check that sub stage ids declared inside “create level” each have a “create”
        // statement.
        this.unMatchCreatedWithUsed(this.subStageIdsInLevel, subStages, "SubStage");
        this.unMatchCreatedWithUsed(this.wallIdsInStage, walls, "Wall");
        this.unMatchCreatedWithUsed(this.fireballIdsInStage, fireballs, "Fireball");
    }
}
