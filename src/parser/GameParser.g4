parser grammar GameParser;
options { tokenVocab=GameLexer; }

program: game (level)+ (substage)* (wall)* (fireball)* EOF;
game: GAME_START TEXT WIDTH_DIMENSION NUM reward;

// game config
dimension : DIMENSION_START NUM DIMENSION_SEP NUM;
reward: REWARD_START NUM REWARD_SEP NUM UNITS TRAVELED;
speed: SPEED NUM;
coordinate: LEFT_BRACE NUM COMMA NUM RIGHT_BRACE;
coordinates: (coordinate (COMMA coordinate)*);
score: SCORE OP NUM;
y_coordinate: Y_COORDINATE NUM;

// level & staging
level: LEVEL_START NUM speed? withWalls? withFireballs? (substageLocation)*;
substage: SUBSTAGE_START NUM speed? withWalls? withFireballs? score?;
substageLocation : SUBSTAGE_LOCATION_START coordinate SUBSTAGE_LOCATION_SEP NUM;
withWalls: WITH_WALL ids;
withFireballs: WITH_FB ids;
ids: (NUM (COMMA NUM)*);

// obstacles
wall: WALL_START NUM dimension WALL_SEP coordinates;
fireball: FIREBALL_START NUM trigger y_coordinate speed?;
trigger: loopTrigger | staticTrigger;
loopTrigger: LOOP_TRIGGER NUM UNITS;
staticTrigger: STATIC_TRIGGER NUM;
