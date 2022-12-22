# Game Maker DSL

![GameUPDATED](https://user-images.githubusercontent.com/49849754/209035730-2cacc6de-eeb5-4814-b659-d05a56e0fc3f.gif)

## Use case and motivation
Our DSL is a game maker that combines some of our favourite childhood games like Mario, JetPack Joyrider, and Flappy Bird. The motivation behind our DSL was to reduce the learning curve of having to learn complex frameworks such as Unity and Blender, and instead, allow users to put their focus entirely on the layout of the game so that users with little to no programming experience to be able to create their own games by specifying the placement design of obstacles. 

## Running our project
1. First, import all the required libraries for our project.
    - In IntelliJ Idea, navigate to `File > Project Structure > Libraries`
    - Add all the `.jar`. files inside [lib](lib)
    - ![image](https://media.github.students.cs.ubc.ca/user/1272/files/b959a973-dfb7-4b20-8aa1-1e2f7f761aeb)
2. Generate all ANTLR files inside [parser](src/parser) using an IDE extension
    - In IntelliJ, open the file pane and find `GameLexer.g4`, `GameParser.g4` in [`src/parser`](src/parser)
3. Run [`src/ui/Main.java`](src/ui/Main.java)

## Playing our Game
- Use the `↑` and `↓` keys to navigate the character
- Dodge fireballs and walls
- Enter portals to go into substages! If you die in a substage, you go back to the beginning of the level.
- The goal is to finish all the levels
- Press 'R' to restart if you die, 'SPACE' to start the game, 'P' to pause the game

## Modifying Example Input
Feel free to modify [`src/inputs/input.txt`](src/inputs/input.txt) by following the grammar below.

You can also use any `.txt` file inside [`src/inputs`](src/inputs) by changing [`src/ui/Main.java`](src/ui/Main.java) to use the desired file.

> NOTE: The coordinate system has origin (0,0) at the top left corner and uses the concept of **game units**. Each game unit is 50 pixels. So since the height of our game is 500 pixels, the valid y-coordinates for walls and fireballs range from 0-9 game units.

> NOTE: Walls can overlap with each other. We decided not to make this a static check since we thought it would be too restrictive on users and would make it difficult for them to debug.

Try out [`CheckList.md`](CheckList.md) to experiment with the static and dynamic checks we support. The error messages will appear in the console output.

## Example Input and Output

```javascript
create a game
  called MyGame
  of width 24
  reward 50 every 1 units traveled

create level 1
  with speed 3
  with walls 1, 2
  with fireballs 1, 2, 3, 4
  if hit (16,6)  go to substage 1
  if hit (20,4) go to substage 2

create level 2
  with speed 4
  with walls 2
  if hit (10,0)   go to substage 1
  if hit (16,6) go to substage 3

create substage 1
  with speed 5
  with walls 1
  with fireballs 1, 3
  score * 2

create substage 2
  with speed 4
  with walls 2
  with fireballs 2
  score + 500

create substage 3
  with speed 3
  with walls 1, 2
  with fireballs 2
  score * 3

create walls 1
  of height 1 and width 1
  at (23, 0)

create walls 2
  of height 1 and width 3
  at (0, 0), (8, 2), (16, 8)

create fireball 1
  trigger every 1 units
  at y=3
  with speed 2

create fireball 2
  trigger at x=14
  at y=3
  with speed 3

create fireball 3
  trigger at x=18
  at y=6
  with speed 1

create fireball 4
  trigger every 1 units
  at y=8
  with speed 3
```
## Grammar

```js
program : game (level)+ (substage)* (wall)* (fireball)* EOF ;
game : 'create a game called' TEXT widthDimension reward ;

// game config
dimension : 'of height' NUM 'and width' NUM;
reward: 'reward' NUM 'every' NUM 'units traveled';
speed: 'with speed' NUM;
coordinate: '(' NUM ',' NUM ')';
coordinates: (coordinate (',' coordinate)*);
score: 'score' OP NUM;
y_coordinate: 'at y=' NUM;

// level & staging
level: 'create level' NUM speed? withWalls? withFireballs? (substageLocation)*;
substage: 'create substage' NUM speed? withWalls? withFireballs? score?;
substageLocation : 'if hit' coordinate 'go to substage' NUM;
withWalls: 'with walls' ids;
withFireballs: 'with fireballs' ids;
ids: (NUM (',' NUM)*);

// obstacles
wall: 'create walls' NUM dimension 'at' coordinates;
fireball: 'create fireball' NUM trigger y_coordinate speed?;
trigger: loopTrigger | staticTrigger;
loopTrigger: 'trigger every' NUM 'units';
staticTrigger: 'trigger at x=' NUM;

// other tokens
NUM: [0-9]+;
OP: '+' | '-' | '*' | '/';
COMP: '>' | '<' | '=';
TEXT: [a-zA-Z0-9]+;
```

## Code Structure
- [`src/ast`](src/ast) contains all the files we used to build our AST
- [`src/ui`](src/ui) contains all the files we used to build our UI
- Collision Detector Visitor Pattern
    - We used the visitor design pattern to implement our collision detection system
    - Relevant classes include: [CollisionVisitor](https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/blob/main/src/ui/CollisionVisitor.java) and  [CollisionDetector](https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/blob/main/src/ui/CollisionDetector.java)

## Past versions of our DSL
Please see [`ExampleInput.md`](ExampleInput.md) for previous versions of our DSL.

## Future work
- [ ] Preview/visualize layout of game
- [ ] Allow ID’s to be names or support comments
    - E.g. create wall Top3By4Wall
    - E.g. create substage DifficultSubstageLotsOfWalls
