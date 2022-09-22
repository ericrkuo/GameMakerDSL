# Draft Grammar

```js
program : game stages (obstacle)* (loop)* ;

game : 'create a game called' TEXT dimension ;

// stages
stages : stage nestedStage* ;
stage: 'create stage' NUM speed? ;
nestedStage: stage parent coordinate score ;
parent: 'with parent stage' NUM ;
score: 'score' OP NUM ;

// obstacles
obstacle : wall | fireball ;

wall : 'create a wall' dimension coordinate condition? ;

fireball : 'create a fireball' coordinate speed? condition? ;

// control flow
condition: 'if stage' COMPARATOR NUMBER;
loop: 'do every' NUMBER 'ms' fireball;

// obstacle configurations
dimension : 'of height' NUMBER 'and length' NUMBER ;
coordinate : 'at x=' XPOS 'and y=' YPOS | 'at y=' YPOS ;
speed: 'with speed' NUMBER ;

// other tokens
COMPARATOR: '>' | '<' | '=' ;
XPOS : NUMBER ;
YPOS : NUMBER ;
TEXT : [a-zA-Z]+ ;
NUMBER: [0-9]+ ;
OP: '+' | '-' | '*' | '/';
```
