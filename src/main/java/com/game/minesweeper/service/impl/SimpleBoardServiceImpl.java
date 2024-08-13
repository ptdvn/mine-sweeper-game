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

package com.game.minesweeper.service.impl;

import com.game.minesweeper.constants.Constants;
import com.game.minesweeper.converter.NumberConverter;
import com.game.minesweeper.dto.BoardInfoDTO;
import com.game.minesweeper.entity.Board;
import com.game.minesweeper.entity.Square;
import com.game.minesweeper.service.BoardService;

import java.util.Scanner;

import static com.game.minesweeper.constants.Constants.BLANK_SPACE;
import static com.game.minesweeper.constants.Constants.MAX_MINE_TO_SQUARE_RATIO;

public class SimpleBoardServiceImpl implements BoardService {

    @Override
    public BoardInfoDTO buildBoardInfoDTO(Scanner scanner) {
        int gridSize = getGridSize(scanner);
        int numberOfMines = getNumberOfMines(scanner, gridSize);
        return BoardInfoDTO.builder().gridSize(gridSize).numberOfMines(numberOfMines).build();
    }

    @Override
    public Board buildBoard(BoardInfoDTO boardInfoDTO) {
        return new Board(boardInfoDTO.getGridSize(), boardInfoDTO.getGridSize(), boardInfoDTO.getNumberOfMines());
    }

    private int getNumberOfMines(Scanner scanner, int gridSize) {
        int numberOfMines;
        askForNumberOfMines();
        while (true) {
            if (scanner.hasNextInt()) {
                numberOfMines = scanner.nextInt();
                if (numberOfMines > 0 && numberOfMines <= (gridSize * gridSize) * MAX_MINE_TO_SQUARE_RATIO) {
                    break;
                } else {
                    System.err.println("That's not a valid grid size. Please try again.\n");
                    askForNumberOfMines();
                }
            } else {
                System.err.println("That's not a valid integer. Please try again.\n");
                askForNumberOfMines();
                scanner.next();
            }
        }
        return numberOfMines;
    }

    private int getGridSize(Scanner scanner) {
        int gridSize;
        askForGridSize();
        while (true) {
            if (scanner.hasNextInt()) {
                gridSize = scanner.nextInt();
                if (gridSize > 0 && gridSize <= Constants.MAX_GRID_SIZE) {
                    break;
                } else {
                    System.err.println("That's not a valid grid size. Please try again.\n");
                    askForGridSize();
                }
            } else {
                System.err.println("That's not a valid integer. Please try again.\n");
                askForGridSize();
                scanner.next();
            }
        }
        return gridSize;
    }

    private void askForGridSize() {
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid - Max size 9x9 grid): ");
    }

    private void askForNumberOfMines() {
        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
    }

    @Override
    public void printBoard(Board board) {
        System.out.println("Here is your mine field:");
        printColumnNumberRow(board);
        printSquareRows(board);
    }

    private void printColumnNumberRow(Board board) {
        StringBuilder columnString = new StringBuilder();
        columnString.append(BLANK_SPACE);
        for (int j = 0; j < board.getColumns(); j++) {
            columnString.append(BLANK_SPACE).append(j + 1);
        }
        System.out.println(columnString);
    }

    private void printSquareRows(Board board) {
        for (int i = 0; i < board.getRows(); i++) {
            StringBuilder rowString = new StringBuilder();
            rowString.append((NumberConverter.convertIntToChar(i)));
            for (int j = 0; j < board.getColumns(); j++) {
                rowString.append(BLANK_SPACE).append(board.getSquares()[i][j].getPrintValue());
            }
            System.out.println(rowString);
        }
    }

    /**
     * Reveals the square at the specified row and column.
     * If the square is not a mine and has not been revealed yet, it will be revealed.
     * If the square has no adjacent mines, the method will recursively reveal all adjacent squares.
     *
     * @param row the row index of the square to reveal
     * @param col the column index of the square to reveal
     */
    @Override
    public void revealSquare(Board board, int row, int col) {
        if(!board.isValidSquare(row, col)) {
            return;
        }
        Square square = board.getSquares()[row][col];
        if (!square.isRevealed() && !square.isMine()) {
            square.setRevealed(true);
            board.setNumberOfNonMineSquaresRevealed(board.getNumberOfNonMineSquaresRevealed() + 1);
            square.setPrintValue(String.valueOf(square.getAdjacentMines()));
            revealAdjacentSquares(board, row, col, square);
        }
    }

    private void revealAdjacentSquares(Board board, int row, int col, Square square) {
        if (square.getAdjacentMines() == 0) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    revealSquare(board,row + x, col + y);
                }
            }
        }
    }

}
