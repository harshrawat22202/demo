
# AP PROJECT

## Group 29
Name: Manveet Singh Roll No.:2022280            
Name: Harsh Rawat   Roll No.:2022202

## Description
For our AP course project, I'm developing a JavaFX game inspired by Stick Hero. The goal is to recreate the core mechanics of Stick Hero while adding a unique touch to enhance the gaming experience.

## How to Run
### First method
->Open the project folder in Intellij.    
->Click the run method in Main class.


### Second method
->Open terminal in the project folder.   
->run "mvn clean javafx:run" to run the code.

## Implementation

Used flyweight design pattern for players using hashmap and used singleton design pattern for filehandler which serializes and desirealizes players hashmap (containing all the data) .
We have provided player an opportunity to save game which save cherries and score of the player .
In case player falls or collides with platform he can use 2 cherries to revive himself and continue as befor but cherries will be deducted by 2 . We also mention highest score achieved by player . We have added background music https://www.youtube.com/watch?v=XoLouT7TqZY to enhance user experience. 
We have made 8 scenes and only gameWindow has a separate controller . We have used AniamtionTimer to detect collision with platform and catching of cherries .

## Multithreading 
We have used multithreading in playing music in background and used Animation timer for
detection of collisions