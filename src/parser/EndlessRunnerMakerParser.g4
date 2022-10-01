parser grammar EndlessRunnerMakerParser;
options { tokenVocab=EndlessRunnerMakerLexer; }

program: game (level)+ (substage)* (obstacle)* EOF ;
game: GAME_START TEXT widthDimension reward ;

// game config
dimension : DIMENSION_START NUM DIMENSION_SEP NUM ;
widthDimension: WIDTH_DIMENSION NUM;
reward: REWARD_START NUM REWARD_SEP NUM UNITS;
speed: SPEED NUM;
coordinate: LEFT_BRACE NUM COMMA RIGHT_BRACE;
coordinates: (coordinate (COMMA coordinate)*);
score: SCORE OP NUM;
y_coordinate: Y_COORDINATE NUM;
x_coordinate: X_COORDINATE TRIGGER_NUM;

// level & staging
level: LEVEL_START NUM speed? withWalls? withFireballs? (substageLocation)*;
substage: SUBSTAGE_START NUM speed? withWalls? withFireballs? score? ;
substageLocation : SUBSTAGE_LOCATION_START coordinate SUBSTAGE_LOCATION_SEP NUM;
withWalls: WITH_WALL ids;
withFireballs: WITH_FB ids;
ids: (NUM (COMMA NUM)*);

// obstacles
obstacle: wall | fireball;
wall: WALL_START NUM dimension WALL_SEP coordinates;
fireball: FIREBALL_START NUM trigger y_coordinate speed?;
trigger: loopTrigger | staticTrigger;
loopTrigger: EVERY NUM UNITS;
staticTrigger: TRIGGER x_coordinate;
