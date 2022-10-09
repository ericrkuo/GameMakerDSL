package parser;

import ast.*;
import enums.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * Converts a parse tree to an AST.
 * EndlessRunnerMakerParserBaseVisitor gives default implementations (but we override the ones we want)
 */
public class ParseTreeToAST extends GameParserBaseVisitor<Node> {
    public StaticCheck staticCheck;

    public ParseTreeToAST() {
        this.staticCheck = new StaticCheck();
    }

    @Override
    public Program visitProgram(GameParser.ProgramContext ctx) {
        Game game = (Game) ctx.game().accept(this);
        Program program = new Program(game);

        for (GameParser.LevelContext l : ctx.level()) {
            Level level = (Level) l.accept(this);

            // check duplicate declaration
            staticCheck.duplicateDeclare(staticCheck.levelIds, level.getId(), "Level");

            program.getLevels().put(level.getId(), level);
            // <static check add>
            staticCheck.levelIds.add(level.getId());
        }
        // check if level id’s to be strictly increasing by 1
        staticCheck.levelIdsIncreasingByOne();

        for (GameParser.SubstageContext s : CollectionUtils.emptyIfNull(ctx.substage())) {
            Substage substage = (Substage) s.accept(this);

            // check duplicate declaration
            staticCheck.duplicateDeclare(staticCheck.substages, substage.getId(), "SubStage");

            program.getSubStages().put(substage.getId(), substage);
            // <static check add>
            staticCheck.substages.add(substage.getId());
        }
        // check if check that sub stage ids declared inside “create level” each have a “create”
        // statement.
        staticCheck.unMatchCreatedWithUsed(staticCheck.subStageIdsInLevel, staticCheck.substages, "SubStage");

        for (GameParser.WallContext w : CollectionUtils.emptyIfNull(ctx.wall())) {
            Wall wall = (Wall) w.accept(this);

            // check duplicate declaration
            staticCheck.duplicateDeclare(staticCheck.walls, wall.getId(), "Wall");

            program.getWalls().put(wall.getId(), wall);
            // <static check add>
            staticCheck.walls.add(wall.getId());
        }
        // check if check that wall ids declared inside “create level” & “create substage” each have a “create”
        // statement.
        staticCheck.unMatchCreatedWithUsed(staticCheck.wallIdsInStage, staticCheck.walls, "Wall");

        for (GameParser.FireballContext f : CollectionUtils.emptyIfNull(ctx.fireball())) {
            Fireball fireball = (Fireball) f.accept(this);

            // check duplicate declaration
            staticCheck.duplicateDeclare(staticCheck.fireballs, fireball.getId(), "Fireball");

            program.getFireballs().put(fireball.getId(), fireball);
            // <static check add>
            staticCheck.fireballs.add(fireball.getId());
        }
        // check if check that wall ids declared inside “create level” & “create substage” each have a “create”
        // statement.
        staticCheck.unMatchCreatedWithUsed(staticCheck.fireballIdsInStage, staticCheck.fireballs, "Fireball");

        // now that we have parsed all obstacles, render the objects in each stage and level
        program.renderAllObjects();
        return program;
    }

    @Override
    public Game visitGame(GameParser.GameContext ctx) {
        String name = ctx.TEXT().getText();
        Integer width = Integer.parseInt(ctx.NUM().getText());
        Reward reward = (Reward) ctx.reward().accept(this);
        return new Game(name, width, reward);
    }

    @Override
    public Level visitLevel(GameParser.LevelContext ctx) {
        Integer id = Integer.parseInt(ctx.NUM().getText());
        Speed speed = (ctx.speed() != null) ? (Speed) ctx.speed().accept(this) : Speed.DEFAULT_SPEED;
        List<Integer> wallIds = (ctx.withWalls() != null) ?
                ((Ids) ctx.withWalls().ids().accept(this)).getIds()
                : Collections.emptyList();
        List<Integer> fireballIds = (ctx.withFireballs() != null) ?
                ((Ids) ctx.withFireballs().ids().accept(this)).getIds()
                : Collections.emptyList();
        // <static check add>
        for (Integer wallId: wallIds) {
            staticCheck.wallIdsInStage.add(wallId);
        }
        for (Integer fbId: fireballIds) {
            staticCheck.fireballIdsInStage.add(fbId);
        }

        Map<Coordinate, Integer> coordinateToSubstageIdMap = new HashMap<>();

        for (GameParser.SubstageLocationContext s : CollectionUtils.emptyIfNull(ctx.substageLocation())) {
            Coordinate coordinate = (Coordinate) s.coordinate().accept(this);
            Integer substageID = Integer.parseInt(s.NUM().getText());
            coordinateToSubstageIdMap.put(coordinate, substageID);
            // <static check add>
            staticCheck.subStageIdsInLevel.add(substageID);
        }

        return new Level(id, speed, wallIds, fireballIds, coordinateToSubstageIdMap);
    }

