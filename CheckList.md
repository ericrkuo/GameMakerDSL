## Static Check list
- enforce level id’s to be strictly increasing by 1
  (all other ids like substage, wall, fireball we don’t care)
- make sure all ids declared in level exist. 
  For example, check that wall ids declared inside “create level” each have a “create wall” statement. Same for 
  substage and fireball
- no duplicate declaration
- Check the integer overflow (e.g. ids, coordinates, speed)