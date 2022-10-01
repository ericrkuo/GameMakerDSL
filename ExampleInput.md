# Example Input


## Version 1
```js
Game: Stage1

For i from 1 to 3
  Stage: 
    Name: Stage{i}
    Length: 60
      Speed: 10 * i
      NextStage: Stage{i+1} if i < 3 else None

Obstacle:
  Height: 5
  Location:
    Stage: Stage1
    Coordinate: (10, 10)

Condition:
  IF:
    Action: Pass
    Location:
      Stage: Stage2
      X: 30
      Y: infine (<= if the birds pass at x = 30 on stage2, it happens)

Def FireballEffect
  Argument: StageName
  Body:
    Fireball:
      Radius: 5
      Location:
        Stage: StageName
        Coordinate: (10, 40)
```

## Version 2
Main changes
- Make the DSL simpler
- Each obstacle can have a condition


```js
create game
  name: My Game
  height: 20
  width: 60

// Feature 1: Loop to define multiple levels
create stage for i from 1 to 3
  name: Stage{i}
  // speed increases with each level
  speed: i

// wall obstacles
create wall
  height: 5
  at x=3 and y=10

// Feature 2: Object creation dependent upon some condition
create wall
  height: 3
  at x=0 and y=RANDOM
  if stage=2

// Feature 3: Loops for objects
do every 2000 ms
  create fireball
    speed: 2
    radius: 3
    at y=4
    if stage>=2
```

## Version 3
Main changes
- Make the DSL even simpler

```js
create a game
  called MyGame
  of height 20 and length 60

// Feature 1: nested stages
create stage 1
  with speed 2

create stage 2
  with speed 3
  with parent stage 1
  at x=2 and y=10
  score * 2

create stage 3
  with parent stage 2
  at x=100 and y=4
  score + 500

// wall obstacles
create a wall
  of height 5 and length 1
  at x=3 and y=10

// Feature 2: Object creation dependent upon some condition
create a wall
  of height 1 and length 3
  at x=0 and y=RANDOM
  if stage=2

// Feature 3: Loops for objects
do every 2000 ms
  create a fireball
    at y=4
    with speed 2
    if stage>2
```

## Version 4
Main changes
- Polish definition of stages and levels (more similar to substages in mario)
- Array-like declaration of walls

```js
/**
 * Creation of a game
*/
create a game
  called MyGame
  of height 50 and length 200
  reward 50 every 5 units traveled

/**
 * Feature 1: Levels and nested stages
 * - Each level can have mini levels called substages. This is similar to Super Mario Bros where the Mario can enter into another substage through the green pipe
 * - Substages can be more challenging, and thus can reward with score multipliers 
 * - If a substage is not entered, the character moves to the end of the level and onto the next level
 * - When a character reaches the end of a substage, they return back to where they were in the level
*/
create level 1
  with speed 2
  if hit (2,10)  go to substage 1
  if hit (50,25) go to substage 2

  create substage 1
    with speed 3
    score * 2

  create substage 2
    with speed 4
    score + 500

create level 2
  with speed 2
  if hit (20,20) go to substage 1

  create substage 1
    with speed 3
    score * 3

/**
 * Obstacle: walls
 * - walls of varying dimensions can be placed in levels and substages
 * - walls can be reused across levels and substages
*/
create walls
  of height 5 and length 1
  at (3,10)

/**
 * Feature 2: Object creation dependent upon some condition
 * - Walls can be reused across future levels or stages to make them more difficult
 * - Walls can be applied to specific substages or levels
*/
create walls
  of height 1 and length 3
  at (0, 20), (10, 10), (20, 30)
  if level = 1
  if substage > 0

/**
 * Feature 3: Loops for fireballs
 * - Fireballs come from the right side of the screen, move left, and disappear once they exit the screen
 * - Fireballs take precedence over walls
 * - Loops can be defined to reuse fireballs
*/
do every 2000 ms
  create fireball
    at y=4
    with speed 2
    if level > 2
```

## Version 5
Main changes

```js
/**
 * Creation of a game
*/
// TODO change grammar, removed height, changed length to width
create a game
  called MyGame
  of width 200
  reward 50 every 5 units traveled

/**
 * Feature 1: Levels and nested stages
 * - Each level can have mini levels called substages. This is similar to Super Mario Bros where the Mario can enter into another substage through the green pipe
 * - Substages can be more challenging, and thus can reward with score multipliers 
 * - If a substage is not entered, the character moves to the end of the level and onto the next level
 * - When a character reaches the end of a substage, they return back to where they were in the level
*/
create level 1
  with speed 2
  with walls 1, 2
  with fireballs 1
  if hit (2,10)  go to substage 1
  if hit (50,25) go to substage 2

create level 2
  with speed 2
  with walls 1
  if hit (5,5)   go to substage 1 // reuse of substage across levels
  if hit (20,20) go to substage 3

create substage 1
  with speed 3
  with walls 1
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

/**
 * Obstacle: walls
 * - walls of varying dimensions can be placed in levels and substages
 * - walls can be reused across levels and substages
*/
create walls 1
  of height 5 and width 1
  at (3,10)

// TODO polish up this documentation and all other documentation
/**
 * Feature 2: Object creation dependent upon some condition
 * - Walls can be reused across future levels or stages to make them more difficult
 * - Walls can be applied to specific substages or levels
*/
create walls 2
  of height 1 and width 3
  at (0, 20), (10, 10), (20, 30)

/**
 * Feature 3: Loops for fireballs
 * - Fireballs come from the rightmost side of the screen/frame, move left, and disappear once they exit the screen
 * - Fireballs take precedence over walls
 * - Loops can be defined to reuse fireballs
*/
// TODO change grammar - units and loops changed
create fireball 1
  trigger every 50 units
  at y=4
  with speed 2

create fireball 2
  trigger at x = 30
  at y=4
  with speed 2
```
