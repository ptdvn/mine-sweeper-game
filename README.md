# Mine Sweeper Game

## System Design

The Mine Sweeper Game is a console-based application developed in Java. The application follows a simple object-oriented design with a focus on encapsulation and modularity. The main components of the application are:

- `Board`: Represents the game board. It contains a grid of `Square` objects and methods to manipulate and query the state of the board.
- `Square`: Represents a square on the game board. It contains information about whether the square is a mine, the number of adjacent mines, and whether it has been revealed.
- `GameServiceInjector` and `CLIGameServiceInjectorImpl`: These are used for dependency injection, providing a flexible way to construct and configure the game.
- `NumberConverter`: A utility class for converting between characters and integers, used for user input and output.

## Assumptions

- The game is played on a square grid, with a maximum size of 9x9.
- The number of mines is at most 35% of the total number of squares.
- User input for grid size and number of mines is provided via the console.
- User input for revealing squares is provided as letters (A-I) or (a-i) for rows and numbers (1-9) for columns.

## System Requirements
- Operating System: Windows, Linux, or MacOS
- Java 11 or higher
- Maven 3.6.0 or higher

## Build Instructions

1. Clone the repository to your local machine.
2. Navigate to the project directory in your terminal.
3. Run the following command to build the project:

```bash
mvn clean install
```

## Run Instructions

After building the project, you can run the game with the following command:

```bash
java -cp target/mine-sweeper-game-1.0-SNAPSHOT.jar com/game/minesweeper/MineSweeperApplication
```

Follow the prompts in the console to play the game. Enjoy!

## License

This project is licensed under the Apache License, Version 2.0. See the `LICENSE` file for more details.