    @Override
    public Substage visitSubstage(GameParser.SubstageContext ctx) {
        Integer id = Integer.parseInt(ctx.NUM().getText());
        Speed speed = (ctx.speed() != null) ? (Speed) ctx.speed().accept(this) : Speed.DEFAULT_SPEED;
        Score score = (ctx.score() != null) ? (Score) ctx.score().accept(this) : Score.DEFAULT_SCORE;
        List<Integer> wallIds = (ctx.withWalls() != null) ?
                ((Ids) ctx.withWalls().ids().accept(this)).getIds()
                : Collections.emptyList();
        List<Integer> fireballIds = (ctx.withFireballs() != null) ?
                ((Ids) ctx.withFireballs().ids().accept(this)).getIds()
                : Collections.emptyList();
        // <static check add>
        for (Integer wallId: wallIds) {
            staticCheck.wallIdsInStage.add(wallId);
        }
        for (Integer fbId: fireballIds) {
            staticCheck.fireballIdsInStage.add(fbId);
        }
        return new Substage(id, speed, wallIds, fireballIds, score);
    }

    @Override
    public Wall visitWall(GameParser.WallContext ctx) {
        Integer id = Integer.parseInt(ctx.NUM().getText());
        Integer height = Integer.parseInt(ctx.dimension().NUM(0).getText());
        Integer width = Integer.parseInt(ctx.dimension().NUM(1).getText());
        List<Coordinate> coordinates = new ArrayList<>();

        for (GameParser.CoordinateContext c : ctx.coordinates().coordinate()) {
            coordinates.add((Coordinate) c.accept(this));
        }

        return new Wall(id, height, width, coordinates);
    }

    @Override
    public Fireball visitFireball(GameParser.FireballContext ctx) {
        Integer id = Integer.parseInt(ctx.NUM().getText());
        Speed speed = (ctx.speed() != null) ? (Speed) ctx.speed().accept(this) : Speed.DEFAULT_SPEED;
        Integer y_coordinate = Integer.parseInt(ctx.y_coordinate().NUM().getText());

        Trigger trigger;
        if (ctx.trigger().loopTrigger() != null) {
            trigger = (Trigger) ctx.trigger().loopTrigger().accept(this);
        } else {
            trigger = (Trigger) ctx.trigger().staticTrigger().accept(this);
        }

        return new Fireball(id, speed, y_coordinate, trigger);
    }

    @Override
    public Trigger visitLoopTrigger(GameParser.LoopTriggerContext ctx) {
        return new Trigger(Integer.parseInt(ctx.NUM().getText()), TriggerFlavour.Loop);
    }

    @Override
    public Trigger visitStaticTrigger(GameParser.StaticTriggerContext ctx) {
        return new Trigger(Integer.parseInt(ctx.NUM().getText()), TriggerFlavour.Static);
    }

    @Override
    public Reward visitReward(GameParser.RewardContext ctx) {
        Integer value = Integer.parseInt(ctx.NUM(0).getText());
        Integer distance = Integer.parseInt(ctx.NUM(1).getText());
        return new Reward(value, distance);
    }

    @Override
    public Score visitScore(GameParser.ScoreContext ctx) {
        Operator operator = Score.parseOperator(ctx.OP().getText());
        Integer value = Integer.parseInt(ctx.NUM().getText());
        return new Score(operator, value);
    }

    @Override
    public Coordinate visitCoordinate(GameParser.CoordinateContext ctx) {
        Integer x = Integer.parseInt(ctx.NUM(0).getText());
        Integer y = Integer.parseInt(ctx.NUM(1).getText());
        return new Coordinate(x, y);
    }

    @Override
    public Speed visitSpeed(GameParser.SpeedContext ctx) {
        return new Speed(Integer.parseInt(ctx.NUM().getText()));
    }

    @Override
    public Ids visitIds(GameParser.IdsContext ctx) {
        Ids ids = new Ids();
        for (TerminalNode n : ctx.NUM()) {
            ids.getIds().add(Integer.parseInt(n.getText()));
        }
        return ids;
    }
}
