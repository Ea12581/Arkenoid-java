# Arkenoid-java
This is an exrecise I have beuild in Java douring my OOP course In Bar Ilan University.
The game is Arkenoid. There is a paddle who bounce up a ball\balls and the goal is to hit the barackets and gain points.
If one ball fall down under the paddle, he is destroyed. If all the balls are getting destroyed, the game is over.

To run The game, It's needed Ant-Apache to be installed, the biuld file is an xml file called "build".

There are two options to run the game(open cmd inside the folder you saved the files):

1)ant run

would run the game's four levels, 1-4 and finish.

2) ant -Dargs="4" run

would take the arguments between the " " and run the levels by order based on this args (1 is level 1 etc until level 4).
every char besides 1,2,3,4 would be ignored.

*tip:
you can press "p" through the game to freez it untill you press space.
If you won the game or got game over, you would get a screen that tells you how many points you have got. every baracket worth 5, every level
worth 100.

some background:
I have used GUI which was supplised by the staff of the course (biuoop-1.4.jar file)
I have used design pattern "listener" which the almost every object in the game was listening to when the ball hit something and what to do according to that hit.
For exemple, the ball was listening if he hit somthing and change his vector do to the angle of the hit, baracket got desepired and tells to counter to reise the
points etc.

It's was a lot of fun, i hope you would enjoy!
