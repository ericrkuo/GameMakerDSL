# Draft Grammar

```js
program : game (level)+ (obstacle)* (loop)* ;

game : 'create a game called' TEXT dimension reward ;

// levels & substages
level : 'create level' NUM speed? (substageCondition)* (substage)* ;
substageCondition : 'if hit' coordinate 'go to substage' NUM ;
substage : 'create substage' NUM speed? score? ;

// obstacles
obstacle : wall | fireball ;

wall : 'create walls' 
    dimension
    'at' (coordinate (',' coordinate)*)
    condition? ;

fireball : 'create fireball' y_coordinate speed? condition? ;

// control flow
condition: levelCondition substageCondition? ;
levelCondition : 'if level' COMPARATOR NUMBER ;
substageCondition : 'if substage' COMPARATOR NUMBER ;
loop: 'do every' NUMBER 'ms' fireball;

// obstacle/game configurations
dimension : 'of height' NUMBER 'and length' NUMBER ;
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
