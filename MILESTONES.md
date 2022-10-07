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

# Milestone 3

## Mockup of Concrete Language Design
We mocked up our language design with some example inputs and gramamr.
- [ExampleInput.md](/ExampleInput.md#version-4)
- [DraftGrammar.md](/DraftGrammar.md)

Furthermore, we've also made substantial progress in implementing our lexer and parser. See https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/pull/5

## User Studies Results
We created example inputs and outputs for our user studies:
- [FirstUserStudyExampleInput.md](/FirstUserStudyExampleInput.md)
- [FirstUserStudyExampleOutput.pdf](/FirstUserStudyExampleOutput.pdf)

Here's the common feedback that we got from our user studies. Right now, our group is currently discussing and prioritizing which ideas to implement based on the results from our user studies.

- Change loop for fireballs to not be by time, but by distance. Users were having a hard time picturing how time flows in our game, and instead, suggested to not use a time based mechanism. (e.g. do every 50 units vs do every 2000 ms)
- Make condition for walls mandatory
- User’s suggested to make condition for walls mandatory. Originally, having no condition meant that the walls applied for every level and substage, but we should make the user explicitly declare it so it’s easier for others to understand
- BUG: User should be able to specify walls for only a substage, and also a level and a substage
    - Right now we say if level = 1, if substage = 1
    - But this is ambiguous if we mean a specific substage, or both the level and substage.
    - Also we can’t just say “if substage = 1” because there can be multiple levels
- BUG: We should indicate at what coordinate in x-axis the fireball gets triggered
    - Regardless of whether fireball is looping or not
    - Otherwise it’s ambiguous when the fireball gets triggered
- Word “length” is confusing, should be “width”
- Enforce stage id to be positive and somehow for users to write in ascending order
- Coordinates
    - What’s one block worth, 5 units? Hard to calculate
    - Where should coordinates of walls be. If I say put a wall of height 10 and width 5 at (5,5), is it the upper left corner of the wall that is at (5,5)?
- Users forget which walls they wrote and which they didn’t write
    - This feedback is crucial - we need to make our language easy to use
    - Possible solutions
    - https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/issues/7  
- Unnecessary words like of, and, a, handle, height, length, speed in our grammar

## Progress & Timeline
- [Here](https://docs.google.com/document/d/1sPjLjNMnLESEZp0rIRhdijkrM3di5Ryl8ud1PNJVrLE/edit#bookmark=kix.m4z93j95ajlk) is a breakdown of our planned tasks/work items along with our proposed deadlines. 
- We've also gotten started on the UI https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/tree/ast_design

## Notes of any important changes/feedback from TA discussion
Our TA Jifeng said "Your plans for the fourth milestone look very good!" and "Secondly, congratulations on your current progress (finishing your first
user study, and making substantial progress implementing the lexer and
parser). Your plans for the next week are clear as well."

Furthermore, Jifeng gave us a lot of great advice for building our AST such as using the traversal order of our parse tree to map to parts of our AST. He gave us very concrete and detailed feedback for this work item!

## Any planned follow-up tasks
In terms of next steps, we'll be finalizing our lexer/parser based on the feedback from our user studies, continue working on the UI, and implement the AST. We'll also be planning for our final user study for M4. 

# Milestone 4

## Status of implementation
Finished PR for lexer/parser 
- https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/pull/5 
Improved Lexer/Parser based on user studies
- https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/pull/8 
Added PR for ParseTree to AST conversion
- https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/pull/9 
Have begun working on UI
- https://github.students.cs.ubc.ca/CPSC410-2022W-T1/Project1Group12/tree/ui 

## Progress & Timeline
- [Here](https://docs.google.com/document/d/1sPjLjNMnLESEZp0rIRhdijkrM3di5Ryl8ud1PNJVrLE/edit#bookmark=kix.ambtxxky84t4) is a breakdown of our planned tasks/work items along with our proposed deadlines

## Plans for Final User Study
We’ll be conducting our final user study next Wednesday and we're aiming to finish our UI before then

## Notes of any important changes/feedback from TA discussion
Jifeng said we're on track, and encouraged us to visit his office hours or contact him if we have any questions regarding to connecting AST with UI, final uesr studies, or in general, any concerns!
