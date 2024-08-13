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

import com.game.minesweeper.dto.BoardInfoDTO;
import com.game.minesweeper.entity.Board;
import com.game.minesweeper.entity.Square;
import com.game.minesweeper.service.impl.SimpleBoardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SimpleBoardServiceImplTest {


    @Mock
    private Board board;

    @Mock
    private Scanner scanner;

    private SimpleBoardServiceImpl simpleBoardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        simpleBoardService = new SimpleBoardServiceImpl();
    }

    @Test
    void testBuildBoardInfoDTO() {
        when(scanner.hasNextInt()).thenReturn(true, true, false);
        when(scanner.nextInt()).thenReturn(5, 5);

        BoardInfoDTO boardInfoDTO = simpleBoardService.buildBoardInfoDTO(scanner);

        assertEquals(5, boardInfoDTO.getGridSize());
        assertEquals(5, boardInfoDTO.getNumberOfMines());
    }

    @Test
    void testBuildBoard() {
        BoardInfoDTO boardInfoDTO = new BoardInfoDTO(5, 9);
        Board board = simpleBoardService.buildBoard(boardInfoDTO);

        assertEquals(9, board.getRows());
        assertEquals(9, board.getColumns());
        assertEquals(5, board.getNumMines());
    }

    @Test
    void testPrintBoard() {
        when(board.getColumns()).thenReturn(5);
        when(board.getRows()).thenReturn(5);

        Square[][] squares = new Square[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                squares[i][j] = new Square();
            }
        }
        when(board.getSquares()).thenReturn(squares);

        assertDoesNotThrow(() -> simpleBoardService.printBoard(board));
    }

    @Test
    void testRevealSquare() {
        when(board.isValidSquare(1, 1)).thenReturn(true);

        Square[][] squares = new Square[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                squares[i][j] = new Square();
            }
        }

        when(board.getSquares()).thenReturn(squares);

        simpleBoardService.revealSquare(board, 1, 1);

        assertTrue(squares[1][1].isRevealed());
    }


}
