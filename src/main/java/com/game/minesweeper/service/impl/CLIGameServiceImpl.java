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
import com.game.minesweeper.service.BoardService;
import com.game.minesweeper.service.GameService;
import com.game.minesweeper.validation.InputValidator;

import java.util.Scanner;

import static com.game.minesweeper.constants.Constants.UPPERCASE_YES;


public class CLIGameServiceImpl implements GameService {

    private BoardService boardService;

    public CLIGameServiceImpl(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * This method is responsible for starting and managing the game of Minesweeper.
     * The game continues as long as the player chooses to play again after each game. This is controlled by the `continuePlaying` boolean.*
     * Once the game is over (either the user has won or lost), it calls the `endGame` method to display the game over message.
     * Finally, the player is asked if they want to play again. If they choose to do so, a new game starts. Otherwise, the method ends, and the game is over.
     */
    @Override
    public void startGame() {
        boolean continuePlaying = true;
        while (continuePlaying) {
            System.out.println("Welcome to Minesweeper!\n");
            Scanner scanner = new Scanner(System.in);
            BoardInfoDTO boardInfoDTO = boardService.buildBoardInfoDTO(scanner);
            Board board = boardService.buildBoard(boardInfoDTO);
            while (!board.isGameOver()) {
                boardService.printBoard(board);
                String squareAddressInput = getValidSquareAddressInput(scanner, boardInfoDTO);
                processUserInput(squareAddressInput, board);
            }
            endGame(board);
            continuePlaying = askToPlayAgain(scanner);
        }
    }

    private boolean askToPlayAgain(Scanner scanner) {
        System.out.println("Would you like to play again? (yes/no)");
        String userInput = scanner.next();
        return userInput.equalsIgnoreCase(UPPERCASE_YES);
    }

    @Override
    public void endGame(Board board) {
        if (board.isGameLost()) {
            System.out.println("Oh no, you detonated a mine! Game over.\n");
        }
        if (board.isGameWon()) {
            System.out.println("Congrats! You swept all mines! Game over.\n");
        }
    }

    private String getValidSquareAddressInput(Scanner scanner, BoardInfoDTO boardInfoDTO) {
        String userInput;
        while (true) {
            System.out.println("Please input new square to reveal! (e.g. A1):");
            userInput = scanner.next();
            if (InputValidator.isValidSquareAddress(userInput, boardInfoDTO)) {
                break;
            }
        }
        return userInput;
    }

    /**
     * This method processes the user's input during the game.
     *
     * It first converts the user's input into row and column indices. The input is expected to be in the format of a letter
     * followed by a number (e.g., "A1"), where the letter represents the row and the number represents the column.
     *
     * The method then calls the `processMine` method on the board with the row and column indices. If a mine is hit,
     * the method returns immediately.
     *
     * If a mine is not hit, the method reveals the square at the given indices and prints a message to the console
     * indicating the number of adjacent mines to that square.
     *
     * @param string The user's input, expected to be in the format of a letter followed by a number (e.g., "A1").
     * @param board The game board.
     */
    @Override
    public void processUserInput(String string, Board board) {
        int rowIndex = NumberConverter.convertCharToIntIgnoreCase(string.charAt(Constants.ROW_INDEX));
        int columnIndex = Integer.parseInt(String.valueOf(string.charAt(Constants.COLUMN_INDEX))) - 1;
        board.processMine(rowIndex, columnIndex);
        if (board.isMineHit()) {
            return;
        }
        boardService.revealSquare(board, rowIndex, columnIndex);
        System.out.println(String.format("This square contains %d adjacent mines. \n", board.getSquares()[rowIndex][columnIndex].getAdjacentMines()));
    }

}

