/*
 * Copyright 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.minesweeper.entity;

import com.game.minesweeper.constants.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Square[][] squares;
    private int numMines;
    private int numberOfNonMineSquares;
    private int numberOfNonMineSquaresRevealed;
    private boolean mineHit;
    private int rows;
    private int columns;

    public Board(int rows, int cols, int mines) {
        this.rows = rows;
        this.columns = cols;
        this.numMines = mines;
        this.numberOfNonMineSquares = (this.rows * this.columns) - numMines;
        initializeSquares(rows, cols);
        placeMines();
        calculateAdjacentMines();
    }

    private void initializeSquares(int rows, int cols) {
        squares = new Square[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                squares[i][j] = new Square();
                squares[i][j].setPrintValue(Constants.UNDERSCORE_SYMBOL);
            }
        }
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(columns);
            if (!squares[row][col].isMine()) {
                squares[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    public void processMine(int row, int col) {
        if (getSquares()[row][col].isMine()) {
            mineHit = true;
        }
    }

    public boolean isGameOver() {
        return isGameLost() || isGameWon();
    }
    public boolean isGameLost() {
        return mineHit;
    }

    public boolean isGameWon() {
        return numberOfNonMineSquares == numberOfNonMineSquaresRevealed;
    }



    /**
     * Calculates the number of adjacent mines for each square of the grid.
     * <p>
     * This method iterates through each square in the grid. For each non-mine square,
     * it counts the number of adjacent squares that contain mines and sets this count as the number of adjacent mines for that square.
     * </p>
     * <p>
     * The method considers all eight possible adjacent squares (up, down, left, right, and the four diagonals)
     * and ensures that the square being checked is within the bounds of the grid.
     * </p>
     */
    private void calculateAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!squares[i][j].isMine()) {
                    int count = 0;
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (isValidSquare(i + x, j + y) && squares[i + x][j + y].isMine()) {
                                count++;
                            }
                        }
                    }
                    squares[i][j].setAdjacentMines(count);
                }
            }
        }
    }

    public boolean isValidSquare(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }
}
