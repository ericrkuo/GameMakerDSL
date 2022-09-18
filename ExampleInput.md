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
    if stage=2
```
