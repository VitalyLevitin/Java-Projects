Greetings reader and thank you for the opportunity.

Quick thought process for the challenge and few notes:

The idea behind my code is that in order to balance the carpet I need to
Pay attention to all sides.
So by checking cross quarters I managed to simultaneously check all sides.
(or in other words every hunter needs to have anti-symmetric counterpart)

So I counted in those quarters for two things: Hunters and empty spaces.
The Hunters count was to check whenever balance is possible (if not balanced already).
The empty spaces count happened for two reasons,
first one was to remove x amount of empty spaces that got occupied for carpet balancing.
and the second reason was to check how many additional hunters it's possible to add.

In the odd counter I checked the middle row/col separately to avoid double counting.

Notes:
1) Sorry about the random +1 around the code.
Java arrays start at 0 and the challenge had them starting at 1, so had to balance somehow.
2) I programmed the code as [row][col]. I'm pretty sure that in the challenge example
it was [col][row]. Not sure how much it affects the answer since it's pretty much mirroring.
3) I really couldn't figure out my huge answers after the 100th test,
and of course the "java run out of heap space" at test #141.
If it's possible I would really appreciate some kind of clue on how I should of 
approached the error/debugging it.

Thank you very much for reading and having this challenge,
Vitaly Levitin.