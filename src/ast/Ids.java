package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * This class should NOT be used. It is only used to parse the fireball and wall ids inside level/substage declarations
 */
public class Ids extends Node {
    private final List<Integer> ids = new ArrayList<>();

    public Ids() {
    }

    public List<Integer> getIds() {
        return ids;
    }
}
