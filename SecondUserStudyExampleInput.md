# Inputs for First User Study

## Example input
```js
create a game
    called MyGame
    of width 1500
    reward 50 every 5 units traveled

create level 1
    with speed 3
    with walls 1, 2, 3, 4, 5, 6
    if hit (600, 200)  go to substage 1

create substage 1
    with speed 5
    with walls 1, 2, 6, 7, 8, 9
    with fireballs 1, 2, 3
    score * 2

create walls 1
    of height 2 and width 1
    at (200,0), (800,0), (500,200)

create walls 2
    of height 3 and width 1
    at (350, 300), (600, 350), (1250, 100)

create walls 3
    of height 1 and width 4
    at (450, 100), (1050, 350)

create walls 4
    of height 5 and width 1
    at (800, 250)

create walls 5
    of height 1 and width 6
    at (950, 150)

create walls 6
    of height 1 and width 9
    at (1050, 0)

create walls 7
    of height 2 and width 6
    at (350, 0)

create walls 8
    of height 5 and width 9
    at (1050, 150)

create walls 9
    of height 5 and width 1
    at (800, 150)

create fireball 1
    trigger at x=400
    at y=300
    with speed 2

create fireball 2
    trigger at x=950
    at y=300
    with speed 3

create fireball 3
    trigger at x=1400
    at y=100
    with speed 1
```

## Test Input (DO NOT SHOW - used as "answer key" to compare with what test user produces)
```js
create a game
    called MyGame
    of width 1000
    reward 50 every 5 units traveled

create level 1
    with speed 2
    with walls 1, 2, 3, 4, 5
    with fireballs 1
    if hit (500, 350)  go to substage 1

create substage 1
    with speed 3
    with walls 1, 3, 6, 7
    score * 2

create walls 1
    of height 2 and width 1
    at (300, 0), (500, 200), (750, 300)

create walls 2
    of height 3 and width 1
    at (600, 250)

create walls 3
    of height 5 and width 1
    at (300, 250), (750, 0)

create walls 4
    of height 1 and width 4
    at (450, 100)

create walls 5
    of height 3 and width 1
    at (600, 250)

create walls 6
    of height 1 and width 6
    at (450, 100)

create walls 7
    of height 3 and width 2
    at (550, 250)

create fireball 1
    trigger at x=900
    at y=100
    with speed 3
```
