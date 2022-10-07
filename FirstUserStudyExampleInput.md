# Inputs for First User Study

## Example input
```js
create a game
  called MyGame
  of height 40 and length 150
  reward 50 every 5 units traveled

create level 1
  with speed 2
  if hit (60,20)  go to substage 1

  create substage 1
    with speed 3
    score * 2

// shared across levels and substages
create walls
  of height 10 and length 5
  at (20,0), (50,20), (80,0)
  if level = 1
  if substage > 0

create walls of height 15 and length 5
  at (30,25), (60,25)
  // TODO need to fix conditions
  if level = 1
  if substage > 0

create walls of height 25 and length 5
  at (80,15)
  if level = 1
  if substage > 0

create walls of height 5 and length 45
  at (105,0)
  if level = 1
  if substage > 0

// specific to level 1
create walls of height 15 and length 5
  at (125,10)
  if level = 1

create walls of height 5 and length 20
  at (45,10), (105,35)
  if level = 1

create walls of height 5 and length 30
  at (95,15)
  if level = 1

// specific to substage 1
create walls of height 25 and length 45
  at (105,15)
  // TODO need to fix conditions
  if level = 1
  if substage = 1

create walls of height 10 and length 40
  at (35,0)
  if level = 1
  if substage = 1

do every 2000 ms
  create fireball
    at y=30
    with speed 2
    if level = 1
    if substage = 1

create fireball
  at y=10
  with speed 4
  if level = 1
  if substage = 1
```

## Test Input (DO NOT SHOW - used as "answer key" to compare with what test user produces)
```js
create a game
  called MyGame
  of height 40 and length 100
  reward 50 every 5 units traveled

create level 1
  with speed 2
  if hit (50,35)  go to substage 1

  create substage 1
    with speed 3
    score * 2

// shared across levels and substages
create walls
  of height 10 and length 5
  at (30,0), (75,30)
  if level = 1
  if substage > 0

create walls of height 25 and length 5
  at (30,15), (75,0)
  if level = 1
  if substage > 0

// specific to level 1
create walls of height 10 and length 5
  at (50,20)
  if level = 1

create walls of height 5 and length 20
  at (45,10)
  if level = 1

create fireball
  at y=10
  with speed 4
  if level = 1

// specific to substage 1
create walls of height 10 and length 30
  at (45,10)
  if level = 1
  if substage = 1

create walls of height 15 and length 10
  at (55,25)
  if level = 1
  if substage = 1
```