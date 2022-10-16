# Project1Group12

## Running our project
1. First, import all the required libraries for our project.
    - ![image](https://media.github.students.cs.ubc.ca/user/1272/files/b959a973-dfb7-4b20-8aa1-1e2f7f761aeb)
    - This can be done by importing all the `.jar`. files inside [lib](lib)
2. Generate all ANTLR files inside [parser](src/parser) using an IDE extension
3. Run [Main](src/ui/Main.java)

## Playing our Game
- Use the `↑` and `↓` to navigate the character
- Dodge fireballs and walls
- Enter portals to go into substages! If you die in a substage, you go back to the beginning of the level.
- The goal is to finish all the levels

## Grammar

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

## User Study Summary
We conducted the final user study as follows:

1. explain the grammar of our DSL
2. show the user what a desired output (game) looks like
3. ask them to recreate the desired output using our grammar
4. compare the users code with our desired input (code that should generate the desired output)

#### Final user study

- [Desired Output](./SecondUserStudyExampleOutput.pdf)
- [Desired Input](./SecondUserStudyExampleOutput.pdf)

#### First user study

- [Desired Output](./FirstUserStudyExampleOutput.pdf)
- [Desired Input](./FirstUserStudyExampleInput.md)

### Notes from the final user study
#### User's confusion
- Game units vs pixel units 
    - E.g. Units of wall heights and widths (game units of 50px) vs the coordinate units (which are in pixels)
- Scoring system 
    - E.g. Whether the score multiplier in the substage applies to the current score, or the reward rate (reward 50 every 5 unites travelled)
- Inconsistency of plurality 
    - E.g. “with fireballs” vs “create fireball”
- Ordering of statements
    - E.g. First declare levels, substages, walls, then fireballs. Our parser enforces this order
    - E.g. Within level/substage declaration, first speed, then walls, fireballs, then either substage location or score depending on if level or substage.

#### User's suggestions
- A way for the user to preview/visualize what their layout looks like
    - E.g. The user may want to see what level 10 looks like without having to reach that level
- Removing redundant words such as “travelled” from “every 50 units travelled”
- String name for objects rather than restricting to integer
    - E.g. create wall myWallForLevel1
- Add the ability to add comments
- In the “create wall” statement, to split up height and width declaration
    - E.g. “with width 20 NEWLINE with height 100”


### Notes from the first user study
#### User's confusion
- Users forget which walls they wrote and which they didn’t write
    - we decided to change the language design so that we can declare in-game objects and reuse them in declarations of stages
- Users were having a hard time calculating coordinates in pixels
    - we decided to introduce game unit, which is equal to a side of a block
- Users were having a hard time picturing how time flows in our Game 
    - we decided to instead use a time based mechanism.
        - e.g. from `do every 2000 ms` to `do every 50 units` 

## Future work
- [ ] Preview/visualize layout of game
- [ ] Allow ID’s to be names or support comments
    - E.g. create wall Top3By4Wall
    - E.g. create substage DifficultSubstageLotsOfWalls
