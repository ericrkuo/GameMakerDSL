parser grammar EndlessRunnerMakerParser;
options { tokenVocab=EndlessRunnerMakerLexer; }

program: game (level)+ (obstacle)* (loop)* EOF;
game: GAME_START TEXT dimension reward;

// level & staging
level: LEVEL_START NUMBER speed? (substageLocation)* (substage)*;
substageLocation : SUBSTAGE_LOCATION_START coordinate SUBSTAGE_LOCATION_SEP NUMBER;
substage : SUBSTAGE_START NUMBER speed? score?;

// obstacles
obstacle : wall | fireball;
wall : WALL_START
    dimension
    WALL_SEP (coordinate (COORDINATES_SEP coordinate)*)
    condition? ;
fireball : FIREBALL_START y_coordinate speed? condition? ;

// control flow
condition: levelCondition substageCondition?;
levelCondition : LEVEL_CONDITION COMP NUMBER ;
substageCondition : SUBSTAGE_CONDITION COMP NUMBER;
loop: LOOP NUMBER MS fireball;

// obstacle / game config
dimension: DIMENSION_START NUMBER DIMENSION_SEP NUMBER;
reward: REWARD_START NUMBER REWARD_SEP NUMBER REWARD_END;
speed: SPEED NUMBER;
coordinate : COORDINATE_START COORDINATE_NUMBER COORDINATE_SEP COORDINATE_NUMBER COORDINATE_END;
score: SCORE OP NUMBER;
y_coordinate : Y_COORDINATE NUMBER;