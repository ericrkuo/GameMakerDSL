## Static Check list
- enforce level id’s to be strictly increasing by 1
  (all other ids like substage, wall, fireball we don’t care)
- make sure all ids declared in level exist. 
  For example, check that wall ids declared inside “create level” each have a “create wall” statement. Same for 
  substage and fireball
- no duplicate declaration

## Dynamic Check List
- Check the integer overflow (e.g. ids, coordinates, speed)
  - Game width: 100 - 2000
  - reward value: 5 - 100
  - reward distance: 1 - 10
  - speed: 1 - 10
  - coordinate x: 0 - (game width / game unit)
  - coordinate y: 0 - 10 ( Game Height, 50 for a box)
  - score: 1 - 1000
- Duplicate coordinates with the same wall
