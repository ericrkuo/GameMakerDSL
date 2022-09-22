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
create a game
  called MyGame
  of height 20 and length 60
  // TODO define a goal
  finish at level 4
  // TODO scoring - should indicate by distance

// Feature 1: nested stages
// TODO what happens if miss the stage
create level 1
  with speed 2
  if hit x=2 and y=10
    go to substage A
  if hit x=100 and y=50
    go to substage B

  // TODO clarify grammar to make it distinct between stage and substage
  create substage A
    with speed 3
    exit at x=2 and y=10
    score * 2 // double points

  create substage B
    at x=100 and y=4
    score + 500

create level 2
  with speed 2
  if hit x=2 and y=10
    go to substage A // TODO substages with the same name but different level

  create substage A
    with speed 3
    exit at x=2 and y=10
    score * 2 // double points

// wall obstacles
create a wall
  of height 5 and length 1
  at x=3 and y=10

// Feature 2: Object creation dependent upon some condition
create walls
  of height 1 and length 3
  at (0, RANDOM), (10, 10), (20, 30)
  // TODO think about if condition for level or stage
  if level > 2
  if substage > 1 // idea: can reuse this to make future stages harder

  // TODO can consider AND/OR conditions

// Feature 3: Loops for fireballs
do every 2000 ms
  create a fireball
    at y=4
    with speed 2
    if stage>2
```