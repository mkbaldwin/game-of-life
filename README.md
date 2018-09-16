# Game of Life

A simple implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) written in Kotlin. 

## About the Game

The "Game" is a simulation of cellular life that takes an initial set of input, and then applies a set of rules
on each successive iteration (generation).

## Rules

The following rules are applied during each round of the simulation. The state of each cell in the next generation is 
based on the  cell's eight neighboring cells.

 * A live cell with less than two live neighbors dies.
 * A live cell with two or three live neighbors lives.
 * A live cell with more than three live neighbors dies.
 * A dead cell with exactly three live cells becomes alive. 
 
 ## Compiling and Running
 
 This application is written in Kotlin and includes a Gradle build script. The build script should be run using the 
 gradle wrapper included.
 
 On OS X/Linux:
 
 ```
 ./gradlew start
 ```

On Windows:

```
gradlew.bat start
```
 
 ## License

This code is published under the [MIT license](LICENSE). 