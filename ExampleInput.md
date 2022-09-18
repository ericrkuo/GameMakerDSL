# Example Input

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
