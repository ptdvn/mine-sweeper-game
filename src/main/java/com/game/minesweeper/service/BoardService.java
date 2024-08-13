package com.game.minesweeper.service;

import com.game.minesweeper.dto.BoardInfoDTO;
import com.game.minesweeper.entity.Board;

import java.util.Scanner;

public interface BoardService {
    void printBoard(Board board);
    void revealSquare(Board board, int row, int col);
    BoardInfoDTO buildBoardInfoDTO(Scanner scanner);
    Board buildBoard(BoardInfoDTO boardInfoDTO);

}
