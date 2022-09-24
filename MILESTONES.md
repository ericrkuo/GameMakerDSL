# Milestone 1

TA: Jifeng  
Google Doc for our Project: https://docs.google.com/document/d/1sPjLjNMnLESEZp0rIRhdijkrM3di5Ryl8ud1PNJVrLE/edit?usp=sharing

## Brief description of your planned DSL idea

### Who will use our DSL?
University students

### For what purpose?
To make it easier to write math formulas using LaTeX.
Users would write some formulas in our DSL, and we would generate a LaTeX document for them, which they can then upload to a LaTeX editor like Overleaf, to render into a PDF.

### Why is it better than just a general-purpose programming language?
Our DSL will wrap the LaTeX host language into a simpler language that intentionally limits the power of the underlying language/framework. This will suit our users better by making LaTeX more concise and easy to use.
For example, users will not need to understand all the possible LaTeX commands that are out there, and do not need to worry about specifying braces (regular, square, curly).

## Ideas so far for main language features

1. Define complex equations
    - Can combine basic expressions with one another
    - E.g. nested equations, sub-expressions within a fraction, limits, summations, etc.
    - This feature allows infinite potential usage and allows features to be nested and composed together

2. Concisely specify fractions/matrices/vectors using loops, procedures or functions

3. Allow the user to define variables that can be referenced in other areas

## Notes of any important changes/feedback from TA discussion

Jifeng provided us with the following feedback based on our example inputs we had:
- To make the language more complex by encorproating conditionals & control flow
- Potentially make it an imperative language and to allow the user to type function calls
- He also built on top of our existing ideas to introduce some preliminary thoughts on how we can use loops
```
Multiply
    Fraction
        Numerator i
        Denominator i + 1
For i from 2 to 10

Matrix
    Rows 4
    Columns 4
    Content ( (1 if i = j else 0) for i from 1 to 4 for j from 1 to 4 )
```
## Any planned follow-up tasks or features still to design
- To dig deeper and think of more ways users can use our features, and how they can express them
    - We plan on syncing up over the weekend to further hash out our ideas
- Potentially consider other ideas for our project as we're a bit hesitant how useful our DSL would be
- Have a sync up meeting to discuss and plan for Milestone 2
- Explore ANTLR and play around with it locally


# Milestone2

## New project idea
- Our new project idea is an endless runner game. The idea is that the character has to avoid obstacles like walls and fireballs and move past a series of levels.
- Example input: 
[Here's](https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/blob/758cb12bb7921a6a2c5d3084e4ab0b060e51334e/ExampleInput.md#version-4) what an example input by our user would look like

## Plan divison of main responsibilties between team members & timeline
- [Link](https://docs.google.com/document/d/1sPjLjNMnLESEZp0rIRhdijkrM3di5Ryl8ud1PNJVrLE/edit#bookmark=id.o6jjuopxhx01)

## Draft grammer
- [Link](https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/blob/758cb12bb7921a6a2c5d3084e4ab0b060e51334e/DraftGrammar.md)

## Summary of progress so far
- Defined tasks, assigned team members, and set deadlines
- Drafted several implementations of example user input
- Created a draft grammar - https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/pull/2
- Boostrapped repo - https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/pull/3 

## Notes of any important changes/feedback from TA discussion

Jifeng provided us with the following feedback based on our example inputs we had:

- Currently, our progress looks really well. Furthermore, our time schedule is very good. He told us to keep up the good work.
- We already have the enough features to satisfy the requirements but feel free to add extra after we have done the core implementations.
- To prioritize formalizing our grammar and conducting user studies next week.

## Any planned follow-up tasks
- Formalize our grammer
- Work on Lexer and Parser
- Conduct user studies and reiterate on our grammar based on feedback
- AST Design
- Concurrent work on UI for game platform
