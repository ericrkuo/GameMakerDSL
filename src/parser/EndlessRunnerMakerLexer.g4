lexer grammar EndlessRunnerMakerLexer;

// (DEFAULT MODE)
GAME_START: 'create a game called' WS* -> mode(TEXT_MODE);
WIDTH_DIMENSION: 'of width';
DIMENSION_START: 'of height';
DIMENSION_SEP: 'and width';

REWARD_START: 'reward';
REWARD_SEP: 'every';
UNITS: 'units traveled';

// level & staging
LEVEL_START: 'create level';
SUBSTAGE_START: 'create substage';
SPEED: 'with speed';
WITH_WALL: 'with wall';
WITH_FB: 'with fireballs';
SUBSTAGE_LOCATION_START: 'if hit';
SUBSTAGE_LOCATION_SEP: 'go to substage';
SCORE: 'score';

// num, comma, braces, operation, comp
COMMA: ',';
NUM: [0-9]+;
LEFT_BRACE: '(';
RIGHT_BRACE: ')';
OP: '+' | '-' | '*' | '/';
COMP: '>' | '<' | '=';

// obstacles
WALL_START: 'create walls';
WALL_SEP: 'where';
FIREBALL_START: 'create fireball';
TRIGGER: 'trigger' WS* -> mode(TRIGGER_MODE);
EVERY: 'do every';
Y_COORDINATE: 'at y=';

// space & new line
WS: [\r\n\t ]+ -> channel(HIDDEN);

mode TEXT_MODE;
TEXT: [a-zA-Z0-9]+ -> mode(DEFAULT_MODE);

mode TRIGGER_MODE;
X_COORDINATE: 'at x=';
TRIGGER_NUM: [0-9]+ -> mode(DEFAULT_MODE);
TRIGGER_WS: [\r\n\t ]+ -> channel(HIDDEN);