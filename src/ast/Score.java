package ast;

import enums.Operator;

/**
 * Specifies how the score should be handled once a user enters a substage
 */
public class Score extends Node {
    private final Operator operator;
    private final Integer value;
    public static final Score DEFAULT_SCORE = new Score(Operator.Plus, 0);

    public Score(Operator operator, Integer value) {
        this.operator = operator;
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public Integer getValue() {
        return value;
    }

    public static Operator parseOperator(String s) {
        return switch (s) {
            case "+" -> Operator.Plus;
            case "-" -> Operator.Minus;
            case "*" -> Operator.Multiply;
            case "/" -> Operator.Divide;
            default -> null;
        };
    }
}
