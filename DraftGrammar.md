# Draft Grammar

```js
program : game (level)+ (substage)* (obstacle)* ;

game : 'create a game called' TEXT widthDimension reward ;

// levels & substages
level : 'create level' NUM speed? withWalls? withFireballs? (substageLocation)* ;
substageLocation : 'if hit' coordinate 'go to substage' NUM ;
substage : 'create substage' NUM speed? withWalls* withFireballs* score? ;
withWalls: 'with walls' ids ;
withFireballs: 'with fireballs' ids ;
ids: (NUMBER (',' NUMBER)*) ;

// obstacles
obstacle : wall | fireball ;

wall : 'create walls' NUMBER
    dimension
    'at' (coordinate (',' coordinate)*) ;

fireball : 'create fireball' NUMBER trigger y_coordinate speed? ;
trigger : loopTrigger | staticTrigger ;
loopTrigger : 'trigger every ' NUMBER 'units' ;
staticTrigger : 'trigger at x=' NUMBER ; 

// obstacle/game configurations
dimension : 'of height' NUMBER 'and width' NUMBER ;
widthDimension : 'of width' NUMBER ;
reward : 'reward' NUMBER 'every' NUMBER 'units traveled'
score: 'score' OP NUM ;
coordinate : '(' NUMBER ',' NUMBER ')';
y_coordinate : 'at y=' NUMBER ;
speed: 'with speed' NUMBER ;

// other tokens
COMPARATOR: '>' | '<' | '=' ;
TEXT : [a-zA-Z0-9]+ ;
NUMBER: [0-9]+ ;
OP: '+' | '-' | '*' | '/';
```
