lexer grammar EndlessRunnerMakerLexer;

// (DEFAULT MODE)
GAME_START: 'create a game' WS* 'called' WS* -> mode(TEXT_MODE);
DIMENSION_START: 'of height' WS* -> mode(NUM_MODE);
DIMENSION_SEP: 'and length' WS* -> mode(NUM_MODE);
REWARD_START: 'reward' WS* -> mode(NUM_MODE);
REWARD_SEP: 'every' WS* -> mode(NUM_MODE);
REWARD_END: 'units traveled';

// level & staging
LEVEL_START: 'create level' WS* -> mode(NUM_MODE);
SPEED: 'with speed' WS* -> mode(NUM_MODE);
SUBSTAGE_LOCATION_START: 'if hit' WS* -> mode(COORDINATE_MODE);
SUBSTAGE_LOCATION_SEP: 'go to substage' WS* -> mode(NUM_MODE);
SUBSTAGE_START: 'create substage' WS* -> mode(NUM_MODE);
SCORE: 'score' WS* -> mode(NUM_MODE);

// obstacles
WALL_START: 'create walls';
WALL_SEP: 'at' WS* -> mode(COORDINATE_MODE);
COORDINATES_SEP: ',' WS* -> mode(COORDINATE_MODE);
FIREBALL_START: 'create fireball';
Y_COORDINATE: 'at y=' WS* -> mode(NUM_MODE);

// control flow
LEVEL_CONDITION: 'if level' WS* -> mode(NUM_MODE);
SUBSTAGE_CONDITION: 'if substage' WS* -> mode(NUM_MODE);
LOOP: 'do every' WS* -> mode(NUM_MODE);
MS: 'ms';

// space & new line
WS: [\r\n\t ]+ -> channel(HIDDEN);

mode TEXT_MODE;
TEXT: [a-zA-Z0-9]+ -> mode(DEFAULT_MODE);

mode NUM_MODE;
NUMBER: [0-9]+ -> mode(DEFAULT_MODE);
COMP: '>' | '<' | '=';
OP: '+' | '-' | '*' | '/';
WS_NUM: [\r\n\t ]+ -> channel(HIDDEN);

mode COORDINATE_MODE;
COORDINATE_START: '(';
COORDINATE_SEP: ',';
COORDINATE_END: ')' -> mode(DEFAULT_MODE);
COORDINATE_NUMBER: [0-9]+;
WS_COORDINATE: [\r\n\t ]+ -> channel(HIDDEN);

